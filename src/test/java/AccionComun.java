import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class AccionComun extends Main {
    
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
        Assert.assertTrue(driver.findElements(By.xpath("//strong[text()=\""+busqueda+"\"]")).isEmpty());

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

    public static void accederPaginaCrearTramite() throws InterruptedException {
      // Accion
      driver.get("https://seuelectronica-int.ajuntament.bcn/APPS/ptbportal/login.do?language=ca&style=ciutada&origen=portal_tramits&loginTarget=T400i&iniciar=");
      Thread.sleep(1000);
  }

  public static void loginCertificado(boolean esCiudadano) {

    try {
      // Crea las clases y las convierten en hilos
      AbrirPopUpCertificado abrirPopUpCertificado = new AbrirPopUpCertificado(driver, wait);
      Thread hiloAbrirPopUpCertificado = new Thread(abrirPopUpCertificado);
      EnviarCertificado enviarCertificado = new EnviarCertificado(esCiudadano);
      Thread hiloEnviarCertificado = new Thread(enviarCertificado);

      // Corren
      hiloAbrirPopUpCertificado.start();
      hiloEnviarCertificado.start();

      hiloAbrirPopUpCertificado.join();
      hiloEnviarCertificado.join();
    }
    catch (InterruptedException e) {
        e.printStackTrace();
    }
    
  }

  public static void adjuntarFicheroTramite() {
          // Paso 14
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"page\"]")));
      
      // Paso 15
      // Accion
      File file = new File("./src/test/resources/ff.txt");
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id=\"doc-file\"]"))).sendKeys(file.getAbsolutePath());
      
      // Paso 16
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"text_add\"]"))).sendKeys("titol prova");
    
      // Paso 17
      // Accion
      //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@value,\"Adjuntar\")]"))).click();
    
      // Paso 17
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='botonera']/a"))).click();
    
  }

  public static void rellenarFormularioAñadirTramite() {
      // Paso 3
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='T00i_INT_domTipoVia']/option[@value='DM.R.CALLE']"))).click();
    
      // Paso 4
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='T00i_INT_domNombre']"))).sendKeys("Montserrat");
    
      // Paso 5
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='T00i_INT_domNumero']"))).sendKeys("10");
    
      // Paso 6
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='T00i_INT_domMunicipio']"))).sendKeys("Barcelona");
    
      // Paso 7
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='T00i_INT_domProvincia']"))).sendKeys("Barcelona");
    
      // Paso 8
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='T00i_INT_contEmail']"))).sendKeys("melopez@aubay.es");
    
      // Paso 9
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='T00i_INT_contTelMobil']"))).sendKeys("605582675");
    
      // Paso 10
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='T00i_asuntoRegistro']"))).sendKeys("text prova");
    
      // Paso 11
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='T00i_expofets']"))).sendKeys("text prova");

      // Paso 12
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='T00i_concpetic']"))).sendKeys("text prova");

      // Paso 13
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='continuar']"))).click();
  }

  public static void validarFormulario() throws IOException {
    scrshot = ((TakesScreenshot)driver);
    srcFile = scrshot.getScreenshotAs(OutputType.FILE);
    destFile = new File("./screenshoots/validateForm.png");
    FileUtils.copyFile(srcFile, destFile);

    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,1200)", "");
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@value,'Enviar')]"))).click();

  }

  public static void firmarDigitalmente() throws InterruptedException,IOException {

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='firmaNueva']"))).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='certificat']/span[@class='txt']"))).click();
    scrshot = ((TakesScreenshot)driver);
    srcFile = scrshot.getScreenshotAs(OutputType.FILE);
    destFile = new File("./screenshoots/sendedTramit.png");
    FileUtils.copyFile(srcFile, destFile);

    Thread.sleep(20000);
   
  }

}
