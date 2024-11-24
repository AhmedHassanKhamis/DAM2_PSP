import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ejercicio7Cliente {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 12350;

        try (Socket socket = new Socket(serverAddress, port)) {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Introduce un número entero (<= 0 para salir):");
                int numero = scanner.nextInt();

                Numeros numeros = new Numeros(numero);
                out.writeObject(numeros);

                if (numero <= 0) break;

                numeros = (Numeros) in.readObject();
                System.out.println("Respuesta del servidor:");
                System.out.println("Cuadrado: " + numeros.getCuadrado());
                System.out.println("Cubo: " + numeros.getCubo());
            }
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}
