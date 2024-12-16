import java.io.*;
import java.security.*;
import javax.crypto.*;

public class AlmacenaClaveSecreta {
   public static void main(String[] args) {
	try {
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		kg.init(128);

        //genera clave secreta
		SecretKey clave = kg.generateKey();

		ObjectOutputStream out = new ObjectOutputStream(
				       new FileOutputStream("Clave.secreta"));
		out.writeObject(clave);
		out.close();
	} catch (NoSuchAlgorithmException e) {e.printStackTrace();} 
	  catch (FileNotFoundException e) {e.printStackTrace();} 
	  catch (IOException e) {e.printStackTrace();}
   }//main
}//..AlmacenaClaveSecreta
