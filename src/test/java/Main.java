import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
  private static Main imi_instance = null;

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
        wait = new WebDriverWait(driver, 30);
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

    // Paso 6
    AccionComun.aceptarSubmit();

    // Paso 7
    AccionComun.aceptarSubmit();

    // Paso 8
    AccionComun.aceptarSubmit();
  }
  
  /**
   * IMI_TC007_APOV001 - Validar Acceso Portal Ciudadanía
   */
  @Test(description = "IMI_TC007_APOV001 - Acceso Portal Ciudadanía", enabled = true)
  public void IMI_TC007_APOV001() throws InterruptedException {
    // Paso 1
    driver.get("https://seuelectronica.ajuntament.barcelona.cat/oficinavirtual/ca");
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'person-space')])[1]//a"))).click();

    // Paso 2
    AccionComun.loginCertificado(true);
  }
}