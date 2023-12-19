import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Asercion extends Main {
    
    public static void validarElemento(String xPath) {
        WebElement element = driver.findElement(By.xpath(xPath));
        Assert.assertTrue(element.isDisplayed());
    }

}
