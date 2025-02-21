import java.io.*;
import java.util.Scanner;


public class Ejercicio1AAHK {
  public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    String cadena;
		do {
			cadena = scanner.nextLine();
			if (cadena.startsWith("*")) {
				System.out.println("FIN!");
			}else {
				System.out.println("cadena: "+cadena+", longitud: "+cadena.length()*2 );
			}
		} while (!cadena.startsWith("*"));
	}
}