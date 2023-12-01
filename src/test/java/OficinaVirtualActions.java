import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class OficinaVirtualActions extends Main {
    
    public static void accederOficinaVirtual() throws InterruptedException {
      // Accions - Entrar pagina principal
      Main.driver.get("https://seuelectronica.ajuntament.barcelona.cat/oficinavirtual/ca");
      Thread.sleep(1000);
    }

    public static void accederPaginaBusquedaTramites() {
        // Accions - Acceder a la pagina de busqueda de tramites y ordenación alfabética
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(@class,'font s16 regular white research-all')]")))).click();
    
    }

    public static void realizarBusquedaOficinaVirtual(String busqueda) {
        // Accion
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//input[@name=\"searchWord\"]")))).sendKeys(busqueda);
        // Asserts
        Assert.assertTrue(driver.findElements(By.xpath("//strong[text()='"+busqueda+"']")).isEmpty());

    }

    public static void verificarBusquedaOficinaVirtual(String busqueda) {
      // Accion
      wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@role=\"listbox\"]/mat-option[1]")))).click();
      // Asserts
      Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), \""+busqueda+"\")]"))).isDisplayed());

    }

    public static void ordenarAlfabeticamente(char letra) throws InterruptedException {
       wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[text()='"+letra+"']")))).click();
      // Asserts
      Assert.assertTrue(driver.findElement(By.xpath("//span[@id='"+letra+"']")).isDisplayed());
      Thread.sleep(1000);
    
    }
}
