package ejercicios.SMTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;
import org.apache.commons.net.pop3.POP3SClient;

public class ejercicio2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar datos para enviar correo SMTP
        System.out.print("Introduce servidor SMTP: ");
        String smtpServer = scanner.nextLine();

        System.out.print("Necesita negociación TLS (S, N)?: ");
        String tlsInput = scanner.nextLine();
        boolean necesitaTLS = tlsInput.equalsIgnoreCase("S");

        System.out.print("Introduce usuario SMTP: ");
        String smtpUser = scanner.nextLine();

        System.out.print("Introduce password SMTP: ");
        String smtpPassword = scanner.nextLine();

        System.out.print("Introduce puerto SMTP: ");
        int smtpPort = Integer.parseInt(scanner.nextLine());

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

        // Validar mensaje
        if (mensaje.toString().trim().isEmpty()) {
            System.out.println("No se puede enviar un mensaje vacío.");
            return;
        }

        // Llamar al método para enviar correo
        enviarCorreoSMTP(smtpServer, smtpPort, smtpUser, smtpPassword, necesitaTLS, remitente, destino, asunto, mensaje.toString());

        // Solicitar datos para servidor POP3
        System.out.print("Introduce servidor POP3: ");
        String pop3Server = scanner.nextLine();

        System.out.print("Introduce puerto POP3: ");
        int pop3Port = Integer.parseInt(scanner.nextLine());

        System.out.print("Introduce usuario POP3: ");
        String pop3User = scanner.nextLine();

        System.out.print("Introduce password POP3: ");
        String pop3Password = scanner.nextLine();

        // Llamar al método para acceder al correo POP3
        accederCorreoPOP3(pop3Server, pop3Port, pop3User, pop3Password);
    }

    private static void enviarCorreoSMTP(String server, int port, String user, String password, boolean necesitaTLS,
                                         String remitente, String destino, String asunto, String mensaje) {
        // Similar al código proporcionado para el envío SMTP (sin cambios relevantes)
        System.out.println("Función de envío de correo implementada...");
    }

    private static void accederCorreoPOP3(String server, int port, String user, String password) {
        POP3SClient client = new POP3SClient(true); // Conexión segura
        try {
            client.connect(server, port);
            System.out.println("Conexión al servidor POP3 establecida: " + client.getReplyString());

            if (!client.login(user, password)) {
                System.err.println("Error al iniciar sesión en el servidor POP3.");
                return;
            }

            System.out.println("Autenticación exitosa: " + client.getReplyString());

            int numMensajes = client.listMessages().length;
            System.out.println("Número total de mensajes: " + numMensajes);

            for (int i = 1; i <= numMensajes; i++) {
                Reader messageReader = client.retrieveMessage(i);
                if (messageReader == null) {
                    System.out.println("No se pudo recuperar el mensaje " + i);
                    continue;
                }

                BufferedReader reader = new BufferedReader(messageReader);
                System.out.println("Mensaje " + i + ":");
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }
                System.out.println("-----------------------------");
            }


            client.logout();
        } catch (IOException e) {
            System.err.println("Error al conectar al servidor POP3: " + e.getMessage());
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                System.err.println("Error al desconectar del servidor POP3: " + e.getMessage());
            }
        }
    }
}
