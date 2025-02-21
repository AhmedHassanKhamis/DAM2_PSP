public class Ejercicio2USO extends Thread {
	public static void main(String[] args) {
		

		Ejercicio2R tareaUno = new Ejercicio2R("TIC");
		Ejercicio2R tareaDos = new Ejercicio2R("TAC");	


		Thread hilo1 = new Thread(tareaUno, "TIC");
		Thread hilo2 = new Thread(tareaDos, "TAC");


		hilo1.start();
		hilo2.start();

		

		
	}// main
	
}// HiloEjemplo1
