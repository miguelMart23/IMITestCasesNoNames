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
        wait = new WebDriverWait(driver, 2);
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
      OficinaVirtualActions.accederOficinaVirtual();
      
      // Paso 2
      OficinaVirtualActions.accederPaginaBusquedaTramites();

      // Paso 3
      OficinaVirtualActions.ordenarAlfabeticamente('A');

      
  }

   /**
    * IMI_TC002 – Buscar palabra clave portal
    * Validar que trás buscar una palabra clave de resultados
   */
  
   @Test(description = "IMI_TC002 – Buscar palabra clave portal", enabled = true)

   public void IMI_TC002() throws InterruptedException {
 
      // Paso 1
      OficinaVirtualActions.accederOficinaVirtual();

      
      // Paso 2
      OficinaVirtualActions.realizarBusquedaOficinaVirtual("venda");
  }


     /**
    * IMI_TC002 – Buscar palabra clave portal
    * Validar que trás buscar una palabra clave de resultados
   */
  
   @Test(description = "IMI_TC003", enabled = true)

   public void IMI_TC003() throws InterruptedException {
 
      // Paso 1
      TramitesActions.accederPaginaCrearTramite();
      
      // Paso 2
      TramitesActions.loginConCertificadoAOC();
      
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
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='CONS_NS.SI']"))).click();

      // Paso 13
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='continuar']']"))).click();
    
      // Paso 14
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(@for,'doc-file')]")));
      
      // Paso 15
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='doc-file']"))).click();
      
      // Paso 16
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='text_add']"))).sendKeys("titol prova");
    
      // Paso 17
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@value,'Adjuntar')]"))).click();
    
      // Paso 18
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'botonera')]//a[contains(@class,'enlaceboton')]"))).click();
    
      // Paso 19
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@value,'Enviar')]"))).click();
    
      // Paso 20
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='firmaNueva']"))).click();
        
      // Paso 21
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,\"btn-certificatDigital\")]"))).click();
        
      

    }


   /**
    * IMI_TC004_BT001 – Validar Búsqueda Portal
    * Validar que trás buscar
    * algo en la la barra de búsqueda se abra su pagina de descripción
   */
  
   @Test(description = "IMI_TC004_BT001 – Validar Búsqueda Portal", enabled = true)

   public void IMI_TC004_BT001() throws InterruptedException {
 
      // Paso 1
      OficinaVirtualActions.accederOficinaVirtual();
      
      // Paso 2
      OficinaVirtualActions.realizarBusquedaOficinaVirtual("Dret d'accés a la informació pública");

      // Paso 3
      OficinaVirtualActions.verificarBusquedaOficinaVirtual("Dret d'accés a la informació pública");
  }

  
  
}