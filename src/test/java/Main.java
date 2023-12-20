
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

  // Instanciar accionescondiciónes
  public static String testId;
  static WebDriver driver;
  static Wait<WebDriver> wait;
  static TakesScreenshot scrshot;
  static File srcFile;
  static File destFile;

  private static Main home_instance = null;

  // Instanciar clases de test con patrón Singleton
  public static Main setInstance() {
    if (home_instance == null) {
      home_instance = new Main();
    }
    return home_instance;
  }

  @BeforeMethod
  public void setup_test() throws Exception {

    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("start-maximized");
    chromeOptions.addArguments("--log-level=1");
    driver = new ChromeDriver(chromeOptions);
    wait = new WebDriverWait(driver, 45);

  }

  @AfterMethod
  public void breakup_test() throws Exception {
    // Cerrar el navegador
    driver.quit();
  }

  /**************************************************
   * Pruebas
   **************************************************/

  /**
   * 
   * IMI_TC001 - Ordenar Alfabéticamente
   * Comprovar que tras darle
   * click a la letra "A" para
   * filtrar el contenido que se muestre empieze por la letra "A"
   * 
   * @throws IOException
   */

  @Test(description = "IMI_TC001 - Ordenar Alfabéticamente", enabled = true)
  public void IMI_TC001() throws InterruptedException, IOException {
    testId = "IMI_TC001";
    try {
      // Paso 1
      AccionComun.accederOficinaVirtual();
      Asercion.validarElemento("//a[contains(@class,'font s16 regular white research-all')]");

      // Paso 2
      String letra = "A";
      AccionComun.accederPaginaBusquedaTramites();
      Asercion.validarElemento("//h1[text()=' Cerqueu el que us interessa ']");

      // Paso 3
      AccionComun.ordenarAlfabeticamente(letra.charAt(0));
      Asercion.validarElemento("//span[@id='"+letra+"']");
      System.out.println("Test " + testId + "completado");

    } catch (Exception e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
      Assert.fail(errorCause);
    }

  }

  /**
   * IMI_TC002 – Buscar palabra clave portal
   * Validar que trás buscar una palabra clave de resultados
   * 
   * @throws IOException
   */

  @Test(description = "IMI_TC002 – Buscar palabra clave portal", enabled = true)

  public void IMI_TC002() throws InterruptedException, IOException {
    testId = "IMI_TC002";
    try {
      // Paso 1
      AccionComun.accederOficinaVirtual();
      Asercion.validarElemento("//p[text()=' Cerqueu el que us interessa ']");

      // Paso 2
      AccionComun.realizarBusquedaOficinaVirtual("venda");
      Asercion.validarElemento("//input[@name=\"searchWord\"]");

      System.out.println("Test " + testId + " completado");

    } catch (Exception e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");

      Assert.fail(errorCause);
    }
  }

  /**
   * IMI_TC003 – Buscar palabra clave portal
   * Validar que trás buscar una palabra clave de resultados
   * 
   * @throws IOException
   */

  @Test(description = "IMI_TC003", enabled = true)
  public void IMI_TC003() throws InterruptedException, IOException {
    testId = "IMI_TC003";
    try {
      // Paso 1
      AccionComun.accederPaginaCrearTramite();
      Asercion.validarTexto("//div[@class='box_id_header']/h2", "Identifiqueu-vos amb certificat digital");

      // Paso 2
      AccionComun.loginCertificado(true);
      Asercion.validarTexto("//span[@id='T00i_INT_capaTitulo1']/legend/span", "Dades de la persona sol·licitant");

      // Paso 3
      AccionComun.rellenarFormularioAñadirTramite();
      // Paso 4
      AccionComun.adjuntarFicheroTramite();
      Asercion.validarTexto("//h4/span[@class='font s18 semi' and text()='Dades de la persona sol·licitant  '][1]", "Dades de la persona sol·licitant");

      // Paso 5
      AccionComun.validarFormulario();
      Asercion.validarElemento("//a[@id='firmaNueva']");
      // Paso 6
      AccionComun.firmarDigitalmente();
      Asercion.validarElemento("//div[@class='descargapdf']");
      // Paso 7
      AccionComun.checkCorrectOperationNumber();
      Asercion.validarElemento("//div[@id='contingut'");


      System.out.println("Test " + testId + "completado");

    } catch (Exception e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
      Assert.fail(errorCause);
    }
  }

  /**
   * IMI_TC004_BT001 – Validar Búsqueda Portal
   * Validar que trás buscar
   * algo en la la barra de búsqueda se abra su pagina de descripción
   * 
   * @throws IOException
   */

  @Test(description = "IMI_TC004_BT001 - Validar Búsqueda Portal", enabled = true)
  public void IMI_TC004_BT001() throws InterruptedException, IOException {
    testId = "IMI_TC004_BT001";
    try {
      // Paso 1
      AccionComun.accederOficinaVirtual();
      Asercion.validarElemento("//input[@name='searchWord']");

      // Paso 2
      AccionComun.realizarBusquedaOficinaVirtual("Dret d'accés");
      Asercion.validarElemento("//div[@role=\"listbox\"]/mat-option[1]");

      // Paso 3
      String busqueda = "Dret d'accés";
      AccionComun.verificarBusquedaOficinaVirtual(busqueda);
      System.out.println("//app-fitxa-header//div[contains(text(),\""+busqueda+"\")]");
      Asercion.validarElemento("//app-fitxa-header//div[contains(text(),\""+busqueda+"\")]");
      System.out.println("Test " + testId + "completado");


    } catch (Exception e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
      Assert.fail(errorCause);
    }
  }
  
  /**
   * IMI_TC005_BT002 – Abrir Trámite
   * Comprueba la acción de abrir trámite
   * 
   * @throws IOException
   */
  @Test(description = "IMI_TC005_BT002 - Abrir Trámite", enabled = true)
  public void IMI_TC005_BT002() throws InterruptedException, IOException {
    try {
      // Precondición
      IMI_TC004_BT001();
      testId = "IMI_TC005_BT002";

      // Paso 1
      AccionComun.iniciarTramite();

      // Paso 2
      AccionComun.loginCertificado(true);

      System.out.println("Test " + testId + "completado");

    } 
    catch (TimeoutException | NoSuchElementException e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
    }
  }

  /**
   * IMI_TC006_BT003 – Trámite Dret Accés
   * Comprueba que se puede rellenar el trámite
   * 
   * @throws IOException
   */
  @Test(description = "IMI_TC006_BT003 - Trámite Dret Accés", enabled = true)
  public void IMI_TC006_BT003() throws InterruptedException, IOException {
    try {
      // Precondición
      IMI_TC005_BT002();
      testId = "IMI_TC006_BT003";

      // Paso 1
      AccionComun.anyadirParametros("email", "test@test.com");

      // Paso 2
      AccionComun.aceptarSubmit();

      // Paso 3
      AccionComun.rellenarTextArea(1, "test");

      // Paso 4
      AccionComun.aceptarSubmit();

      // Paso 5
      AccionComun.aceptarSubmit();

      // Paso 6
      AccionComun.aceptarSubmit();

      // Paso 7
      AccionComun.enviarTramite();

      // Paso 8
      AccionComun.guardarYCerrarTramite();
      
      System.out.println("Test " + testId + "completado");

    } catch (TimeoutException | NoSuchElementException e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
    }
  }

  /**
   * IMI_TC007_APOV001 - Validar Acceso Portal Ciudadanía
   * Comprobación de acceso al portal de la ciudadanía
   * @throws IOException
   */
  @Test(description = "IMI_TC007_APOV001 - Validar Acceso Portal Ciudadanía", enabled = true)
  public void IMI_TC007_APOV001() throws InterruptedException, IOException {
    testId = "IMI_TC007_APOV001";
    try {
      // Paso 1
      AccionComun.accederOficinaVirtualVPN();

      // Paso 2
      AccionCiudadano.accederEspaiPersonal();

      // Paso 3
      AccionComun.loginCertificado(true);

      System.out.println("Test " + testId + "completado");

    } catch (TimeoutException | NoSuchElementException e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
    }
  }

  /**
   * IMI_TC008_APOV002 - Notificacions Enotum
   * Acceso de ciudadano con certificado a la oficina virtual a notificaciones
   * @throws IOException
   */
  @Test(description = "IMI_TC008_APOV002 - Notificacions  Enotum", enabled = true)
  public void IMI_TC008_APOV002() throws InterruptedException, IOException {
    try {

      // Prcondiciónes
      IMI_TC007_APOV001();
      testId = "IMI_TC008_APOV002";

      // Paso 1
      AccionCiudadano.entrarApartadoNotificaciones();

      // Paso 2
      AccionCiudadano.verNotificacionesEnotum();

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");

    } catch (TimeoutException | NoSuchElementException e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
    }
  }

