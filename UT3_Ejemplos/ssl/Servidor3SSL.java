package ssl;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateException;

import javax.net.ssl.*;

public class Servidor3SSL {
	public static void main(String[] arg) throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException {
		int puerto = 6000;
		
		//Definir el fichero almacén que contiene el certificado y la clave //para acceder a  él
		FileInputStream ficAlmacen = new FileInputStream
		                             ("D:/CAPIT5/SSL/srv/AlmacenSrv");
		String claveAlmacen = "1234567";

		//Cargar en un KeyStore el almacén que contiene el certificado
		KeyStore almacen = KeyStore.getInstance(KeyStore.getDefaultType());
		almacen.load(ficAlmacen, claveAlmacen.toCharArray());
		 
		//Crear el gestor de claves a partir del objeto KeyStore e //inicializarlo con la clave del almacén
		KeyManagerFactory kmf = KeyManagerFactory.getInstance 
		                        (KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(almacen, claveAlmacen.toCharArray());     
		       
		//Creación del contexto con soporte TLS       
		SSLContext contextoSSL = SSLContext.getInstance("TLS");
		contextoSSL.init(kmf.getKeyManagers(), null, null);
		        
		//Creación del socket SSL de servidor a patir del contexto
		SSLServerSocketFactory sfact = contextoSSL.getServerSocketFactory();
		SSLServerSocket servidorSSL = (SSLServerSocket) 
		                sfact.createServerSocket(puerto);

		DataInputStream flujoEntrada = null;//FLUJO DE ENTRADA DE CLIENTE
		DataOutputStream flujoSalida = null;//FLUJO DE SALIDA AL CLIENTE
		
		SSLSocket clienteConectado = null;				
		
		for (int i = 1; i < 5; i++) {
			System.out.println("Esperando al cliente " + i);
			
			clienteConectado = (SSLSocket) servidorSSL.accept();	
			          
	           
			  
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