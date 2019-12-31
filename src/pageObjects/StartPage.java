package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class StartPage {
    private static WebElement element = null;

    public static WebElement allowCookies(WebDriver driver){
        try {
            Thread.sleep(2000L);
        } catch (Exception e) {
            e.getCause();
            System.out.println(e);
        }

        element = driver.findElement(By.cssSelector("button.optanon-allow-all"));
        return element;
    }

    public static WebElement goToSignIn(WebDriver driver){
        element = driver.findElement(By.cssSelector("#sign-in"));
        return element;
    }

    public static WebElement searchInput(WebDriverWait wait){
        element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchLocation")));
        return element;
    }

    public static WebElement buyEstate(WebDriver driver){
        element = driver.findElement(By.id("buy"));
        return element;
    }
}
