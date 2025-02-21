import java.util.Scanner;

public class Ejercicio1A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String linea;

        while (true) {
            linea = scanner.nextLine();
            if (linea.equals("*")) {
                break;
            }
            System.out.println("Entrada: " + linea);
        }

        System.out.println("Finalizando LectorEntrada...");
        scanner.close();
    }
}
