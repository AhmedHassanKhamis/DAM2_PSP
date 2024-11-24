import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ejercicio8Cliente {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 12351;

        try (DatagramSocket socket = new DatagramSocket()) {
            Persona persona = new Persona("Juan", 25);
            System.out.println("Enviando objeto: " + persona);

            ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOut = new ObjectOutputStream(byteOutStream);
            objectOut.writeObject(persona);
            byte[] data = byteOutStream.toByteArray();

            InetAddress serverInet = InetAddress.getByName(serverAddress);
            DatagramPacket packet = new DatagramPacket(data, data.length, serverInet, port);
            socket.send(packet);

            byte[] buffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);

            ByteArrayInputStream byteStream = new ByteArrayInputStream(responsePacket.getData());
            ObjectInputStream objectInput = new ObjectInputStream(byteStream);
            Persona modifiedPersona = (Persona) objectInput.readObject();

            System.out.println("Objeto modificado recibido: " + modifiedPersona);
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}
