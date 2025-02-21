import java.io.IOException;

public class Ejemplo9 {
	public static void main(String args[]) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "DIR");

		//la salida a consola
		pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);	    
		Process p = pb.start();		
	
	}
}// Ejemplo9
