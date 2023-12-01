import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TramitesActions extends Main {

    public static void accederPaginaCrearTramite() throws InterruptedException {
        // Accion
        driver.get("https://seuelectronica-int.ajuntament.barcelona.cat/APPS/ptbportal/login.do?language=ca&style=ciutada&origen=portal_tramits&loginTarget=T400i&iniciar=");
        Thread.sleep(1000);
    }

    public static void loginConCertificadoAOC() {
      // Accion
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//button[contains(@class,\"btn-certificatDigital\")]")))).click();
        
      try {
        // Crea las clases y las convierten en hilos
        AbrirPopUpCertificado abrirPopUpCertificado = new AbrirPopUpCertificado(driver, wait);
        Thread hiloAbrirPopUpCertificado = new Thread(abrirPopUpCertificado);
        EnviarCertificado enviarCertificado = new EnviarCertificado(true);
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
