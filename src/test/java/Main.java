import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Main {

  // Instanciar acciones/condiciones
  public static String testId;
  static WebDriver driver;
  static Wait<WebDriver> wait; 
  static TakesScreenshot scrshot;
  static File srcFile;
  static File destFile;
  private static String texto;

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
        wait = new WebDriverWait(driver, 60);
        
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

   * IMI_TC001 - Ordenar Alfabéticamente
   * Comprovar que tras darle
   * click a la letra "A" para
   * filtrar el contenido que se muestre empieze por la letra "A"
   */
  
   @Test(description = "IMI_TC001 - Ordenar Alfabéticamente", enabled = true)
   public void IMI_TC001() throws InterruptedException {

      // Paso 1
      AccionComun.accederOficinaVirtual();
      
      // Paso 2
      AccionComun.accederPaginaBusquedaTramites();

      // Paso 3
      AccionComun.ordenarAlfabeticamente('A');

      
  }

   /**
    * IMI_TC002 – Buscar palabra clave portal
    * Validar que trás buscar una palabra clave de resultados
   */
  
   @Test(description = "IMI_TC002 – Buscar palabra clave portal", enabled = true)

   public void IMI_TC002() throws InterruptedException {
 
      // Paso 1
      AccionComun.accederOficinaVirtual();

      
      // Paso 2
      AccionComun.realizarBusquedaOficinaVirtual("venda");
  }


     /**
    * IMI_TC003 – Buscar palabra clave portal
    * Validar que trás buscar una palabra clave de resultados
     * @throws IOException
   */
  
   @Test(description = "IMI_TC003", enabled = true)

   public void IMI_TC003() throws InterruptedException, IOException {
 
      // Paso 1
      AccionComun.accederPaginaCrearTramite();


      // Paso 2
      AccionComun.loginCertificado(true);
      
      // Paso 3
      AccionComun.rellenarFormularioAñadirTramite();

      // Paso 4
      AccionComun.adjuntarFicheroTramite();

      // Paso 5
      AccionComun.validarFormulario();

      // Paso 6
      AccionComun.firmarDigitalmente();

    }


   /**
    * IMI_TC004_BT001 – Validar Búsqueda Portal
    * Validar que trás buscar
    * algo en la la barra de búsqueda se abra su pagina de descripción
   */
  
   @Test(description = "IMI_TC004_BT001 – Validar Búsqueda Portal", enabled = true)

   public void IMI_TC004_BT001() throws InterruptedException {
 
      // Paso 1
      AccionComun.accederOficinaVirtual();
      
      // Paso 2
      AccionComun.realizarBusquedaOficinaVirtual("Dret d'accés");

      // Paso 3
      AccionComun.verificarBusquedaOficinaVirtual("Dret d'accés");
  }

  /**
    * IMI_TC005_BT002 – Abrir Trámite
    * Comprueba la acción de abrir trámite
   */
  @Test(description = "IMI_TC005_BT002 - Abrir Trámite", enabled = true)
  public void IMI_TC005_BT002() throws InterruptedException {
    // Precondición
    IMI_TC004_BT001();
   
    // Paso 1
    AccionComun.iniciarTramite();
 
    // Paso 2
    AccionComun.loginCertificado(true);
  }
 
  /**
    * IMI_TC006_BT003 – Trámite Dret Accés
    * Comprueba que se puede rellenar el trámite
   */
  @Test(description = "IMI_TC006_BT003 - Trámite Dret Accés", enabled = true)
  public void IMI_TC006_BT003() throws InterruptedException {
    // Precondición
    IMI_TC005_BT002();
   
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
  }
 
  /**
   * IMI_TC007_APOV001 - Validar Acceso Portal Ciudadanía
   * Comprobación de acceso al portal de la ciudadanía
   */
  @Test(description = "IMI_TC007_APOV001 - Validar Acceso Portal Ciudadanía", enabled = true)
  public void IMI_TC007_APOV001() throws InterruptedException {
    // Paso 1
    AccionComun.accederOficinaVirtual();
 
    // Paso 2
    AccionCiudadano.accederEspaiPersonal();
 
    // Paso 3
    AccionComun.loginCertificado(true);
  }
 
  /**
   * IMI_TC008_APOV002 - Notificacions  Enotum
   * Acceso de ciudadano con certificado a la oficina virtual a notificaciones
   */
  @Test(description = "IMI_TC008_APOV002 - Notificacions  Enotum", enabled = true)
  public void IMI_TC008_APOV002() throws InterruptedException {
    // Precondiciones
    IMI_TC007_APOV001();
   
    // Paso 1
    AccionCiudadano.entrarApartadoNotificaciones();
 
    // Paso 2
    AccionCiudadano.verNotificacionesEnotum();
  }
 
 
  

  /**
  * IMI_TC009 - Validar Modificar Datos Del Registro De Suscriptores
  * Valida que se puedan modificar los datos en el registro de Suscriptores
  */
 
  @Test(description = "IMI_TC009 - Validar Modificar Datos Del Registro De Suscriptores", enabled = true)
 
  public void IMI_TC009() throws InterruptedException {
 
    IMI_TC007_APOV001();
 
    Thread.sleep(2000);
 
    // Paso 1
 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class, 'options-desktop')])//p[2]")));
 
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner-file')]")));
 
    driver.findElement(By.xpath("(//div[contains(@class, 'options-desktop')])//p[2]")).click();
 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-notifications")));
 
    Thread.sleep(2000);
 
    // Paso 2
 
    Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'personal-space')]//div[3]//button")).isEnabled());
 
    driver.findElement(By.xpath("//div[contains(@class, 'personal-space')]//div[3]//button")).click();
 
    // Paso 3
 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@formcontrolname, 'telephone')]")));
 
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner-file')]")));
 
    driver.findElement(By.xpath("//input[contains(@formcontrolname, 'telephone')]")).sendKeys("123456789");
 
    Thread.sleep(2000);
 
    Assert.assertTrue(driver.findElement(By.xpath("//button[normalize-space()='Actualitzar dades']")).isEnabled());
 
    driver.findElement(By.xpath("//button[normalize-space()='Actualitzar dades']")).click();
 
    // Comprobacion
 
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='content-box-modal col-10 offset-1']//div[2]"))).click();
 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@formcontrolname, 'telephone')]")));
 
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner-file')]")));
 
    Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(), '123456789')]")).isDisplayed());
 
  }
 
  /**
  * IMI_TC010 - Consulta Estado Trámite Finalizado
  * Comprobación del estado de un trámite administrativo ya finalizado
  */
 
  /**
  @Test(description = "IMI_TC010 - Consulta Estado Trámite Finalizado", enabled = true)
 
  public void IMI_TC010() throws InterruptedException {
 
    IMI_TC007_APOV001();
 
    // Paso 1
 
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa bcn-icon-esquerra-bold']"))).click();
 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//section[contains(@class,\"check-tramit-status\")])[1]")));
 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='check-tramit-status ie10up']")));
 
    WebElement element = driver.findElement(By.xpath("//section[@class='check-tramit-status ie10up']"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
 
    Thread.sleep(2000);
 
    Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder='Introduïu el número']")).isDisplayed());
 
    driver.findElement(By.xpath("//input[@placeholder='Introduïu el número']")).sendKeys("504684-27");
 
    driver.findElement(By.xpath("//input[@placeholder='Introduïu el número']")).sendKeys(Keys.RETURN);
 
    // Paso 2
 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-tramit-status-detail")));
 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-tramit-status-detail//div[contains(@class,\"modal-subtitle\")]")));
 
    List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + "504684-27" + "')]"));
    Assert.assertTrue(list.size() > 0);
 
  }
  */
 
  /**
  * IMI_TC011 - Validar Acceso Portal Empresa
  * Comprobación de acceso al portal de la empresa
  */
 
  @Test(description = "IMI_TC011 - Validar Acceso Portal Empresa", enabled = true)
 
  public void IMI_TC011() throws InterruptedException {
 
    // Paso 1
    driver.get("https://seuelectronica-int.ajuntament.bcn/oficinavirtual/ca");
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'person-space')])[2]//a"))).click();
 
    // Paso 2
    AccionComun.loginCertificado(false);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='espai_personal']//li//a"))).click();
    Thread.sleep(10000);
 
  }
 
  /**
  * IMI_TC012 - Ver Detalle Tramite
  * Consulta del detalle de un trámite en el portal de la empresa
  */
 
  /**
  @Test(description = "IMI_TC012 - Ver Detalle Tramite", enabled = true)
 
  public void IMI_TC012() throws InterruptedException {
 
    IMI_TC011();
 
    // Paso 1
 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@class,'enterprise')][2]")));
 
    driver.findElement(By.xpath("//li[contains(@class,'enterprise')][2]")).click();
 
    // Paso 2
 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'509100-15')]")));
 
    driver.findElement(By.xpath("//p[contains(text(),'509100-15')]")).click();
 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button")));
 
    String winHandleBefore = driver.getWindowHandle();
 
    driver.findElement(By.xpath("//button")).click();
 
    for(String winHandle : driver.getWindowHandles()){
        driver.switchTo().window(winHandle);
    }
 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='contingut']")));
 
    driver.close();
 
    driver.switchTo().window(winHandleBefore);
 
  }
  */
  

  /**
  * IMI_TC013_A - Ver Detalle Tramite
  * Consulta del detalle de un trámite en el portal de la empresa
  */
  @Test(description = "IMI_TC0013_AEOV002 - Ver Información Fiscal", enabled = true)
  public void IMI_TC0013_AEOV002() throws InterruptedException {
    //navegar a virtual ofice companis
    IMI_TC011();
    //paso 1
    wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"profile-tab\"]")))).click();
    wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"profile\"]/app-enterprise-tax/div[2]/div/div[1]/div/div[1]")))).click();
    Thread.sleep(2000);
    //paso 2
 
    wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"ex1-tab-1\"]")))).click();
    driver.close();
 
 
 
 
  }

  /**
  * IMI_TC014 - Ver Detalle Tramite
  * Consulta del detalle de un trámite en el portal de la empresa
  */
  @Test(description = "IMI_TC0014_AEOV003 - Ver Información Fiscal Y Volver A Pagina Inicio", enabled = true)
  public void IMI_TC0014_AEOV003() throws InterruptedException {
    //navegar a virtual ofice companis
    IMI_TC011();
    //paso 1
    wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"profile-tab\"]")))).click();
    wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"profile\"]/app-enterprise-tax/div[2]/div/div[1]/div/div[1]")))).click();
    Thread.sleep(2000);
    //paso 2
 
    wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"ex1-tab-1\"]")))).click();
    wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[contains(@class,'espai_personal')]//a")))).click();
    Thread.sleep(2000);
    driver.close();
  }
 
  /**
  * IMI_TC015 - Ver Detalle Tramite
  * Consulta del detalle de un trámite en el portal de la empresa
  */
  @Test(description = "IMI_TC0014_AEOV003 - Ver Información Fiscal Y Volver A Pagina Inicio", enabled = true)
  public void IMI_TC0015_AEOV003() throws InterruptedException {

  }

  /**
  * IMI_TC016 - Ver Detalle Tramite
  * Consulta del detalle de un trámite en el portal de la empresa
  */
  @Test(description = "IMI_TC0014_AEOV003 - Ver Información Fiscal Y Volver A Pagina Inicio", enabled = true)
  public void IMI_TC0016_AEOV003() throws InterruptedException {

  }

  /**
   * IMI_TC017 - Sección Que Es Oficina Virtual
   * Comprobación de acceso a 
   * la sección de 
   * "qué es la oficina virtual"
   */
 
  @Test(description = "IMI_TC017_PS002 - Sección Que Es Oficina Virtual", enabled = true)
  public void IMI_TC017() throws InterruptedException, IOException {

    // Precondición

    AccionComun.accederOficinaVirtual();

    Thread.sleep(10000);

    // Accion
    
    driver.findElement(By.xpath("//*[@id='myTab']/div[2]/a[1]")).click();

    Thread.sleep(10000);

    // Condicion

    Assert.assertTrue(driver.findElements(By.xpath("//h3[text()=\"Què és l'Oficina Virtual?\"]")).size() > 0);

  }

  /**
   * IMI_TC018 - Búsqueda Produce Resultados
   * Comprobación de funcionamiento 
   * del buscador
   */
  @Test(description = "IMI_TC018_PS003 - Búsqueda Produce Resultados", enabled = true)

  public void IMI_TC018() throws InterruptedException, IOException {

    // Precondición

    AccionComun.accederOficinaVirtual();

    Thread.sleep(10000);

    // Accion

    AccionComun.enviarAlBuscador("//*[@id='mat-input-0']", "familia");

    Thread.sleep(1000);

    // Condicion

    Assert.assertTrue(driver.findElements(By.xpath("//*[@id=\"home\"]/div[2]/div/app-search-by-keyword/div/div[2]/div[1]")).size() > 0);

  }

  /**
   * IMI_TC019 - Pagina Citas
   * Comprobación de buscador 
   * acceso a la página de citas
   */
  @Test(description = "IMI_TC019_PS004 - Pagina Citas", enabled = true)

  public void IMI_TC019() throws InterruptedException, IOException {

    // Precondición

    AccionComun.accederOficinaVirtual();

    Thread.sleep(10000);

    // Accion

    AccionComun.enviarAlBuscador("//*[@id='mat-input-0']", "cita");

    Thread.sleep(1000);

    // Condicion

    Assert.assertTrue(driver.findElements(By.xpath("//*[@id=\"home\"]/div[2]/div/app-search-by-keyword/div/div[2]/div[1]")).size() > 0);

  }

  /**
   * IMI_TC020 - Consultar Información Citas E Iniciar Tramite
   * Comprobación de consulta de 
   * información e inicio de trámite
   */
  @Test(description = "IMI_TC020_PS005 - Consultar Información Citas E Iniciar Tramite", enabled = true)

  public void IMI_TC020() throws InterruptedException, IOException {

    // Precondición

    AccionComun.accederOficinaVirtual();

    Thread.sleep(10000);

    // Paso 1

    // Accion

    AccionComun.enviarAlBuscador("//*[@id='mat-input-0']", "cita");

    Thread.sleep(1000);

    // Condicion

    Assert.assertTrue(driver.findElements(By.xpath("//*[@id=\"home\"]/div[2]/div/app-search-by-keyword/div/div[2]/div[1]")).size() > 0);

    // Paso 2

    // Accion

    driver.findElement(By.xpath("//*[@id=\"home\"]/div[2]/div/app-search-by-keyword/div/div[2]/div[1]/div[1]")).click();

    Thread.sleep(2000);

    // Condicion

    Assert.assertTrue(driver.findElements(By.xpath("//*[@id='nav-3']/app-fitxa-general-description")).size() > 0);

    // Paso 3

    // Accion

    driver.findElement(By.xpath("//*[@id='nav-12-tab']")).click();

    Thread.sleep(1000);

    driver.findElement(By.xpath("//*[@id='nav-2-tab']")).click();

    Thread.sleep(1000);

    // Condicion

    Assert.assertTrue(driver.findElements(By.xpath("//*[@id='starter-buttons']/div/div/div/button")).size() > 0);

    // Paso 4

    // Accion

    driver.findElement(By.xpath("//*[@id='starter-buttons']/div/div/div/button")).click();

    Thread.sleep(1000);

    // Condicion

    Assert.assertTrue(driver.findElements(By.xpath("//*[@id='page']")).size() > 0);

  }

  /**
   * IMI_TC021 - Creación Borrador Derechos Acceso
   * Comprobación de consulta 
   * de información e inicio 
   * de trámite
   */
  @Test(description = "IMI_TC021_PS006 - Creación Borrador Derechos Acceso", enabled = true)

  public void IMI_TC021() throws InterruptedException, IOException {

    AccionComun.accederOficinaVirtualVPN();

    Thread.sleep(10000);

    // - Paso 1 
    
    // - Accion - User Search For "dret acces"

    AccionComun.enviarAlBuscador("//input[@name=\"searchWord\"]", "dret acces");
    
    // - Condicion - Results For "dret acces" Should Be Displayed
    
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,\"media-body\")])[1]")));
    
    List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + "dret acces" + "')]"));
    
    Assert.assertTrue(list.size() > 0);

    // - Paso 2

    // - Accion - User Clicks On First Panel Of Results
    
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'card-notification')])[1]")));

    driver.findElement(By.xpath("(//div[contains(@class,'card-notification')])[1]")).click();
    
    Thread.sleep(1000);
    
  
    // - Paso 3

    // Accion - User Initiates Process

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button)[3]")));

    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner-file')]")));

    Thread.sleep(1000);

    driver.findElement(By.xpath("(//button)[3]")).click();

    Thread.sleep(1000);


    // Paso 4

    // Accion - User Confirms Dialog

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'modal-dialog')])[3]")));

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'modal-dialog')]//button)[5]")));

    driver.findElement(By.xpath("(//div[contains(@class,'modal-dialog')]//button)[5]")).click();


    
    // Paso 5

    // Accion - User Sign In With Certificate
    

    Thread.sleep(1000);

    AccionComun.loginCertificado(true);



    // Condicion - Page Is "dades personals de la persona sol·licitant"

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'dates-title')]")));

    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner')]")));

    texto = driver.findElement(By.xpath("(//div[contains(@class,'dates-title')])[1]")).getText();

    Assert.assertTrue(texto.equals("dades personals de la persona sol·licitant"));


    // Paso 6

    // Accion - User Fills In First Page

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'dates-title')]")));

    driver.findElement(By.xpath("//input[contains(@formcontrolname,'email')]")).sendKeys("imi_certificats@bcn.cat");

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-wizard-buttons//input[1]")));

    driver.findElement(By.xpath("//app-wizard-buttons//input[1]")).click();

    // Condicion - Page Is "dades de la sol·licitud"

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'dates-title')]")));

    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner')]")));

    texto = driver.findElement(By.xpath("(//div[contains(@class,'dates-title')])[1]")).getText();

    Assert.assertTrue(texto.equals("dades de la sol·licitud"));


    // Paso 7

    // Accion - User Fills In Second Page

    driver.findElement(By.xpath("(//textarea)[1]")).sendKeys("info test");

    driver.findElement(By.xpath("(//textarea)[2]")).sendKeys("moti test");
    
    driver.findElement(By.xpath("(//textarea)[3]")).sendKeys("format test");
    

    // Paso 8

    // Accion - User Clicks Next

    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner')]")));

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-wizard-buttons//input[1]")));

    driver.findElement(By.xpath("//app-wizard-buttons//input[1]")).click();

    // Condicion - Page Is "autorització de consulta de dades"

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'dates-title')]")));

    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner')]")));

    texto = driver.findElement(By.xpath("(//div[contains(@class,'dates-title')])[1]")).getText();

    Assert.assertTrue(texto.equals("autorització de consulta de dades"));


    // Paso 9

    // Accion - User Attachs File "(//input[contains(@type,'file')])[1]"

    

    // Paso 10

    // Accion - User Clicks Next

    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner')]")));

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-wizard-buttons//input[1]")));

    driver.findElement(By.xpath("//app-wizard-buttons//input[1]")).click();

    // Condicion - Page Is "notificacions sol·licitant"

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'dates-title')]")));

    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner')]")));

    texto = driver.findElement(By.xpath("(//div[contains(@class,'dates-title')])[1]")).getText();

    Assert.assertTrue(texto.equals("notificacions sol·licitant"));


    // Paso 11

    // Accion - User Clicks Next

    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner')]")));

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-wizard-buttons//input[1]")));

    driver.findElement(By.xpath("//app-wizard-buttons//input[1]")).click();

    // Condicion - Page Is "Dades del sol·licitant"

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'dates-title')]")));

    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner')]")));

    texto = driver.findElement(By.xpath("(//div[contains(@class,'dates-title')])[1]")).getText();

    Assert.assertTrue(texto.equals("Dades del sol·licitant"));


    // Paso 12

    // Accion - User Checks Accept Privacy Policy

    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner')]")));

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@class='material-checkbox']")));

    driver.findElement(By.xpath("//label[@class='material-checkbox']")).click();


    // Paso 13

    // Accion - User Clicks Next

    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner')]")));

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-wizard-buttons//input[1]")));

    driver.findElement(By.xpath("//app-wizard-buttons//input[1]")).click();

    // Condicion - Page Is "document a signar"

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'dates-title')]")));

    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'bg-spinner')]")));

    texto = driver.findElement(By.xpath("(//div[contains(@class,'dates-title')])[1]")).getText();

    Assert.assertTrue(texto.equals("document a signar"));


    // Paso 14

    // Accion - User Saves Form

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//app-wizard-save-draft//button)[1]")));

    driver.findElement(By.xpath("(//app-wizard-save-draft//button)[1]")).click();

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'content-box-modal')]//button)[3]")));

    driver.findElement(By.xpath("(//div[contains(@class,'content-box-modal')]//button)[3]")).click();

    // Condicion - Form Is Saved Correctly

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'bcn-icon-ok-bold')]")));

  }

  /**
   * IMI_TC022_PS007 - Recuperar Borrador
   * Comprobación del estado de un trámite administrativo ya finalizado
   */
  @Test(description = "IMI_TC022_PS007 - Recuperar Borrador", enabled = true)
  public void IMI_TC022_PS007() throws InterruptedException {
    // Precondiciones
   
  }


}