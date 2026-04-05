package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("http://opencart.abstracta.us/");
        /* driver.get("https://demo.opencart.com");*/
    }

    public void clickProduct(String product) {
        driver.findElement(By.linkText(product)).click();
    }

    public String getProductCode() {
        return driver.findElement(By.xpath("//li[contains(text(),'Product Code')]")).getText();
    }
}