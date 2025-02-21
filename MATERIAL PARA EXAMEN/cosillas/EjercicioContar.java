import java.io.*;

public class EjercicioContar {
    
    // Método para contar caracteres en un archivo (versión secuencial)
    public static int contarCaracteres(String archivo) {
        int contador = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            while (reader.read() != -1) {
                contador++;
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + archivo);
        }
        return contador;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java ContadorCaracteres <archivo1> <archivo2> ...");
            return;
        }

        // **Versión secuencial**
        System.out.println("Ejecutando en modo secuencial...");
        long t_inicio = System.currentTimeMillis();
        for (String archivo : args) {
            int caracteres = contarCaracteres(archivo);
            System.out.println("Archivo: " + archivo + " - Caracteres: " + caracteres);
        }
        long t_fin = System.currentTimeMillis();
        System.out.println("Tiempo total (secuencial): " + (t_fin - t_inicio) + " ms");

        // **Versión con hilos**
        System.out.println("\nEjecutando en modo multihilo...");
        t_inicio = System.currentTimeMillis();
        ContadorHilo[] hilos = new ContadorHilo[args.length];

        for (int i = 0; i < args.length; i++) {
            hilos[i] = new ContadorHilo(args[i]);
            hilos[i].start();
        }

        for (ContadorHilo hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        t_fin = System.currentTimeMillis();
        System.out.println("Tiempo total (multihilo): " + (t_fin - t_inicio) + " ms");
    }

    // **Clase interna para ejecutar cada conteo en un hilo**
    static class ContadorHilo extends Thread {
        private String archivo;

        public ContadorHilo(String archivo) {
            this.archivo = archivo;
        }

        public void run() {
            int caracteres = contarCaracteres(archivo);
            System.out.println("Archivo: " + archivo + " - Caracteres: " + caracteres);
        }
    }
}
