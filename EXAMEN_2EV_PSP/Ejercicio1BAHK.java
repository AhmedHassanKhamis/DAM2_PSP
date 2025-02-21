import java.io.*;

public class Ejercicio1BAHK {
	public static void main(String[] args) throws IOException {
		//linux
		// Process pAHK = new ProcessBuilder("date").start();

		Process pAHK = new ProcessBuilder("java", "Ejercicio1AAHK.java").start();

		// escritura -- envia entrada a DATE
		OutputStream osAHK = pAHK.getOutputStream();
		osAHK.write("holas\n".getBytes());
		osAHK.write("que tal estas?\n".getBytes());
		osAHK.write("*\n".getBytes());
		osAHK.flush(); // vacía el buffer de salida

		// lectura -- obtiene la salida de DATE
		InputStream is = pAHK.getInputStream();		
		 int c;
		 while ((c = is.read()) != -1)
			System.out.print((char) c);
		 is.close();
		 
		// COMPROBACION DE ERROR - 0 bien - 1 mal
		int exitVal;
		try {
			exitVal = pAHK.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}//Ejemplo4
