public class Ejercicio2R implements Runnable{
	private String nombre;
	// constructor
	public Ejercicio2R(String nombre) {
		this.nombre = nombre;
		System.out.println("CREANDO HILO:" + nombre);
	}

	// metodo run
	public void run() {
		for (int i=0; i<5; i++)       
			System.out.println(Thread.currentThread().getName());
			try {
					Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
	}

}// HiloEjemplo1
