import java.net.InetAddress;
import java.net.UnknownHostException;

public class ejercicio1 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java HostInfo <nombre o dirección IP>");
            return;
        }

        String hostNameOrIP = args[0];
        try {
            InetAddress address = InetAddress.getByName(hostNameOrIP);
            System.out.println("Nombre de host: " + address.getHostName());
            System.out.println("Dirección IP: " + address.getHostAddress());
            System.out.println("¿Es localhost?: " + address.isLoopbackAddress());
            System.out.println("¿Es alcanzable?: " + address.isReachable(2000));
        } catch (UnknownHostException e) {
            System.out.println("Error: No se pudo resolver el host.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
