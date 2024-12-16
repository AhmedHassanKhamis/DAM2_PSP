package ssl;
import java.io.*;
import java.math.BigInteger;
import java.security.cert.X509Certificate;

import javax.net.ssl.*;

public class Servidor2SSL {
	public static void main(String[] arg) throws IOException {
		int puerto = 6000;
		
        System.setProperty("javax.net.ssl.keyStore", "D:/CAPIT5/SSL/srv/AlmacenSrv");
        System.setProperty("javax.net.ssl.keyStorePassword", "1234567");
        

		SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory
				.getDefault();
		SSLServerSocket servidorSSL = (SSLServerSocket) sfact
				.createServerSocket(puerto);
		
		DataInputStream flujoEntrada = null;//FLUJO DE ENTRADA DE CLIENTE
		DataOutputStream flujoSalida = null;//FLUJO DE SALIDA AL CLIENTE
		
		SSLSocket clienteConectado = null;				
		
		for (int i = 1; i < 5; i++) {
			System.out.println("Esperando al cliente " + i);
			
			clienteConectado = (SSLSocket) servidorSSL.accept();	
			
		              
	         SSLSession session = ((SSLSocket) clienteConectado).getSession();	 
	    	 System.out.println("Host: "+session.getPeerHost());
	    	 System.out.println("Cifrado: " + session.getCipherSuite());
	         System.out.println("Protocolo: " + session.getProtocol());
	         System.out.println("IDentificador:" + new BigInteger(session.getId()));
	         System.out.println("Creación de la sesión: " + session.getCreationTime());
	           
	    	 X509Certificate certificate = (X509Certificate)session.getLocalCertificates()[0];
	         System.out.println("Propietario:    "+certificate.getSubjectDN());
	         System.out.println("Algoritmo:    "+certificate.getSigAlgName());
	         System.out.println("Tipo:    "+certificate.getType());
	         System.out.println("Emisor:    "+certificate.getIssuerDN());
	         System.out.println("Número Serie: "+certificate.getSerialNumber());
	         
			  
			flujoEntrada = new DataInputStream(clienteConectado.getInputStream());

			// EL CLIENTE ME ENVIA UN MENSAJE
			System.out.println("Recibiendo del CLIENTE: " + i + " \n\t"
					+ flujoEntrada.readUTF());
	
			flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());

			// ENVIO UN SALUDO AL CLIENTE
			flujoSalida.writeUTF("Saludos al cliente del servidor");
		}
		// CERRAR STREAMS Y SOCKETS
		flujoEntrada.close();
		flujoSalida.close();
		//clienteConectado.close();
		servidorSSL.close();

	}// main
}// ..ServidorSSL 