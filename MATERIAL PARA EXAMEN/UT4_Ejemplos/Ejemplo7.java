import java.io.File;
import java.io.IOException;
 
public class Ejemplo7 { 
  public static void main(String args[]) throws IOException {
    ProcessBuilder pb = new ProcessBuilder("ls");
    
    File fOut = new File("salida.txt");
    File fErr = new File("error.txt");
 
    pb.redirectOutput(fOut);
    pb.redirectError(fErr); 
    pb.start(); 
  }
}// Ejemplo7
