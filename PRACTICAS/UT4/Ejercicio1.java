import java.io.*;

public class Ejercicio1 {
	public static void main(String[] args) throws IOException {

		// creamos objeto File al directorio donde esta Ejemplo2
		File directorio = new File("./");

		// El proceso a ejecutar es Ejemplo2
		ProcessBuilder pb = new ProcessBuilder("java", "LeerNombre","ahmed");

		// se establece el directorio donde se encuentra el ejecutable
		pb.directory(directorio);

		System.out.printf("Directorio de trabajo: %s%n", pb.directory());

		// se ejecuta el proceso
		Process p = pb.start();

		// obtener la salida devuelta por el proceso
		try {
			InputStream is = p.getInputStream();
			int c;
			while ((c = is.read()) != -1)
				System.out.print((char) c);
			is.close();
			// esperar a que el proceso termine y obtener el valor de salida
			int exitCode = p.waitFor();
			System.out.printf("El proceso ha terminado con el código de salida: %d%n", exitCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}// Ejemplo3