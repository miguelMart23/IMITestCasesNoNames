import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.text.html.parser.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

  // Instanciar acciones/condiciones
  public static String testId;
  WebDriver driver;
  Wait<WebDriver> wait;

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
    wait = new WebDriverWait(driver, 4);
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
  
   @Test(description = "IMI_TC001", enabled = true)
   public void IMI_TC001() throws InterruptedException {

      // Paso 1
      // Accion
      driver.get("https://seuelectronica.ajuntament.barcelona.cat/oficinavirtual/ca");
      Thread.sleep(1000);
      
      // Paso 2
      wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(@class,'font s16 regular white research-all')]")))).click();
      
      // Paso 3
      wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[text()='A']")))).click();
      // Asserts
      Assert.assertTrue(driver.findElement(By.xpath("//span[@id='A']")).isDisplayed());
      Thread.sleep(1000);
      
  }

   /**
    * IMI_TC002 – Buscar palabra clave portal
    * Validar que trás buscar una palabra clave de resultados
   */
  
   @Test(description = "IMI_TC002", enabled = true)

   public void IMI_TC002() throws InterruptedException {
 
      // Paso 1
      // Accion
      driver.get("https://seuelectronica.ajuntament.barcelona.cat/oficinavirtual/ca");
      Thread.sleep(1000);
      
      // Paso 2
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//input[@name=\"searchWord\"]")))).sendKeys("venda");
      // Asserts
      Assert.assertTrue(driver.findElements(By.xpath("//strong[text()='venda']")).isEmpty());
  }

  /**
    * IMI_TC004_BT001 – Validar Búsqueda Portal
    * Validar que trás buscar
    * algo en la la barra de búsqueda se abra su pagina de descripción
   */
  
   @Test(description = "IMI_TC004_BT001 – Validar Búsqueda Portal", enabled = true)

   public void IMI_TC004_BT001() throws InterruptedException {
 
      // Paso 1
      // Accion
      driver.get("https://seuelectronica.ajuntament.barcelona.cat/oficinavirtual/ca");
      Thread.sleep(1000);
      
      // Paso 2
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"searchWord\"]"))).sendKeys("Dret d'accés");

      // Paso 3
      // Accion
      wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@role=\"listbox\"]/mat-option[1]")))).click();
      // Asserts
      Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), \"Dret d'accés a la informació pública\")]"))).isDisplayed());

  }

}

