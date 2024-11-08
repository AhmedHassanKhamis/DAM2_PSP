package EjemplosFTP;

import java.io.*;
import org.apache.commons.net.ftp.*;

//Para probar en FileZilla Server local
public class ClienteFTPFileZillaServer3SubirFichero {
	public static void main(String[] args) {
		FTPClient cliente = new FTPClient();
		String servidor = "localhost";
		String user = "usertar";
		String pasw = "usertar";

		try {
			System.out.println("Conectándose a " + servidor);
			cliente.connect(servidor);
			boolean login = cliente.login(user, pasw);

			cliente.setFileType(FTP.BINARY_FILE_TYPE);
			String direc = "/NUEVODIREC";
			cliente.enterLocalPassiveMode();
			if (login) {
				System.out.println("Login correcto");
				if (!cliente.changeWorkingDirectory(direc)) {
					String directorio = "NUEVODIREC";
					if (cliente.makeDirectory(directorio)) {
						System.out.println("Directorio :  " + directorio + " creado ...");
						cliente.changeWorkingDirectory(directorio);
					} else {
						System.out.println("No se ha podido crear el Directorio");
						System.exit(0);
					}
				}
				System.out.println("Directorio actual: " + cliente.printWorkingDirectory());

				String archivo = "/home/usertar/EJEMPLO.pdf"; // O cualquiera.
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(archivo));

				if (cliente.storeFile("EJEMPLO.pdf", in)) // Nombre a almacenar
					System.out.println("Subido correctamente... ");
				else
					System.out.println("No se ha podido subir el fichero... ");
				in.close(); // Cerrar flujo
				cliente.logout();
				cliente.disconnect();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}// main
}
