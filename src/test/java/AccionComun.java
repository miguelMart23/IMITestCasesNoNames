import org.openqa.selenium.By;
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
        Assert.assertTrue(driver.findElements(By.xpath("//strong[text()=\"" + busqueda + "\"]")).isEmpty());

    }

    public static void verificarBusquedaOficinaVirtual(String busqueda) {
      // Accion
      wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@role=\"listbox\"]/mat-option[1]")))).click();
    }

    public static void ordenarAlfabeticamente(char letra) throws InterruptedException {
       wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[text()='" + letra + "']")))).click();
      // Asserts
      Assert.assertTrue(driver.findElement(By.xpath("//span[@id='"+letra+"']")).isDisplayed());
      Thread.sleep(1000);
    
    }

    public static void accederPaginaCrearTramite() throws InterruptedException {
      // Accion
      driver.get("https://seuelectronica-int.ajuntament.barcelona.cat/APPS/ptbportal/login.do?language=ca&style=ciutada&origen=portal_tramits&loginTarget=T400i&iniciar=");
      Thread.sleep(1000);
  }

  public static void iniciarTramite() {
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(text(), 'Inicia el tràmit')])[1]"))).click();

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'btn-generic primary font s16 none')]"))).click();
  }

  public static void anyadirParametros(String parametro, String valor) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname=\"" + parametro + "\"]"))).sendKeys(valor);
  }

  public static void aceptarSubmit() {
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

}
