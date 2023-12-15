import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class AccionCiudadano extends Main {
    public static void accederEspaiPersonal(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'person-space')])[1]//a"))).click();
    }
 
    public static void entrarApartadoNotificaciones() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class, 'options-desktop')])//p[2]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-notifications")));
    }
 
    public static void verNotificacionesEnotum() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'personal-space')]//div[2]//button"))).click();
       
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Assert.assertTrue(driver.getTitle().contains("e-NOTUM"));
    }

    public static String conseguirIdTramite() {
        String successText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='font s32 semi black mb-5 ng-star-inserted']"))).getText();
        String testID = successText.replaceAll("[^\\d{4}_\\d+]", "");
        
        return testID;
    }
}
