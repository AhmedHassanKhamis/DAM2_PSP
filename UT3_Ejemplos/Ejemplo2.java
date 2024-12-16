import java.io.*;
public class Ejemplo2 {
   public static void main(String[] args) {
    // System.setSecurityManager(new SecurityManager()); // Sentencia YA NO PERMITIDA
	try {
		//escritura en fichero		
		BufferedWriter fichero = new BufferedWriter
		   (new FileWriter("/home/usertar/Escritorio/000_CLASE/DAM2_PSP/UT3_Ejemplos/Fichero.txt"));
		fichero.write("Escritura de una línea en fichero.");
		fichero.newLine(); // escribe un salto de línea
		fichero.close();
		System.out.println("Final proceso de escritura...");

		//lectura en fichero
		BufferedReader fichero2 = new BufferedReader
              (new FileReader("/home/usertar/Escritorio/000_CLASE/DAM2_PSP/UT3_Ejemplos/Fichero.txt"));
		String linea = fichero2.readLine();
		System.out.println("Contenido del fichero: ");
		System.out.println("\t" + linea);
		fichero2.close();
		System.out.println("Final proceso de lectura...");
			
	} catch (FileNotFoundException fn) {
		System.out.println("No se encuentra el fichero");
	} catch (IOException io) {
		System.out.println("Error de E/S ");
	} catch ( Exception e) {		
	    System.out.println("ERROR => " + e.toString());			
	}

   }// main
}//..Ejemplo2
