import java.io.*;

public class Ejercicio1C {
    public static void main(String[] args) throws IOException {
       
		File directorio = new File("./"); // O si está en el CLASSPATH (".\\bin");
		ProcessBuilder pb = new ProcessBuilder("java", "Ejercicio1A.java");  // Debe encontrar la clase.

		// ProcessBuilder pb = new ProcessBuilder("java", "EjemploLectura");  // Debe encontrar la clase.
		File fBat = new File("archivo1.txt");
		File fOut = new File("salida.txt");
		File fErr = new File("error.txt");
	 
		pb.redirectInput(fBat);
		pb.redirectOutput(fOut);
		pb.redirectError(fErr); 
		pb.directory(directorio);

		// se ejecuta el proceso
		Process p = pb.start();

		// // escritura -- envia entrada 
		// OutputStream os = p.getOutputStream();
		// os.write(fBat.getName().getBytes());
		// os.flush(); // vacía el buffer de salida
		try {
			// lectura -- obtiene la salida
			InputStream is = p.getInputStream();
			int c;
			while ((c = is.read()) != -1)
				System.out.print((char) c);
			is.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
		

		// COMPROBACION DE ERROR - 0 bien - 1 mal
		int exitVal;
		try {
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			InputStream er = p.getErrorStream();
			BufferedReader brer = new BufferedReader(new InputStreamReader(er));
			String liner = null;
			while ((liner = brer.readLine()) != null)
				System.out.println("ERROR >" + liner);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
    }
}
