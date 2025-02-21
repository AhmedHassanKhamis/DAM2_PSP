import java.io.*;

public class Ejercicio3 {
	public static void main(String[] args) throws IOException {
		//linux
		// Process p = new ProcessBuilder("date").start();

		Process p = new ProcessBuilder("java", "LeerDosNumeros.java").start();

		// escritura -- envia entrada a DATE
		OutputStream os = p.getOutputStream();
		os.write("2\n".getBytes());
		os.write("3\n".getBytes());
		os.flush(); // vacía el buffer de salida

		// lectura -- obtiene la salida de DATE
		InputStream is = p.getInputStream();		
		 int c;
		 while ((c = is.read()) != -1)
			System.out.print((char) c);
		 is.close();
		 
		// COMPROBACION DE ERROR - 0 bien - 1 mal
		int exitVal;
		try {
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}//Ejemplo4
