import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ejercicio5Server {
    public static void main(String[] args) {
        int port = 12348;
        int maxClients = 3; // Cambia este valor para permitir más clientes.

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor esperando conexiones en el puerto " + port);

            for (int i = 1; i <= maxClients; i++) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente " + i + " conectado desde " + clientSocket.getInetAddress());

                PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
                out.println("Eres el cliente número " + i);
                clientSocket.close();
            }

            System.out.println("Se alcanzó el límite de " + maxClients + " clientes.");
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
