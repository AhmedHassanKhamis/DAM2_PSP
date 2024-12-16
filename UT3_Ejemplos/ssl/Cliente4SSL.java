package ssl;
import java.io.*;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

import javax.net.ssl.*;

public class Cliente4SSL  {
  public static void main(String[] args) throws Exception {
	String Host = "localhost";
	int puerto = 6000;//puerto remoto		
	  
    
	//Definir el fichero almacén que contiene el certificado de confianza y 
	//la clave para acceder a  él
	FileInputStream ficCerConf = new 
	                FileInputStream("D:/CAPIT5/SSL/cli/CliCertConfianza");
	String claveCerConf = "890123";

	FileInputStream ficAlmacencli= new FileInputStream("D:/CAPIT5/SSL/cli/AlmacenCli");
    String claveAlmacenCli = "clavecli";
       
    //  
    KeyStore almacen=KeyStore.getInstance(KeyStore.getDefaultType());
    almacen.load(ficAlmacencli, claveAlmacenCli.toCharArray());

    KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    kmf.init(almacen, claveAlmacenCli.toCharArray());      
   
	//Cargar en un KeyStore el almacén de certificados de confianza
	KeyStore almacenConf = KeyStore.getInstance(KeyStore.getDefaultType());
	almacenConf.load(ficCerConf, claveCerConf.toCharArray());

	//Crear el gestor de confianza a partir del objeto KeyStore e //inicializarlo con la clave del almacén
	TrustManagerFactory tmf = TrustManagerFactory.getInstance
	                   (TrustManagerFactory.getDefaultAlgorithm());
	tmf.init(almacenConf);

	//Creación del contexto con soporte TLS        
	SSLContext contextoSSL = SSLContext.getInstance("TLS");
	contextoSSL.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null); 
	    
	//Creación del socket SSL de cliente a partir del contexto
	SSLSocketFactory sfact = contextoSSL.getSocketFactory();
	SSLSocket Cliente = (SSLSocket) sfact.createSocket(Host, puerto);


	 System.out.println("PROGRAMA CLIENTE INICIADO....");
	         
      
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
