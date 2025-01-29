import java.net.*;
public class Ejemplo3  {
  public statEjercicio2main(String[] args) {
	String Host = "localhost";
	int Puerto = 6000;//puerto remoto		
	//System.setSecurityManager(new SecurityManager());	
	try {
	  Socket Cliente = new Socket(Host, Puerto);
      System.out.println("CLIENTE INICIADO.");
	  Cliente.close();
      System.out.println("CLIENTE FINALIZADO.");
	} catch (Exception e) {
		System.err.println("ERROR=> " + e.toString());
	}	
  }// main
}//..Ejemplo3  
