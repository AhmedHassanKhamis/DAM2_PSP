import java.io.IOException;
import java.net.Socket;

public class ejercicio2Cliente {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Conectado al servidor:");
            System.out.println(" - Dirección remota: " + socket.getInetAddress().getHostAddress());
            System.out.println(" - Puerto remoto: " + socket.getPort());
            System.out.println(" - Puerto local: " + socket.getLocalPort());
        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}