/**
  * IMI_TC009 - Validar Modificar Datos Del Registro De Suscriptores
  * Valida que se puedan modificar los datos en el registro de Suscriptores
  */

  @Test(description = "IMI_TC009 - Validar Modificar Datos Del Registro De Suscriptores", enabled = true)

  public void IMI_TC009() throws InterruptedException, IOException {
    try {

      // Precondición

      IMI_TC007_APOV001();

      testId = "IMI_TC009";

      Thread.sleep(2000);

      // Paso 1

      AccionComun.navegarAreaNotificaciones();

      // Paso 2

      AccionComun.navegarDatosSuscripcion();

      // Paso 3

      AccionComun.cambiarNumTelefono("123456789");

      // Comprobacion

      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='content-box-modal col-10 offset-1']//div[2]"))).click();

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@formcontrolname, 'telephone')]")));

      wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner-file')]")));

      Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(), '123456789')]")).isDisplayed());
    } 
    catch (Exception e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());
 
      AccionComun.takeScreenshoot(testId + " - " + errorCause);
 
      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
 
      Assert.fail(errorCause);
    } 
  }

  /**
  * IMI_TC010 - Consulta Estado Trámite Finalizado
  * Comprobación del estado de un trámite administrativo ya finalizado
  */

  @Test(description = "IMI_TC010 - Consulta Estado Trámite Finalizado", enabled = true)

  public void IMI_TC010() throws InterruptedException, IOException {
    try {

      IMI_TC007_APOV001();

      testId = "IMI_TC010";

      // Paso 1

      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa bcn-icon-esquerra-bold']"))).click();

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//section[contains(@class,\"check-tramit-status\")])[1]")));

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='check-tramit-status ie10up']")));

      WebElement element = driver.findElement(By.xpath("//section[@class='check-tramit-status ie10up']"));
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

      Thread.sleep(2000);

      Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder='Introduïu el número']")).isDisplayed());

      driver.findElement(By.xpath("//input[@placeholder='Introduïu el número']")).sendKeys("1689361-34");

      driver.findElement(By.xpath("//input[@placeholder='Introduïu el número']")).sendKeys(Keys.RETURN);

      // Paso 2

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-tramit-status-detail")));

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,\"modal-subtitle\")]")));

      List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + "1689361-34" + "')]"));
      Assert.assertTrue(list.size() > 0);
    }
    catch (Exception e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());
 
      AccionComun.takeScreenshoot(testId + " - " + errorCause);
 
      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
 
      Assert.fail(errorCause);
    }

  }

  /**
  * IMI_TC011 - Validar Acceso Portal Empresa
  * Comprobación de acceso al portal de la empresa
  */

  @Test(description = "IMI_TC011 - Validar Acceso Portal Empresa", enabled = true)

  public void IMI_TC011() throws InterruptedException, IOException {

    testId = "IMI_TC011";
    try {

      // Paso 1
      driver.get("https://seuelectronica-int.ajuntament.bcn/oficinavirtual/ca");
      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'person-space')])[2]//a"))).click();
    
      // Paso 2
      Thread.sleep(2000);
      AccionComun.loginCertificado(false);
      Thread.sleep(2000);
      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='espai_personal']//li//a"))).click();

      Thread.sleep(10000);
    }
    catch (Exception e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());
 
      AccionComun.takeScreenshoot(testId + " - " + errorCause);
 
      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
 
      Assert.fail(errorCause);
    }

  }

  /**
  * IMI_TC012 - Ver Detalle Tramite
  * Consulta del detalle de un trámite en el portal de la empresa
  */

  @Test(description = "IMI_TC012 - Ver Detalle Tramite", enabled = true)

  public void IMI_TC012() throws InterruptedException, IOException {

    testId = "IMI_TC011";
    try {

      // Precondición

      IMI_TC011();

      // Paso 1

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@class,'enterprise')][2]")));

      driver.findElement(By.xpath("//li[contains(@class,'enterprise')][2]")).click();

      // Paso 2

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'1689137-35')]")));

      driver.findElement(By.xpath("//p[contains(text(),'1689137-35')]")).click();

      String winHandleBefore = driver.getWindowHandle();

      Thread.sleep(5000);

      driver.findElement(By.xpath("//button[@class='col-10 col-sm-8 col-md-4 btn-generic primary mb-4 font s16']")).click();

      for(String winHandle : driver.getWindowHandles()){
          driver.switchTo().window(winHandle);
      }

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='contingut']")));

      driver.close();

      driver.switchTo().window(winHandleBefore);
    
    }
    catch (Exception e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());
 
      AccionComun.takeScreenshoot(testId + " - " + errorCause);
 
      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
 
      Assert.fail(errorCause);
    }

  }

  /**
   * IMI_TC013_A - Ver Detalle Tramite
   * Consulta del detalle de un trámite en el portal de la empresa
   * @throws IOException
   */
  @Test(description = "IMI_TC0013_AEOV002 - Ver Información Fiscal", enabled = true)
  public void IMI_TC0013_AEOV002() throws InterruptedException, IOException {
    try {
      //navegar a virtual ofice companis
      IMI_TC011();
      //paso 1
      wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"profile-tab\"]")))).click();
      wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"profile\"]/app-enterprise-tax/div[2]/div/div[1]/div/div[1]")))).click();
      Thread.sleep(2000);
      //paso 2
  
      wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"ex1-tab-1\"]")))).click();
    }
    catch (TimeoutException | NoSuchElementException e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
    }
  }

  /**
   * IMI_TC014 - Ver Detalle Tramite
   * Consulta del detalle de un trámite en el portal de la empresa
   * @throws IOException
   */
  @Test(description = "IMI_TC0014_AEOV003 - Comprovar Información Fiscal Y Volver A Pagina Inicio", enabled = true)
  public void IMI_TC0014_AEOV003() throws InterruptedException, IOException {
    try {
      //paso 1
      IMI_TC0013_AEOV002();
      String url = driver.getCurrentUrl();
      assert url.contains("enterprise-information/tax/vehicles");
      Thread.sleep(2000);
      wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/app-root/app-personal-space-layout/div/app-personal-space-header/app-corp-header/div[2]/div[2]/div[1]")))).click();
    }
    catch (TimeoutException | NoSuchElementException e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
    }
  }

  /**
   * IMI_TC015 - Validar Acceso Portal Profesional Autónomo
   * @throws IOException
   * 
   */
  @Test(description = "IMI_TC0015_AAOV001 - Validar Acceso Portal Profesional Autónomo", enabled = true)
  public void IMI_TC0015_AAOV001() throws InterruptedException, IOException {
    try {
      //paso 1
      IMI_TC0016_PS001();
      //paso 2
      wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"home\"]/app-person-space-info/section/div[3]/div[3]/div")))).click();
      AccionComun.loginCertificado(true);
      Thread.sleep(2000);
      driver.close();
    }
    catch (TimeoutException | NoSuchElementException e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
    }
  }

  /**
   * IMI_TC016 - Pagina por Defecto
   * @throws IOException
   * 
   */
  @Test(description = "IMI_TC0016_PS001 - Pagina por Defecto", enabled = true)
  public void IMI_TC0016_PS001() throws InterruptedException, IOException {
    try {
      //paso 1
      AccionComun.accederOficinaVirtualVPN();
      Thread.sleep(4000);
    }
    catch (TimeoutException | NoSuchElementException e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
    }
  }


  /**
   * IMI_TC017 - Sección Que Es Oficina Virtual
   * Comprobación de acceso a
   * la sección de
   * "qué es la oficina virtual"
   */
  @Test(description = "IMI_TC017_PS002 - Sección Que Es Oficina Virtual", enabled = true)
  public void IMI_TC017() throws InterruptedException, IOException {
    testId = "IMI_TC017";
    try {
      // Precondición
      AccionComun.accederOficinaVirtual();
      AccionComun.esperarSegundos(10);

      // Acción
      AccionComun.clickarElemento("//*[@id='myTab']/div[2]/a[1]");
      AccionComun.esperarSegundos(10);

      // Condición
      Asercion.validarElemento("//h3[text()=\"Què és l'Oficina Virtual?\"]");

      System.out.println("Test " + testId + "completado");

    } catch (TimeoutException | NoSuchElementException e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
    }
  }

  /**
   * IMI_TC018 - Búsqueda Produce Resultados
   * Comprobación de funcionamiento
   * del buscador
   */
  @Test(description = "IMI_TC018_PS003 - Búsqueda Produce Resultados", enabled = true)
  public void IMI_TC018() throws InterruptedException, IOException {
    testId = "IMI_TC018";
    try {
      // Precondición
      AccionComun.accederOficinaVirtual();
      AccionComun.esperarSegundos(10);

      // Acción
      AccionComun.enviarTexto("//*[@id='mat-input-0']", "familia");
      AccionComun.pulsarEnter("//*[@id='mat-input-0']");
      AccionComun.esperarSegundos(1);

      // Condición
      Asercion.validarElemento("//*[@id=\"home\"]/div[2]/div/app-search-by-keyword/div/div[2]/div[1]");

      System.out.println("Test " + testId + "completado");

    } catch (TimeoutException | NoSuchElementException e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");

    }
  }

  /**
   * IMI_TC019 - Pagina Citas
   * Comprobación de buscador
   * acceso a la página de citas
   */
  @Test(description = "IMI_TC019_PS004 - Pagina Citas", enabled = true)
  public void IMI_TC019() throws InterruptedException, IOException {
    testId = "IMI_TC019";
    try {
      // Precondición
      AccionComun.accederOficinaVirtual();
      AccionComun.esperarSegundos(10);

      // Acción
      AccionComun.enviarTexto("//*[@id='mat-input-0']", "cita");
      AccionComun.pulsarEnter("//*[@id='mat-input-0']");
      AccionComun.esperarSegundos(1);

      // Condición
      Asercion.validarElemento("//*[@id=\"home\"]/div[2]/div/app-search-by-keyword/div/div[2]/div[1]");

      System.out.println("Test " + testId + "completado");

    } catch (TimeoutException | NoSuchElementException e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
    }
  }

  /**
   * IMI_TC020 - Consultar Información Citas E Iniciar Tramite
   * Comprobación de consulta de
   * información e inicio de trámite
   */
  @Test(description = "IMI_TC020_PS005 - Consultar Información Citas E Iniciar Tramite", enabled = true)
  public void IMI_TC020() throws InterruptedException, IOException {
    testId = "IMI_TC020";
    try {
      // Precondición
      AccionComun.accederOficinaVirtual();
      AccionComun.esperarSegundos(10);

      // Paso 1
      // Accion
      AccionComun.enviarTexto("//*[@id='mat-input-0']", "cita");
      AccionComun.pulsarEnter("//*[@id='mat-input-0']");
      AccionComun.esperarSegundos(1);

      // Condición
      Asercion.validarElemento("//*[@id=\"home\"]/div[2]/div/app-search-by-keyword/div/div[2]/div[1]");

      // Paso 2
      // Acción
      AccionComun.clickarElemento("//*[@id=\"home\"]/div[2]/div/app-search-by-keyword/div/div[2]/div[1]/div[1]");
      AccionComun.esperarSegundos(2);

      // Condición
      Asercion.validarElemento("//*[@id='nav-3']/app-fitxa-general-description");

      // Paso 3
      // Acción
      AccionComun.clickarElemento("//*[@id='nav-12-tab']");
      AccionComun.esperarSegundos(1);

      AccionComun.clickarElemento("//*[@id='nav-2-tab']");
      AccionComun.esperarSegundos(1);

      // Condición
      Asercion.validarElemento("//*[@id='starter-buttons']/div/div/div/button");

      // Paso 4
      // Acción
      AccionComun.clickarElemento("//*[@id='starter-buttons']/div/div/div/button");
      AccionComun.esperarSegundos(1);

      // Condición
      Asercion.validarElemento("//*[@id='page']");

      System.out.println("Test " + testId + "completado");

    } catch (TimeoutException | NoSuchElementException e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
    }
  }

  /**
   * IMI_TC021 - Creación Borrador Derechos Acceso
   * Comprobación de consulta
   * de información e inicio
   * de trámite
   */
  @Test(description = "IMI_TC021_PS006 - Creación Borrador Derechos Acceso", enabled = true)
  public void IMI_TC021() throws InterruptedException, IOException {
    testId = "IMI_TC021";
    try {
      AccionComun.accederOficinaVirtualVPN();
      AccionComun.esperarSegundos(10);

      // - Paso 1
      // - Acción - User Search For "dret acces"
      AccionComun.enviarTexto("//input[@name=\"searchWord\"]", "dret acces");
      AccionComun.pulsarEnter("//input[@name=\"searchWord\"]");

      // - Condición - Results For "dret acces" Should Be Displayed
      Asercion.validarElemento("(//div[contains(@class,\"media-body\")])[1]");

      List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + "dret acces" + "')]"));
      Assert.assertTrue(list.size() > 0);

      // - Paso 2
      // - Acción - User Clicks On First Panel Of Results
      Asercion.validarElemento("(//div[contains(@class,'card-notification')])[1]");

      AccionComun.clickarElemento("(//div[contains(@class,'card-notification')])[1]");

      AccionComun.esperarSegundos(1);

      // Condición
      Asercion.validarElemento("//*[@id='page']");   //////////////////////////////// PROBAR ESTO

      // - Paso 3
      // Acción - User Initiates Process
      Asercion.validarElemento("(//button)[3]");

      Asercion.validarElementoInvisible("//div[contains(@class, 'bg-spinner-file')]");
      AccionComun.esperarSegundos(1);

      AccionComun.clickarElemento("(//button)[3]");
      AccionComun.esperarSegundos(1);

      // Condición
      Asercion.validarElemento("//*[@id='page']");  //////////////////////////////// PROBAR ESTO

      // Paso 4
      // Acción - User Confirms Dialog
      Asercion.validarElemento("(//div[contains(@class,'modal-dialog')])[3]");

      Asercion.validarElementoInvisible("(//div[contains(@class,'modal-dialog')]//button)[5]");

      AccionComun.clickarElemento("(//div[contains(@class,'modal-dialog')]//button)[5]");

      // Condición
      Asercion.validarElemento("//*[@id='page']");  //////////////////////////////// PROBAR ESTO

      // Paso 5
      // Acción - User Sign In With Certificate
      AccionComun.esperarSegundos(1);
      AccionComun.loginCertificado(true);

      // Condición - Page Is "dades personals de la persona sol·licitant"
      Asercion.validarPagina("//*[@id=\"tramitForm\"]/div/app-personal-form/div/div[1]/span", "DADES DE LA PERSONA SOL·LICITANT");

      // Paso 6
      // Acción - User Fills In First Page

      Asercion.validarElemento("//*[@id=\"tramitForm\"]/div/app-personal-form/div/div[1]");

      AccionComun.enviarTexto("//input[contains(@formcontrolname,'email')]", "imi_certificats@bcn.cat");

      AccionComun.aceptarSubmit();
      AccionComun.esperarSegundos(5);

      // Condición - Page Is "dades de la sol·licitud"
      Asercion.validarPagina("//*[@id=\"dataForm\"]/div/div[1]/span", "DADES DE LA SOL·LICITUD");

      // Paso 7
      // Acción - User Fills In Second Page
      AccionComun.enviarTexto("(//textarea)[1]", "info test");
      AccionComun.enviarTexto("(//textarea)[2]", "moti test");
      AccionComun.enviarTexto("(//textarea)[3]", "format test");

      // Condición
      Asercion.validarElemento("//*[@id='page']");  //////////////////////////////// PROBAR ESTO

      // Paso 8
      // Acción - User Clicks Next
      Asercion.validarElementoInvisible("//div[contains(@class, 'bg-spinner')]");
      AccionComun.aceptarSubmit();

      // Condición - Page Is "autorització de consulta de dades"
      Asercion.validarPagina("//*[@id=\"fileForm\"]/div[1]/div[1]/span", "AUTORITZACIÓ DE CONSULTA DE DADES");

      // Paso 9
      // Acción - User Attachs File
      File file = new File("./src/test/resources/prueba.pdf");
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[contains(@type,'file')])[1]"))).sendKeys(file.getAbsolutePath());
      Asercion.validarElemento("//*[text()=\" D'ACORD \"]");
      AccionComun.clickarElemento("//*[text()=\" D'ACORD \"]");

      // Condición
      Asercion.validarElemento("//*[@id='page']");  //////////////////////////////// PROBAR ESTO

      // Paso 10
      // Acción - User Clicks Next
    /*
     * No existe ninguna pagina de notificacions sol·licitant
     * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.
     * xpath("//div[contains(@class, 'bg-spinner')]")));
     * 
     * AccionComun.aceptarSubmit();
     * 
     * //Condición - Page Is "notificacions sol·licitant"
     * 
     * AccionComun.pageIs("(//div[contains(@class,'dates-title')])[1]",
     * "notificacions sol·licitant");
     */

      // Paso 11
      // Acción - User Clicks Next
      Asercion.validarElementoInvisible("//div[contains(@class, 'bg-spinner')]");
      AccionComun.aceptarSubmit();

      // Condición - Page Is "Dades del sol·licitant"
      Asercion.validarPagina("//*[@id=\"tramitForm\"]/div[2]/div/span", "DADES DE LA PERSONA SOL·LICITANT");

      // Paso 12
      // Acción - User Checks Accept Privacy Policy

    /*
     * No aparece ningún elemento al cual aceptar la politica de privacidad
     * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.
     * xpath("//div[contains(@class, 'bg-spinner')]")));
     * 
     * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
     * "//label[@class='material-checkbox']")));
     * 
     * driver.findElement(By.xpath("//label[@class='material-checkbox']")).click();
     */

      // Paso 13
      // Acción - User Clicks Next
      Asercion.validarElementoInvisible("//div[contains(@class, 'bg-spinner')]");
      AccionComun.aceptarSubmit();

      // Condición - Page Is "document a signar"
      Asercion.validarPagina("//*[@id=\"tramitForm\"]/div[2]/div[1]/span", "DESCARREGUEU EL VOSTRE DOCUMENT");

      // Paso 14
      // Acción - User Saves Form
      Asercion.validarElemento("//app-wizard-save-draft/div[1]/div");

      AccionComun.clickarElemento("//app-wizard-save-draft/div[1]/div");

      Asercion.validarElemento("//*[text()=\" D'acord \"]");
      AccionComun.clickarElemento("//*[text()=\" D'acord \"]");

    /*
     * Código de robot que no concuerda con ninguna acción posible
     * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
     * "(//div[contains(@class,'content-box-modal')]//button)[3]")));
     * 
     * driver.findElement(By.xpath(
     * "(//div[contains(@class,'content-box-modal')]//button)[3]")).click();
     */

      // Condición - Form Is Saved Correctly
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'bcn-icon-ok-bold')]")));

      System.out.println("Test " + testId + "completado");

    } catch (TimeoutException | NoSuchElementException e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
    }
  }

  /**
   * IMI_TC022_PS007 - Recuperar Borrador
   * Comprobación del estado de un trámite administrativo ya finalizado
   */
  @Test(description = "IMI_TC022_PS007 - Recuperar Borrador", enabled = true)
  public void IMI_TC022_PS007() throws InterruptedException, IOException {
    try {
      // Prcondiciónes
      IMI_TC021();
      testId = "IMI_TC022_PS007";

      // Paso 1
      String tramiteId = AccionCiudadano.conseguirIdTramite();
      AccionComun.guardarYCerrarTramite();

      // Paso 2
      AccionComun.recuperarTramitePorId(tramiteId);

      // Paso 3
      // Condición - Page Is "dades personals de la persona sol·licitant"
      AccionComun.validarTextoFormulario("Dades de la persona sol·licitant");
      AccionComun.aceptarSubmit();

      // Paso 4
      //Condición - Page Is "dades de la sol·licitud"
      Thread.sleep(5000);
      AccionComun.validarTextoFormulario("Dades del tràmit");
      AccionComun.aceptarSubmit();

      // Paso 5
      //Condición - Page Is "autorització de consulta de dades"
      Thread.sleep(5000);
      AccionComun.validarTextoFormulario("Documentació");
      AccionComun.aceptarSubmit();

      // Paso 6
      //Condición - Page Is "autorització de consulta de dades"
      Thread.sleep(5000);
      AccionComun.validarTextoFormulario("Resum");
      AccionComun.aceptarSubmit();

      // Paso 7
      //Condición - Page is "decarregeu el vostre document"
      AccionComun.validarTextoFormulario("Enviament (registre/signatura)");
      AccionComun.enviarTramite();

      // Paso 8
      Thread.sleep(30000);
      AccionComun.guardarYCerrarTramite();

      System.out.println("Test " + testId + "completado");

    } catch (TimeoutException | NoSuchElementException e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());

      AccionComun.takeScreenshoot(testId + " - " + errorCause);

      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
    }
  }

