package ejercicios.SMTP;
import java.io.IOException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;

import org.apache.commons.net.smtp.*;

public class ejercicio1 {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException {

        Scanner scanner = new Scanner(System.in);

        // Solicitar datos al usuario
        System.out.print("Introduce servidor SMTP: ");
        String server = scanner.nextLine();

        System.out.print("Necesita negociación TLS (S, N)?: ");
        String tlsInput = scanner.nextLine();
        boolean necesitaTLS = tlsInput.equalsIgnoreCase("S");

        System.out.print("Introduce usuario: ");
        String username = scanner.nextLine();

        System.out.print("Introduce password: ");
        String password = scanner.nextLine();

        System.out.print("Introduce puerto: ");
        int puerto = Integer.parseInt(scanner.nextLine());

        System.out.print("Introduce correo del remitente: ");
        String remitente = scanner.nextLine();

        System.out.print("Introduce correo destinatario: ");
        String destino = scanner.nextLine();

        System.out.print("Introduce asunto: ");
        String asunto = scanner.nextLine();

        System.out.println("Introduce el mensaje, finalizará cuando se introduzca un *:");
        StringBuilder mensaje = new StringBuilder();
        while (true) {
            String linea = scanner.nextLine();
            if (linea.equals("*")) break;
            mensaje.append(linea).append("\n");
        }

        if (mensaje.toString().trim().isEmpty()) {
            System.out.println("No se puede enviar un mensaje vacío.");
            return;
        }

        AuthenticatingSMTPClient client = new AuthenticatingSMTPClient(necesitaTLS ? "TLS" : "SSL");

        try {
            int respuesta;
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(null, null);
            KeyManager km = kmf.getKeyManagers()[0];

            // Conectar al servidor
            client.connect(server, puerto);
            System.out.println("Conexión establecida: " + client.getReplyString());

            client.login(server);
            client.setKeyManager(km);

            respuesta = client.getReplyCode();
            if (!SMTPReply.isPositiveCompletion(respuesta)) {
                client.disconnect();
                System.err.println("El servidor SMTP rechazó la conexión.");
                return;
            }

            client.ehlo(server);
            System.out.println("EHLO enviado: " + client.getReplyString());

            if (necesitaTLS) {
                if (client.execTLS()) {
                    System.out.println("Negociación TLS establecida.");
                    client.ehlo(server); // Requerido después de iniciar TLS
                } else {
                    System.err.println("No se pudo establecer negociación TLS.");
                    return;
                }
            }

            try {
				if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.LOGIN, username, password)) {
				    System.out.println("Autenticación exitosa: " + client.getReplyString());

				    SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destino, asunto);
				    client.setSender(remitente);
				    client.addRecipient(destino);
				    System.out.println("Destinatario añadido: " + client.getReplyString());

				    Writer writer = client.sendMessageData();
				    if (writer == null) {
				        System.err.println("Error al iniciar el envío de datos.");
				        return;
				    }
				    writer.write(cabecera.toString());
				    writer.write(mensaje.toString());
				    writer.close();

				    if (client.completePendingCommand()) {
				        System.out.println("Mensaje enviado con éxito.");
				    } else {
				        System.err.println("Error al completar el envío del mensaje.");
				    }
				} else {
				    System.err.println("Autenticación fallida: " + client.getReplyString());
				}
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        } catch (IOException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                System.err.println("Error al desconectar: " + e.getMessage());
            }
        }

        System.out.println("Fin del envío.");
    }
}
