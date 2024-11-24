import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ejercicio4Cliente{
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 12347;

        try (Socket socket = new Socket(serverAddress, port)) {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            System.out.println("Introduce un número entero:");
            String number = userInput.readLine();

            out.println(number);
            String response = in.readLine();
            System.out.println("Respuesta del servidor: " + response);
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}