/**
  * IMI_TC023 - Trámite Volante De Residencia
  * Comprobación de trámite de Volante de Residencia
  */

  @Test(description = "IMI_TC023 - Trámite Volante De Residencia", enabled = true)

  public void IMI_TC023() throws InterruptedException, IOException {
    try {

      // Precondición

      IMI_TC007_APOV001();
      testId = "IMI_TC023";

      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='virtual-ofice vertical-align-c']//p[@class='font s18 black regular'][normalize-space()='Oficina Virtual de Tràmits']"))).click();

      // Paso 1

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-root")));

      Assert.assertTrue(driver.findElement(By.xpath("//input[@name=\"searchWord\"]")).isDisplayed());

      driver.findElement(By.xpath("//input[@name=\"searchWord\"]")).sendKeys("volant");

      driver.findElement(By.xpath("//input[@name=\"searchWord\"]")).sendKeys(Keys.RETURN);

      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,\"media-body\")])[1]")));
    
      List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + "volant" + "')]"));
      Assert.assertTrue(list.size() > 0);

      // Paso 2

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-max']//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//p[1]"))).click();

      // Paso 3

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='buttons-header both-buttons']//div[contains(text(),'Inicia el tràmit')]")));

      wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner-file')]")));

      driver.findElement(By.xpath("//div[@class='buttons-header both-buttons']//div[contains(text(),'Inicia el tràmit')]")).click();

      Thread.sleep(2000);

      // Paso 4

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='botonNext']"))).click();

      Assert.assertTrue(driver.findElement(By.xpath("//button[@id='botonNext']")).isDisplayed());

      Thread.sleep(10000);

      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("window.scrollBy(0,1500)", "");

      Thread.sleep(2000);

      driver.findElement(By.xpath("/html/body/app-root/app-personal-space-layout/div/app-residence-certificate/app-wizard-step/div/div/div/div/form/section[4]/div/div/div/div/div[2]/div[3]/div[2]/label")).click();  

      driver.findElement(By.xpath("//*[@id=\"dataForm\"]/app-wizard-buttons/main/footer/div/div/div[2]/div/div[2]/button")).click();   

      // Paso 5

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='m-0 p-0 finalStep-documentText']")));

      Assert.assertTrue(driver.findElement(By.xpath("//div[@class='virtual-ofice vertical-align-c']//a")).isDisplayed());

      driver.findElement(By.xpath("//div[@class='virtual-ofice vertical-align-c']//a")).click();

      // Paso 6

      Thread.sleep(2000);

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-root")));

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"home-tab\"]")));

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tabMainContent\"]")));

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"searchWord\"]")));
    }
    catch (Exception e) {
      String errorCause = "" + e.getClass();
      errorCause = errorCause.substring(errorCause.lastIndexOf(".") + 1, errorCause.length());
 
      AccionComun.takeScreenshoot(testId + " - " + errorCause);
 
      System.out.println("Fallo en el test " + testId + " (se ha guardado una captura de pantalla en la carpeta sreenshots)");
 
      Assert.fail(errorCause);
    }

  }
}
