import java.io.*;
import java.security.*;

public class RecuperaClaveSecreta {
	public static void main(String[] args) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					"Clave.secreta"));
			Key secreta = (Key) in.readObject();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// main
}// ..RecuperaClaveSecreta 