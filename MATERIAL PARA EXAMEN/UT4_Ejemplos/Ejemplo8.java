import java.io.File;
import java.io.IOException; 

public class Ejemplo8 { 
  public static void main(String args[]) throws IOException {
    ProcessBuilder pb = new ProcessBuilder("konsole");
    
    File fBat = new File("fichero.sh");
    File fOut = new File("salida.txt");
    File fErr = new File("error.txt");
 
    pb.redirectInput(fBat);
    pb.redirectOutput(fOut);
    pb.redirectError(fErr); 
    pb.start(); 

  }
}// Ejemplo8
