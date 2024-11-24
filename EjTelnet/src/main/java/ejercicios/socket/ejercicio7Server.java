import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ejercicio7Server {
    public static void main(String[] args) {
        int port = 12350;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor esperando conexiones en el puerto " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

                Numeros numeros = (Numeros) in.readObject();

                if (numeros.getNumero() <= 0) {
                    System.out.println("Servidor finalizando...");
                    break;
                }

                int numero = numeros.getNumero();
                numeros.setCuadrado((long) numero * numero);
                numeros.setCubo((long) numero * numero * numero);

                System.out.println("Número recibido: " + numero);
                System.out.println("Cuadrado: " + numeros.getCuadrado());
                System.out.println("Cubo: " + numeros.getCubo());

                out.writeObject(numeros);
                clientSocket.close();
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
