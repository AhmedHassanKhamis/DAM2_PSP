import java.io.*;

public class Ejercicio1B {
    public static void main(String[] args) {
        try {
            File directorio = new File("./"); // O si está en el CLASSPATH (".\\bin");
            ProcessBuilder processBuilder = new ProcessBuilder("java", "Ejercicio1A.java");
            processBuilder.directory(directorio);
            Process proceso = processBuilder.start();
            
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));


            // Simula entrada estándar al proceso
            writer.write("Hola\n");
            writer.write("Mundo\n");
            writer.write("*\n");
            writer.flush();
            writer.close();

            // lectura -- obtiene la salida
			InputStream is = proceso.getInputStream();
			int c;
			while ((c = is.read()) != -1)
				System.out.print((char) c);
			is.close();

            // // Lee la salida del proceso
            // String linea;
            // while ((linea = reader.readLine()) != null) {
            //     System.out.println("Proceso dice: " + linea);
            // }

            proceso.waitFor();
            System.out.println("Proceso finalizado con código: " + proceso.exitValue());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
