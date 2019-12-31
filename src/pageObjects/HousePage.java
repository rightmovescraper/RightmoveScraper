package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HousePage {
    private static WebElement element = null;
    private static String previousPrice;
    private static String currentPrice;
    private static int previousValue;
    private static int currentValue;
    private static boolean booleanValue;
    private static double percentage;
    private static String string;


    public static String street(WebDriver driver) {
        element = driver.findElement(By.cssSelector("#primaryContent > div.row.one-col.property-header > div > div > div.property-header-bedroom-and-price > div > address > meta:nth-child(1)"));
        string = element.getAttribute("content");
        return string;
    }

    public static WebElement broadbandButton(WebDriver driver){
        element = driver.findElement(By.cssSelector("#primaryContent > div.row.one-col.property-header > div > div > div.property-header-bedroom-and-price > div > address > meta:nth-child(1)"));
        return element;
    }

    public static WebElement broadbandButtonTriggered(WebDriver driver){
        element = driver.findElement(By.cssSelector(".see-all-offers"));
        return element;
    }
   public static String getFullAddress(WebDriver driver){
        element = driver.findElement(By.cssSelector("a.see-all-offers"));
        string = element.getAttribute("href");
       int index1 = string.indexOf("location=")+9;
       int index2 = string.indexOf("&utm_source");
       String string2 = string.substring(index1, index2);
       return street(driver) + " " + string2;
    }

    public static WebElement marketInfo(WebDriver driver){
        element = driver.findElement(By.cssSelector("#historyMarketTab"));
        return element;
    }

    public static boolean wasItSold(WebDriver driver, WebDriverWait wait){
        booleanValue = driver.findElements(By.xpath("//*[@id=\"soldHistoryBody\"]/table/tbody/tr")).size() != 0;
        return booleanValue;
    }

    public static int getPreviousValue(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"soldHistoryBody\"]/table/tbody/tr/td[2]"));
        previousPrice = element.getText().replaceAll("[\\D+]", "");
        previousValue = Integer.parseInt(previousPrice);
        return previousValue;
    }

    public static int getCurrentValue(WebDriver driver){
        element = driver.findElement(By.id("propertyHeaderPrice"));
        currentPrice = element.getText().replaceAll("[\\D+]", "");
        currentValue = Integer.parseInt(currentPrice);
        return currentValue;
    }

    public static double negativeEquityCalculation(WebDriver driver){
        percentage = (getPreviousValue(driver) * 100) / getCurrentValue(driver);
        return percentage;
    }

    public static WebElement heartProperty(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"secondaryContent\"]/div[2]/div/div/div/ul/li[1]/a"));
        return element;
    }

    public static void likeProperty(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"secondaryContent\"]/div[2]/div/div/div/ul/li[1]/a"));
        if(element.getAttribute("class").contentEquals("icon-before icon-star unsaved button secondary registersource-save-property ")){
            element.click();
        }
    }









}
