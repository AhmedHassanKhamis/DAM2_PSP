package ssl;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateException;

import javax.net.ssl.*;

public class Servidor4SSL {
	public static void main(String[] arg) throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException {
		int puerto = 6000;
		
		FileInputStream ficAlmacen= new FileInputStream("D:/CAPIT5/SSL/srv/AlmacenSrv");
		FileInputStream ficCerConf= new FileInputStream("D:/CAPIT5/SSL/srv/SrvCertConfianza");
		
        String claveAlmacen = "1234567";
        String claveCerConf="cercli";
       
        //   
        KeyStore almacen=KeyStore.getInstance(KeyStore.getDefaultType());
        almacen.load(ficAlmacen, claveAlmacen.toCharArray());
 
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(almacen, claveAlmacen.toCharArray());      
       
        // 
        
        KeyStore almacenConf = KeyStore.getInstance(KeyStore.getDefaultType());
        almacenConf.load(ficCerConf, claveCerConf.toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(almacenConf);

        //        
        SSLContext contextoSSL = SSLContext.getInstance("TLS");
        contextoSSL.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        
        //
        SSLServerSocketFactory sfact = contextoSSL.getServerSocketFactory();
		SSLServerSocket servidorSSL = (SSLServerSocket) sfact.createServerSocket(puerto);
		
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