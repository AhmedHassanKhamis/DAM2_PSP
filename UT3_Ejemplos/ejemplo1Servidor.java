import java.io.*;
import java.net.*;

public class ejemplo1Servidor {
	public static void main(String[] arg) throws IOException {
		int numeroPuerto = 6000;// Puerto
		ServerSocket servidor = null;
        System.setSecurityManager(new SecurityManager());
		try {
		servidor = new ServerSocket(numeroPuerto);

		
		Socket clienteConectado = null;
		System.out.println("Esperando al cliente....."+servidor.getLocalPort());
		System.out.println("Esperando al cliente....."+servidor.getLocalSocketAddress());
		clienteConectado = servidor.accept();
	    System.out.println("Adios al cliente....."+clienteConectado.getPort());
	    System.out.println("Adios al cliente2....."+clienteConectado.getLocalPort());
		
		clienteConectado.close();
		servidor.close();
		} catch (Exception e) {
				System.err.println("ERROR=> " + e.toString());
			}

	}// main
}// fin
