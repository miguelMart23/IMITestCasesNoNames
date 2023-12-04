import java.awt.*; 
import java.awt.event.KeyEvent;

public class EnviarCertificado implements Runnable {
    private boolean esCiudadano;

    /**
     * Se tiene que especificar en el mètodo run cuantas veces tiene que bajar el focus hasta cada certificado
     * @param esCiudadano
     */
    public EnviarCertificado(boolean esCiudadano) {
        this.esCiudadano = esCiudadano;
    }

    @Override
    public void run() { 
        try { 
            Thread.sleep(5000); 
            Robot robot = new Robot();

            // Aquí se pone cuantas veces tiene que bajar la flecha hasta llegar al certificado (1º ciudadano, 2º empresa)
            int pulsarFlecha = (esCiudadano) ? 2 : 3; 

            // Baja hasta que sea 
            for (int i = 0; i < pulsarFlecha; i++) {
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);
            }
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (InterruptedException | AWTException e) { 
            throw new RuntimeException(e); 
        } 
    } 
}
