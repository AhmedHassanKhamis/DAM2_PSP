public class Ejercicio1 extends Thread {
	// constructor
	public Ejercicio1(String nombre) {
		super(nombre);
		System.out.println("CREANDO HILO:" + getName());
	}

	// metodo run
	public void run() {
		for (int i=0; i<5; i++)       
			System.out.println(getName());
			try {
					Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
	}

	//
	public static void main(String[] args) {
		

		Ejercicio1 h1 = new Ejercicio1("TIC");
		Ejercicio1 h2 = new Ejercicio1("TAC");	

		h1.start();
		h2.start();

		

		
	}// main
	
}// HiloEjemplo1
