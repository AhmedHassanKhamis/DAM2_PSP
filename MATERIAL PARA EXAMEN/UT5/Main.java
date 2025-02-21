import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyHilo hilo = new MyHilo();

        // Lanzar el hilo por primera vez
        System.out.println("Introduzca una cadena: S (Suspender), R (Reanudar), * (Finalizar)");
        String input = scanner.nextLine();
        
        if (input.equalsIgnoreCase("S") || input.equalsIgnoreCase("R")) {
            hilo.start(); // Solo se lanza el hilo una vez, después de la primera entrada
        }

        // Proceso repetitivo hasta introducir un '*'
        while (!input.equals("*")) {
            System.out.println("Introduzca una cadena: S (Suspender), R (Reanudar), * (Finalizar)");
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("S")) {
                hilo.suspende();
            } else if (input.equalsIgnoreCase("R")) {
                hilo.reanuda();
            }
        }

        // Finaliza el hilo y muestra el valor del contador
        hilo.detener();  // Detiene el bucle del hilo
        try {
            hilo.join();  // Espera que el hilo termine antes de continuar
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final del contador: " + hilo.getContador());
        System.out.println("El hilo ha finalizado.");
        scanner.close();
    }
}
