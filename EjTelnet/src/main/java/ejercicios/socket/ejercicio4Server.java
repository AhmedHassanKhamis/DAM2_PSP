import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ejercicio4Server {
    public static void main(String[] args) {
        int port = 12347;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor esperando conexiones en el puerto " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

                String number = in.readLine();
                try {
                    int num = Integer.parseInt(number);
                    int square = num * num;
                    out.println("El cuadrado de " + num + " es " + square);
                } catch (NumberFormatException e) {
                    out.println("Error: Por favor, envía un número entero.");
                }

                clientSocket.close();
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
