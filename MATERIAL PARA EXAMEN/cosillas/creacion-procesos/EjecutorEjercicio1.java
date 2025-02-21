import java.util.ArrayList;
import java.util.List;

public class EjecutorEjercicio1 {
    public static void main(String[] args) {
        try {
            // Construir la lista de comandos: "java Ejercicio1" y los argumentos que se reciban.
            List<String> command = new ArrayList<>();
            command.add("java");
            command.add("Ejercicio1");
            for (String arg : args) {
                command.add(arg);
            }
            
            ProcessBuilder pb = new ProcessBuilder(command);
            Process process = pb.start();
            int exitCode = process.waitFor();
            
            System.out.println("El programa Ejercicio1 finalizó con código: " + exitCode);
            
            // Mostrar el mensaje según el código devuelto.
            switch (exitCode) {
                case 1:
                    System.out.println("El número de argumentos es menor que 1.");
                    break;
                case 2:
                    System.out.println("El argumento es una cadena.");
                    break;
                case 3:
                    System.out.println("El argumento es un número entero menor que 0.");
                    break;
                case 0:
                    System.out.println("El programa se ejecutó correctamente.");
                    break;
                default:
                    System.out.println("Código de salida desconocido.");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
