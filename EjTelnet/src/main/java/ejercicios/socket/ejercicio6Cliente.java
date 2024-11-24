import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class ejercicio6Cliente {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 12349;

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(5000);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("Escribe un mensaje (* para salir):");
                String message = userInput.readLine();

                byte[] buffer = message.getBytes();
                InetAddress serverInet = InetAddress.getByName(serverAddress);
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverInet, port);
                socket.send(packet);

                if (message.equals("*")) break;

                packet.setData(new byte[1024]);
                try {
                    socket.receive(packet);
                    String response = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("Respuesta del servidor: " + response);
                } catch (SocketTimeoutException e) {
                    System.out.println("Tiempo de espera agotado. El paquete se perdió.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}
