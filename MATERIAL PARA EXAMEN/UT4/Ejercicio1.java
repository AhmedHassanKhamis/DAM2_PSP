import java.io.*;

public class Ejercicio1 {
  public static void main(String[] args) throws IOException {

	//creamos objeto File al directorio donde esta Ejemplo2
	File directorio = new File("./");	

	//El proceso a ejecutar es Ejemplo2			
	ProcessBuilder pb = new ProcessBuilder("java", "LeerNombre","peter");

    //se establece el directorio donde se encuentra el ejecutable
    pb.directory(directorio);
        
	System.out.printf("Directorio de trabajo: %s%n",pb.directory());

    //se ejecuta el proceso
	Process p = pb.start();

	// Esperamos a que finalice y obtenemos su valor de salida
	try {
		int valorSalida = p.waitFor();
		System.out.println("Valor de salida del proceso: " + valorSalida);
	} catch (Exception e) {
		// TODO: handle exception
		System.err.println(e);
	}
	

   //obtener la salida devuelta por el proceso
	try {
		InputStream is = p.getInputStream();
		int c;
		while ((c = is.read()) != -1)
			System.out.print((char) c);
		is.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		
  }
}// Ejemplo3
