import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

public class Ejercicio3 {

    public static void main(String[] args) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
            SecureRandom numero = SecureRandom.getInstance("SHA1PRNG");
            keyGen.initialize(2048, numero);

            KeyPair par = keyGen.generateKeyPair();
            PrivateKey clavepriv = par.getPrivate();
            PublicKey clavepub = par.getPublic();

            try (FileOutputStream fos = new FileOutputStream("Clave.publica")) {
                fos.write(clavepub.getEncoded());
            }

            try (FileOutputStream fos = new FileOutputStream("Clave.privada")) {
                fos.write(clavepriv.getEncoded());
            }

            System.out.println("Claves guardadas en Clave.publica y Clave.privada");

        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}
