import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ejercicio2Server {
    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor TCP iniciado en el puerto " + port);
            
            for (int i = 1; i <= 2; i++) {
                System.out.println("Esperando cliente " + i + "...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente " + i + " conectado:");
                System.out.println(" - Puerto local: " + clientSocket.getLocalPort());
                System.out.println(" - Puerto remoto: " + clientSocket.getPort());
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
