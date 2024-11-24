import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ejercicio6Server {
    public static void main(String[] args) {
        int port = 12349;

        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Servidor UDP escuchando en el puerto " + port);

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());

                if (message.equals("*")) {
                    System.out.println("Servidor finalizando...");
                    break;
                }

                System.out.println("Mensaje recibido: " + message);
                String response = message.toUpperCase();

                packet.setData(response.getBytes());
                socket.send(packet);
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
