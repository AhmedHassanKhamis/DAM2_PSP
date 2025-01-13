import java.security.*;
import java.util.Scanner;

public class Ejercicio1 {
	public static void main(String[] args) {
        String texto1,texto2,clave;
		MessageDigest md,md2;
        Scanner Teclado = new Scanner(System.in);
        System.out.println("Mensaje1:");
        texto1 = Teclado.nextLine();
        System.out.println("Mensaje2:");
        texto2 = Teclado.nextLine();
        System.out.println("Clave:");
        clave = Teclado.nextLine();

		try {
			md = MessageDigest.getInstance("MD5");
            
			byte dataBytes[] = texto1.getBytes();
			md.update(dataBytes);// SE INTRODUCE TEXTO A RESUMIR
			// byte resumen[] = md.digest(); // SE CALCULA EL RESUMEN
			byte resumen[] = md.digest(clave.getBytes()); // SE CALCULA EL RESUMEN utilizando clave
			System.out.println("Mensaje original: " + texto1);			
			System.out.println("Mensaje resumen: " + new String(resumen));

            md2 = MessageDigest.getInstance("MD5");
            byte dataBytes2[] = texto2.getBytes();
			md2.update(dataBytes2);// SE INTRODUCE TEXTO A RESUMIR
			// byte resumen[] = md.digest(); // SE CALCULA EL RESUMEN
			byte resumen2[] = md2.digest(clave.getBytes()); // SE CALCULA EL RESUMEN utilizando clave
            System.out.println("Mensaje original: " + texto2);			
			System.out.println("Mensaje resumen: " + new String(resumen2));

            System.out.println(Hexadecimal(resumen));
            System.out.println(Hexadecimal(resumen2));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}// main
	// CONVIERTE UN ARRAY DE BYTES A HEXADECIMAL
	static String Hexadecimal(byte[] resumen) {
		String hex = "";
		for (int i = 0; i < resumen.length; i++) {
			String h = Integer.toHexString(resumen[i] & 0xFF);
			if (h.length() == 1)
				hex += "0";
			hex += h;
		}
		return hex.toUpperCase();
	}// Hexadecimal
}// ..
