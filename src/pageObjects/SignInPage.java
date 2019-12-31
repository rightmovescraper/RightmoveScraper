package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class SignInPage {
    private static WebElement element = null;

    public static WebElement enterUsername(WebDriver driver){
        element = driver.findElement(By.cssSelector("#email"));
        return element;
    }

    public static WebElement enterPassword(WebDriver driver){
        element = driver.findElement(By.cssSelector("#password"));
        return element;
    }

    public static WebElement signInButton(WebDriver driver){
        element = driver.findElement(By.cssSelector(".mrm-button"));
        return element;
    }

    public static void signIn(WebDriver driver){
        enterUsername(driver).sendKeys("milicaph@ymail.com");
        enterPassword(driver).sendKeys("&2!gr*WyskmhJQr");
        signInButton(driver).click();
    }


}
