import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class EjecutaPorFicheroAHK {
    public static void main(String[] args) {
        
        File directorioAHK = new File("./"); // O si está en el CLASSPATH (".\\bin");
        
        // Crear el proceso correctamente
        ProcessBuilder pbAHK = new ProcessBuilder("java", "PalindromoAHK");  // Debe encontrar la clase.
        pbAHK.directory(directorioAHK);
        
        // Redirección de archivos de entrada, salida y error
        File fEntradaAHK = new File("entrada.txt");
        pbAHK.redirectInput(fEntradaAHK);
        File fSalidaAHK = new File("salida.txt");
        pbAHK.redirectOutput(fSalidaAHK);
        File fErrAHK = new File("error.txt");
        pbAHK.redirectError(fErrAHK);

        try {
            // Se ejecuta el proceso
            Process pAHK = pbAHK.start();

            // Leer la salida estándar del proceso
            InputStream isAHK = pAHK.getInputStream();
            int cAHK;
            while ((cAHK = isAHK.read()) != -1) {
                System.out.print((char) cAHK);
            }
            isAHK.close();

            // Leer los errores del proceso (si los hay)
            InputStream erAHK = pAHK.getErrorStream();
            BufferedReader brerAHK = new BufferedReader(new InputStreamReader(erAHK));
            String linerAHK;
            while ((linerAHK = brerAHK.readLine()) != null) {
                System.out.println("ERROR >" + linerAHK);
            }
            brerAHK.close();

            // Comprobación de error - 0 bien - 1 mal
            int exitValAHK = pAHK.waitFor();
            System.out.println("Valor de Salida: " + exitValAHK);

        } catch (IOException | InterruptedException ioeAHK) {
            ioeAHK.printStackTrace();
        }
    }
}
