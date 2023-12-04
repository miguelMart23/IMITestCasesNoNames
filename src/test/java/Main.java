
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Main {

  // Instanciar acciones/condiciones
  public static String testId;
  static WebDriver driver;
  static Wait<WebDriver> wait; 
  private static Main imi_instance = null;
  
  static TakesScreenshot scrshot;
  static File srcFile;
  static File destFile;

  // Instanciar clases de test con patrón Singleton
  public static Main setInstance() {
    if (imi_instance == null) {
      imi_instance = new Main();
    }
    return imi_instance;
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
    * IMI_TC004_BT001 – Validar Búsqueda Portal
    * Validar que trás buscar
    * algo en la la barra de búsqueda se abra su pagina de descripción
   */
   @Test(description = "IMI_TC004_BT001 - Validar Búsqueda Portal", enabled = true)
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
   * IMI_TC022_PS007 - Recuperar Borrador
   * Comprobación del estado de un trámite administrativo ya finalizado
   */
  @Test(description = "IMI_TC022_PS007 - Recuperar Borrador", enabled = true)
  public void IMI_TC022_PS007() throws InterruptedException {
    // Precondiciones
    
  }
}