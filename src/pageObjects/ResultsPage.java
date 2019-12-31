package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class ResultsPage {
    private static WebElement element = null;
    private static List<WebElement> elements = null;
    private static ArrayList<String>  strings = new ArrayList<>();
    private static Select select = null;
    private static int number;
    private static String string;

    public static WebElement pageNum(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"l-container\"]/div[3]/div/div/div/div[2]/span[3]"));
        return element;
    }

    public static int pageNumber(WebDriver driver){
        number = Integer.parseInt(pageNum(driver).getText());
        return number;
    }

    public static WebElement nextButton(WebDriver driver){
        element = driver.findElement(By.cssSelector(".pagination-direction--next"));
        return element;
    }

    public static List<WebElement> housesList(WebDriver driver){
        elements = driver.findElements(By.cssSelector("a.propertyCard-anchor"));
        return elements;
    }

    public static String getHouseUrl(WebElement element){
            string = element.getAttribute("id").replaceAll("[\\D+]", "");
            number = Integer.parseInt(string);
            String url ="https://www.rightmove.co.uk/property-for-sale/property-"+ number +".html";
            return url;
    }

    public static Select sortOldest(WebDriver driver){
        select = new Select(driver.findElement(By.id("sortType")));
        return select;
    }












}
