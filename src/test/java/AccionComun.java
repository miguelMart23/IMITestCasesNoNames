import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class AccionComun extends Main {

  public static void navegarInfoFiscal() throws InterruptedException {
    Asercion.validarElemento("//*[@id=\"profile-tab\"]");
    clickarElementoWait("//*[@id=\"profile-tab\"]");
    Asercion.validarElemento("//*[@id=\"profile\"]/app-enterprise-tax/div[2]/div/div[1]/div/div[1]");
    clickarElementoWait("//*[@id=\"profile\"]/app-enterprise-tax/div[2]/div/div[1]/div/div[1]");
    esperarSegundos(2);
    Asercion.validarElemento("//*[@id=\"ex1-tab-1\"]");
    clickarElementoWait("//*[@id=\"ex1-tab-1\"]");
  }
  public static void comprobarInfoFiscal() throws InterruptedException {
    esperarSegundos(5);
    String url = driver.getCurrentUrl();
      assert url.contains("enterprise-information/tax/vehicles");
      esperarSegundos(2);
      clickarElementoWait("/html/body/app-root/app-personal-space-layout/div/app-personal-space-header/app-corp-header/div[2]/div[2]/div[1]");

  }
  
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
      AccionComun.takeScreenshoot("IMI_TC002-Search.png");

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

  public static void enviarTexto(String xpath, String textoBuscar) {
    try {
      driver.findElement(By.xpath(xpath)).sendKeys(textoBuscar);
    
      esperarSegundos(1);

    }
    catch (InterruptedException e) {
        e.printStackTrace();
    }
    
  }

  public static void pulsarEnter(String xpath) {
    driver.findElement(By.xpath(xpath)).sendKeys(Keys.ENTER);
  }

  public static void takeScreenshoot(String nombreFoto) throws IOException {
      scrshot = ((TakesScreenshot)driver);
      srcFile = scrshot.getScreenshotAs(OutputType.FILE);
      destFile = new File("./screenshoots/"+java.time.LocalDate.now()+"/"+nombreFoto+".png");
      FileUtils.copyFile(srcFile, destFile);
  }

  public static void scrollDown(int scrollAbajoPx) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,"+scrollAbajoPx+")", "");
  }

  public static void recuperarTramitePorId (String tramiteId) {
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Introduïu el número']"))).sendKeys(tramiteId);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Consulta']"))).click();
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Recuperar esborrany']"))).click();
  }

  public static void validarTextoFormulario(String textoEsperado) {
    String textoActual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='text-pas']"))).getText();
    Assert.assertEquals(textoActual, textoEsperado);
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
    /* 
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
  */
  }

      public static void navegarAreaNotificaciones() throws InterruptedException, IOException {

    Asercion.validarElemento("(//div[contains(@class, 'options-desktop')])//p[2]");

    Asercion.validarElementoInvisible("//div[contains(@class, 'bg-spinner-file')]");

    clickarElemento("(//div[contains(@class, 'options-desktop')])//p[2]");

    Asercion.validarElemento("//app-notifications");

    esperarSegundos(2);

    takeScreenshoot("navegarAreaNotificaciones");
  }

  public static void navegarDatosSuscripcion() throws InterruptedException {
      Asercion.validarElemento("//div[contains(@class, 'personal-space')]//div[3]//button");

      clickarElemento("//div[contains(@class, 'personal-space')]//div[3]//button");

      esperarSegundos(5);

      Asercion.validarTexto("//p[@class='title-fitxa mobile font s32 black semi m-0']", "Registre de subscriptors al servei de Notificació Electrònica");
  }

  public static void cambiarNumTelefono(String numero) throws InterruptedException, IOException {
    Asercion.validarElemento("//input[contains(@formcontrolname, 'telephone')]");

    Asercion.validarElementoInvisible("//div[contains(@class, 'bg-spinner-file')]");

    enviarTexto("//input[contains(@formcontrolname, 'telephone')]", numero);

    esperarSegundos(2);

    takeScreenshoot("Introducir Num Telefono");

    Asercion.validarElemento("//button[normalize-space()='Actualitzar dades']");

    clickarElemento("//button[normalize-space()='Actualitzar dades']");
  }

  public static void comprobarCambioNumTelefono() throws InterruptedException {
    clickarElementoWait("//div[@class='content-box-modal col-10 offset-1']//div[2]");

    Asercion.validarElemento("//input[contains(@formcontrolname, 'telephone')]");

    Asercion.validarElementoInvisible("//div[contains(@class, 'bg-spinner-file')]");

    Asercion.validarElemento("//span[contains(text(), '123456789')]");
  }

  public static void buscarTramite(String numeroTramite) throws InterruptedException {
    clickarElementoWait("//i[@class='fa bcn-icon-esquerra-bold']");

    Asercion.validarElemento("(//section[contains(@class,\"check-tramit-status\")])[1]");

    Asercion.validarElemento("//section[@class='check-tramit-status ie10up']");

    WebElement element = driver.findElement(By.xpath("//section[@class='check-tramit-status ie10up']"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

    esperarSegundos(2);

    Asercion.validarElemento("//input[@placeholder='Introduïu el número']");

    enviarTexto("//input[@placeholder='Introduïu el número']", numeroTramite);

    pulsarEnter("//input[@placeholder='Introduïu el número']");
  }

  public static void comprobarBusquedaTramite(String numeroTramite) throws InterruptedException {
    Asercion.validarElemento("//app-tramit-status-detail");

    Asercion.validarElemento("//div[contains(@class,\"modal-subtitle\")]");

    List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + numeroTramite + "')]"));
    Assert.assertTrue(list.size() > 0);
  }

  public static void accederEspacio2() throws InterruptedException {
    driver.get("https://seuelectronica-int.ajuntament.bcn/oficinavirtual/ca");
    clickarElementoWait("(//div[contains(@class,'person-space')])[2]//a");
  }

  public static void realizarBusqueda(String buscar) throws InterruptedException {
    Asercion.validarElemento("//app-root");

    Asercion.validarElemento("//input[@name=\"searchWord\"]");

    enviarTexto("//input[@name=\"searchWord\"]", buscar);

    pulsarEnter("//input[@name=\"searchWord\"]");

    Asercion.validarElemento("(//div[contains(@class,\"media-body\")])[1]");
  
    List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + buscar + "')]"));
    Assert.assertTrue(list.size() > 0);
  }

  public static void iniciarTramiteVolant() throws InterruptedException {
    Asercion.validarElemento("//div[@class='buttons-header both-buttons']//div[contains(text(),'Inicia el tràmit')]");

    Asercion.validarElementoInvisible("//div[contains(@class, 'bg-spinner-file')]");

    clickarElemento("//div[@class='buttons-header both-buttons']//div[contains(text(),'Inicia el tràmit')]");

    esperarSegundos(2);
  }

  public static void rellenarFormularioVolant() throws InterruptedException, IOException {
    clickarElementoWait("//button[@id='botonNext']");

    Asercion.validarElemento("//button[@id='botonNext']");

    esperarSegundos(10);

    scrollDown(1500);

    esperarSegundos(2);

    takeScreenshoot("Ultimo Paso Formulario Volant");

    clickarElemento("/html/body/app-root/app-personal-space-layout/div/app-residence-certificate/app-wizard-step/div/div/div/div/form/section[4]/div/div/div/div/div[2]/div[3]/div[2]/label"); 

    clickarElemento("//*[@id=\"dataForm\"]/app-wizard-buttons/main/footer/div/div/div[2]/div/div[2]/button");
  }

  public static void finalizarTramiteVolant() throws InterruptedException, IOException {
    Asercion.validarElemento("//p[@class='m-0 p-0 finalStep-documentText']");

    takeScreenshoot("Finalizar Tramite Volant");

    Asercion.validarElemento("//div[@class='virtual-ofice vertical-align-c']//a");

    clickarElemento("//div[@class='virtual-ofice vertical-align-c']//a");
  }

  public static void validarPaginaBienvenida() throws InterruptedException {
    esperarSegundos(2);

    Asercion.validarElemento("//app-root");

    Asercion.validarElemento("//*[@id=\"home-tab\"]");

    Asercion.validarElemento("//*[@id=\"tabMainContent\"]");

    Asercion.validarElemento("//input[@name=\"searchWord\"]");
  }

  public static void accederMisTramites() throws InterruptedException, IOException {
    Asercion.validarElemento("//li[contains(@class,'enterprise')][2]");

    takeScreenshoot("User accede Mis Tramites");

    clickarElemento("//li[contains(@class,'enterprise')][2]");
  }

  public static void verDetalleTramite() throws InterruptedException, IOException {
    Asercion.validarElemento("//p[contains(text(),'2023_EXP_49690')]");

    takeScreenshoot("Ver Tramite");

    clickarElemento("//p[contains(text(),'2023_EXP_49690')]");

    esperarSegundos(5);

    Asercion.validarTexto("//p[@class='mb-1 font s24 grey regular mt-4 pt-5']", "Detall");

    takeScreenshoot("Ver Detalles Tramite");

  }
 
  public static void clickarElemento(String xpath) {
    driver.findElement(By.xpath(xpath)).click();
  }

  public static void clickarElementoWait(String xpath) {
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
  }

  public static void esperarSegundos(int segundos) throws InterruptedException {
    int milisegundos = segundos*1000;
    Thread.sleep(milisegundos);
  }
}
