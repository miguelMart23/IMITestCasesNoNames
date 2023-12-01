import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbrirPopUpCertificado implements Runnable {
    private Wait<WebDriver> wait;

    public AbrirPopUpCertificado(WebDriver driver, Wait<WebDriver> wait) {
        this.wait = new WebDriverWait(driver, 20);
    }

    @Override
        public void run() 
        { 
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'btn-certificatDigital')]"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Continua']"))).click();
        }
}
