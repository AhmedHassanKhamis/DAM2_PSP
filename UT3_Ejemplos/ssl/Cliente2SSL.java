package ssl;
import java.io.*;
import java.math.BigInteger;
import java.security.cert.X509Certificate;

import javax.net.ssl.*;

public class Cliente2SSL  {
  public static void main(String[] args) throws Exception {
	String Host = "localhost";
	int puerto = 6000;//puerto remoto		
	  
    System.setProperty("javax.net.ssl.trustStore","D:/CAPIT5/SSL/cli/CliCertConfianza");
    System.setProperty("javax.net.ssl.trustStorePassword", "890123");
    
    System.out.println("PROGRAMA CLIENTE INICIADO....");
	SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
	SSLSocket Cliente  = (SSLSocket) sfact.createSocket(Host, puerto);

	 SSLSession session = ((SSLSocket) Cliente).getSession();	 
	 System.out.println("Host: "+session.getPeerHost());
	 System.out.println("Cifrado: " + session.getCipherSuite());
     System.out.println("Protocolo: " + session.getProtocol());
     System.out.println("IDentificador:" + new BigInteger(session.getId()));
     System.out.println("Creaci�n de la sesi�n: " + session.getCreationTime());
       
	 X509Certificate certificate = (X509Certificate)session.getPeerCertificates()[0];
     System.out.println("Propietario: "+certificate.getSubjectDN());
     System.out.println("Algoritmo: "+certificate.getSigAlgName());
     System.out.println("Tipo: "+certificate.getType());
     System.out.println("Emisor: "+certificate.getIssuerDN());
     System.out.println("N�mero Serie: "+certificate.getSerialNumber());
         
      
	// CREO FLUJO DE SALIDA AL SERVIDOR	
	DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

	// ENVIO UN SALUDO AL SERVIDOR
	flujoSalida.writeUTF("Saludos al SERVIDOR DESDE EL CLIENTE");

	// CREO FLUJO DE ENTRADA AL SERVIDOR	
	DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

	// EL servidor ME ENVIA UN MENSAJE
	System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());

	// CERRAR STREAMS Y SOCKETS	
	flujoEntrada.close();	
	flujoSalida.close();	
	Cliente.close();		
  }// main
}//..ClienteSSL
