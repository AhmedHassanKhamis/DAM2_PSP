import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class EjecutorRepetidor {
    public static void main(String[] args) {
        // Leer la cadena desde el teclado.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce una cadena: ");
        String input = scanner.nextLine();
        scanner.close();
        
        try {
            // Ejecutar el programa RepetidorCadena pasando la cadena leída como argumento.
            ProcessBuilder pb = new ProcessBuilder("java", "RepetidorCadena", input);
            Process process = pb.start();
            
            // Leer y mostrar la salida del proceso.
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            int exitCode = process.waitFor();
            System.out.println("El programa RepetidorCadena finalizó con código: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
