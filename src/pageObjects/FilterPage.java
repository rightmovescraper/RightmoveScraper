package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FilterPage {
    private static WebElement element = null;
    private static Select select = null;

    public static Select searchRadius(WebDriver driver){
        select = new Select(driver.findElement(By.id("radius")));
        return select;
    }

    public static Select minPrice(WebDriver driver){
        select = new Select(driver.findElement(By.id("minPrice")));
        return select;
    }

    public static Select maxPrice(WebDriver driver){
        select = new Select(driver.findElement(By.id("maxPrice")));
        return select;
    }

    public static Select minBedrooms(WebDriver driver){
        select = new Select(driver.findElement(By.id("minBedrooms")));
        return select;
    }

    public static Select propertyType(WebDriver driver){
        select = new Select(driver.findElement(By.id("displayPropertyType")));
        return select;
    }

    public static WebElement submitButton(WebDriver driver){
        element = driver.findElement(By.id("submit"));
        return element;
    }

    public static void setParameters(WebDriver driver){
        searchRadius(driver).selectByVisibleText("Within 40 miles");
        minPrice(driver).selectByVisibleText("50,000");
        maxPrice(driver).selectByVisibleText("150,000");
        minBedrooms(driver).selectByVisibleText("2");
        propertyType(driver).selectByVisibleText("Houses");
        submitButton(driver).click();
    }





}
