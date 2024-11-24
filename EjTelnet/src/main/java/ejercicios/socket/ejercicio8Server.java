import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ejercicio8Server {
    public static void main(String[] args) {
        int port = 12351;

        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Servidor UDP escuchando en el puerto " + port);

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                ByteArrayInputStream byteStream = new ByteArrayInputStream(packet.getData());
                ObjectInputStream objectInput = new ObjectInputStream(byteStream);
                Persona persona = (Persona) objectInput.readObject();

                System.out.println("Objeto recibido: " + persona);

                // Modifica los datos del objeto
                persona.setNombre(persona.getNombre().toUpperCase());
                persona.setEdad(persona.getEdad() + 10);

                ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOut = new ObjectOutputStream(byteOutStream);
                objectOut.writeObject(persona);
                byte[] responseData = byteOutStream.toByteArray();

                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, packet.getAddress(), packet.getPort());
                socket.send(responsePacket);
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
