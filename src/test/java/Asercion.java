import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class Asercion extends Main {
    
    public static void validarElemento(String xPath) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        Assert.assertTrue(element.isDisplayed());
    }

    public static void validarElementoInvisible(String xPath) {
        boolean element = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPath)));
        Assert.assertTrue(element);
    }

    public static void validarURL(String url) {
        Assert.assertEquals(url, Main.driver.getCurrentUrl());
    }

    public static void validarTexto(String xPath, String textoAValidar) {
        String texto = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath))).getText();
        Assert.assertEquals(texto, textoAValidar);
    }

    public static void validarPagina(String xpath, String textoValidar) {
        validarElemento(xpath);
    
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner')]")));

        validarTexto(xpath, textoValidar);

      }

}
