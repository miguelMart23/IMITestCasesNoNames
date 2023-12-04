import org.testng.Assert;
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
        wait = new WebDriverWait(driver, 10);
        
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
    * IMI_TC002 – Buscar palabra clave portal
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

  
  
}