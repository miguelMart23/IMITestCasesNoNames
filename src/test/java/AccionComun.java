import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

public class AccionComun extends Main {
    
    public static void accederOficinaVirtual() throws InterruptedException {
      // Accions - Entrar pagina principal
      Main.driver.get("https://seuelectronica.ajuntament.barcelona.cat/oficinavirtual/ca");
      Thread.sleep(8000);
    }

    public static void accederOficinaVirtualVPN() throws InterruptedException {
      // Accions - Entrar pagina principal
      Main.driver.get("https://seuelectronica-int.ajuntament.bcn/oficinavirtual/ca");
      Thread.sleep(8000);
    }

    public static void accederPaginaBusquedaTramites() {
        // Accions - Acceder a la pagina de busqueda de tramites y ordenación alfabética
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(@class,'font s16 regular white research-all')]")))).click();
    
    }

    public static void realizarBusquedaOficinaVirtual(String busqueda) throws IOException {

      // Accion
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//input[@name=\"searchWord\"]")))).sendKeys(busqueda);
        // Asserts
        Assert.assertTrue(driver.findElements(By.xpath("//strong[text()=\""+busqueda+"\"]")).isEmpty());
        AccionComun.takeScreenshoot("searchDone.png");

    }

    public static void verificarBusquedaOficinaVirtual(String busqueda) {
      // Accion
      wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@role=\"listbox\"]/mat-option[1]")))).click();
      // Asserts
      //Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), \""+busqueda+"\")]"))).isDisplayed());

    }

    public static void ordenarAlfabeticamente(char letra) throws InterruptedException {
      Thread.sleep(5000);
       wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[text()='"+letra+"']")))).click();
      // Asserts
      Assert.assertTrue(driver.findElement(By.xpath("//span[@id='"+letra+"']")).isDisplayed());
      Thread.sleep(1000);
    
    }

    public static void accederPaginaCrearTramite() throws InterruptedException {
      // Accion
      driver.get("https://seuelectronica.ajuntament.barcelona.cat/APPS/ptbportal/login.do?language=ca&style=ciutada&origen=portal_tramits&loginTarget=T400i&iniciar=");
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
      Thread.sleep(2000);
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
    AccionComun.takeScreenshoot("validateForm.png");

    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,1200)", "");
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@value,'Enviar')]"))).click();

  }

  public static void firmarDigitalmente() throws InterruptedException,IOException {

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='firmaNueva']"))).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='certificat']/span[@class='txt']"))).click();
    AccionComun.takeScreenshoot("sendedTramit.png");

    Thread.sleep(20000);
   
  }

  public static void iniciarTramite() {
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(text(), 'Inicia el tràmit')])[1]"))).click();
 
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'btn-generic primary font s16 none')]"))).click();
  }
 
  public static void anyadirParametros(String parametro, String valor) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname=\"" + parametro + "\"]"))).sendKeys(valor);
  }
 
  public static void aceptarSubmit() throws InterruptedException {
    Thread.sleep(5000);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@value='Següent']"))).click();
  }
 
  public static void rellenarTextArea(int posicion, String valor) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//textarea[contains(@class,'text-area-edit')])[" + posicion + "]"))).sendKeys(valor);
  }
 
  public static void enviarTramite() {
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@value='Enviar']"))).click();
  }
 
  public static void guardarYCerrarTramite() {
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='TANCAR I TORNAR']"))).click();
  }

  public static void enviarAlBuscador(String xpathBuscador, String textoBuscar) {

    try {
      driver.findElement(By.xpath(xpathBuscador)).sendKeys(textoBuscar);
    
      Thread.sleep(1000);

      driver.findElement(By.xpath(xpathBuscador)).sendKeys(Keys.ENTER);
    }
    catch (InterruptedException e) {
        e.printStackTrace();
    }
    
  }

  public static void takeScreenshoot(String nombreFoto) throws IOException {
      scrshot = ((TakesScreenshot)driver);
      srcFile = scrshot.getScreenshotAs(OutputType.FILE);
      destFile = new File("./screenshoots/"+java.time.LocalDate.now()+"/"+nombreFoto);
      FileUtils.copyFile(srcFile, destFile);
  }

  public static void scrollDown(int scrollAbajoPx) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,"+scrollAbajoPx+")", "");
  }

  public static void checkCorrectOperationNumber() throws IOException {
    // Paso 1
    scrollDown(600);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class,'referencia')][1]/span")));
    // Paso 2
    takeScreenshoot("IMI_TC003-Correct_Operation_Number-1");
    // Paso 3
    String tramitId = driver.findElement(By.xpath("//p[contains(@class,'referencia')][1]/span")).getText();
    // Paso 4
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'caja_estado')][2]//a[1]"))).click();
    // Paso 5
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='home-tab']")));
    // Paso 6
    wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner-file')]"))));
    // Paso 7
    takeScreenshoot("IMI_TC003-Correct_Operation_Number-2");
    // Paso 8
    driver.findElement(By.xpath("//a[@id='home-tab']")).click();
    // Paso 9 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='"+tramitId+"']"))).click();
    // Paso 10 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-query-tramit-detail")));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-query-tramit-detail")));
    // Paso 11
    takeScreenshoot("IMI_TC003-Correct_Operation_Number-3");
    // Paso 12 
    String originalWindow = driver.getWindowHandle();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'col-10 col-sm-8 col-md-4 btn-generic primary mb-4 font s16')]"))).click();
    // Paso 13
    for (String windowHandle : driver.getWindowHandles()) {
      if(!originalWindow.contentEquals(windowHandle)) {
          driver.switchTo().window(windowHandle);
          break;
      }
    }
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='contingut']")));
    // Paso 14
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//tbody//tr[1]//td[2]"))).getText().compareTo(tramitId);
  }



}
