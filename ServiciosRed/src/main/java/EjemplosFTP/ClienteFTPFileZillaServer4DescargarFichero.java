package EjemplosFTP;
import java.io.*;
import org.apache.commons.net.ftp.*;
//Para probar en FileZilla Server local
public class ClienteFTPFileZillaServer4DescargarFichero {
	public static void main(String[] args) {
		FTPClient cliente = new FTPClient();	
	    String servidor = "localhost";
		String user = "usertar";
		String pasw = "usertar";
		try {
			System.out.println("Conectándose a " + servidor);
			cliente.connect(servidor);			
			cliente.enterLocalPassiveMode();
			boolean login = cliente.login(user, pasw);					
			if (login) {
				System.out.println("Login correcto");		
				//descargar fichero
				String direc = "/NUEVODIREC";
				cliente.changeWorkingDirectory(direc); 			
				//stream de salida para recibir el fichero descargado
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("/home/usertar/holaDestino.txt")); //Destino			
				if(cliente.retrieveFile("hola.txt", out)) // Origen
					System.out.println("Recuperado correctamente... ");
				else
					System.out.println("No se ha podido descargar... ");
				out.close();	
				cliente.logout();
				cliente.disconnect();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}//main
}
