import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ejercicio3Cliente {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 12346;

        try (Socket socket = new Socket(serverAddress, port)) {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            System.out.println("Escribe un mensaje para enviarlo al servidor:");
            String message = userInput.readLine();

            out.println(message);
            String response = in.readLine();

            System.out.println("Respuesta del servidor: " + response);
        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}
