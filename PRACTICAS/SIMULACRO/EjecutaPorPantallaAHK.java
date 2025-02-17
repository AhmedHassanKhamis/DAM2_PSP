import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class EjecutaPorPantallaAHK {
    public static void main(String[] args) {
        
        File directorioAHK = new File("./"); // O si está en el CLASSPATH (".\\bin");
        
        // Crear el proceso correctamente
        ProcessBuilder pbAHK = new ProcessBuilder("java", "PalindromoAHK");  // Debe encontrar la clase.
        pbAHK.directory(directorioAHK);
        pbAHK.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        File fErrAHK = new File("error.txt");
        pbAHK.redirectError(fErrAHK);

        try {
            // Se ejecuta el proceso
            Process pAHK = pbAHK.start();

            // Enviar entrada al proceso a través de su OutputStream
            OutputStream osAHK = pAHK.getOutputStream();
            osAHK.write("ana".getBytes());  // Enviar texto "ana"
            osAHK.flush(); // vacía el buffer de salida
            osAHK.close(); // Cerrar el OutputStream

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
            
            // Esperar a que el proceso termine y obtener el código de salida
            int exitCodeAHK = pAHK.waitFor();
            System.out.println("\nProcess exited with code: " + exitCodeAHK);

        } catch (IOException | InterruptedException ioeAHK) {
            ioeAHK.printStackTrace();
        }
    }
}
