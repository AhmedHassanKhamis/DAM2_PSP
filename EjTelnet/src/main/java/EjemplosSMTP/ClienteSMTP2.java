package EjemplosSMTP;

import java.io.IOException;
import java.net.ConnectException;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;

public class ClienteSMTP2 {

	public static void main(String[] args) {
		SMTPClient client = new SMTPClient();
		try {
			int respuesta;
			client.connect("localhost");
			System.out.print(client.getReplyString());
			respuesta = client.getReplyCode();

			if (!SMTPReply.isPositiveCompletion(respuesta)) {
				client.disconnect();
				System.err.println("CONEXION RECHAZADA.");
				System.exit(1);
			}

			// REALIZAR ACCIONES
			client.login(); // inicio de sesión HELO
			String destinatario = "userman@localhost";
			String mensaje = "Hola. \nEnviando saludos.\nChao.";
			String remitente = "usertar@localhost";

			// Mensaje corto
			if (client.sendSimpleMessage(remitente, destinatario, mensaje))
				System.out.println("Mensaje enviado a " + destinatario);
			else
				System.out.println("No se ha podido enviar");

			client.logout();// final de sesión QUIT



			client.disconnect();

		}catch(ConnectException ce){
			System.err.println("EL SERVIDOR NO ESTÁ INICIADO.");			
			System.err.println(ce.getMessage());
			System.exit(2);
		}
		catch (IOException e) {
			System.err.println("NO SE PUEDE CONECTAR AL SERVIDOR.");
			e.printStackTrace();
			System.exit(2);
		}

	}//main

}
