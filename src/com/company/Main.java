package com.company;

import excelIO.XlsxIO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "E:\\ckola\\gecko26\\geckodriver.exe");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        firefoxOptions.setHeadless(true);

        WebDriver driver = (WebDriver) new org.openqa.selenium.firefox.FirefoxDriver(firefoxOptions);
        WebDriverWait wait = new WebDriverWait(driver, 60L);

        driver.get("https://www.rightmove.co.uk/");
        StartPage.allowCookies(driver).click();
        StartPage.goToSignIn(driver).click();

        SignInPage.signIn(driver);

        Actions builder = new Actions(driver);
        Actions seriesOfActions = builder.moveToElement(StartPage.searchInput(wait)).click().sendKeys(StartPage.searchInput(wait), "Stockton-On-Tees, Cleveland");
        seriesOfActions.perform();

        StartPage.buyEstate(driver).click();
        FilterPage.setParameters(driver);

        List<WebElement> houses;
        ArrayList<String> houseUrls = new ArrayList<>();

        ResultsPage.sortOldest(driver).selectByVisibleText("Oldest Listed");

        for (int i = 1; i <= 3; i++) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.propertyCard-anchor")));
            houses = ResultsPage.housesList(driver);
            System.out.println("Page " + i + ". " + "Houses: " + houses.size() + ".");

            for (WebElement house : houses) {
                houseUrls.add(ResultsPage.getHouseUrl(house));
                System.out.println(ResultsPage.getHouseUrl(house));
            }

            if (i != ResultsPage.pageNumber(driver)) {
                ResultsPage.nextButton(driver).click();
            }

        }

        System.out.println("Calculating...");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Results");
        int i = 0;


        ArrayList<String> addresses = new ArrayList<>();
        ArrayList<String> negativeEquityProperties = new ArrayList<>();
        String address;

        for (String houseUrl : houseUrls) {

            driver.get(houseUrl);

            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".check-broadband-speed"))).click();
            wait.until(ExpectedConditions.attributeContains(By.cssSelector(".see-all-offers"), "href", "https://broadband.comparethemarket.com/deeplink/packages/fastest"));
            address = HousePage.getFullAddress(driver);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.cssSelector("#property-detail-button-middle")));

            wait.until(ExpectedConditions.elementToBeClickable(By.id("historyMarketTab"))).click();
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

            if (HousePage.wasItSold(driver, wait)) {
                if (HousePage.getPreviousValue(driver) >= HousePage.getCurrentValue(driver)) {
                    String url = driver.getCurrentUrl();
                    negativeEquityProperties.add(url);
                    addresses.add(address);
                    System.out.println(url);
                    System.out.println(address);

                    Row row = XlsxIO.row(sheet, i++);
                    Cell cell1 = XlsxIO.cell(row, 0);
                    Cell cell2 = XlsxIO.cell(row, 1);
                    XlsxIO.setCell(cell1, url);
                    XlsxIO.setCell(cell2, address);

                    if (HousePage.heartProperty(driver).getAttribute("title").contains("Save")) {
                        HousePage.likeProperty(driver);
                    }

                } else if (HousePage.getCurrentValue(driver) > HousePage.getPreviousValue(driver) && HousePage.negativeEquityCalculation(driver) > 90.00) {
                    String url = driver.getCurrentUrl();
                    negativeEquityProperties.add(driver.getCurrentUrl());
                    addresses.add(address);
                    System.out.println(url);
                    System.out.println(address);

                    Row row = XlsxIO.row(sheet, i++);
                    Cell cell1 = XlsxIO.cell(row, 0);
                    Cell cell2 = XlsxIO.cell(row, 1);
                    XlsxIO.setCell(cell1, url);
                    XlsxIO.setCell(cell2, address);

                    if (HousePage.heartProperty(driver).getAttribute("title").contains("Save")) {
                        HousePage.likeProperty(driver);
                    }

                } else continue;
            } else continue;
        }
        System.out.println(negativeEquityProperties.size());

        driver.close();

        System.out.println("Writing to excel...");

        try {
            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Milica\\Desktop\\Test.xlsx");
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}










