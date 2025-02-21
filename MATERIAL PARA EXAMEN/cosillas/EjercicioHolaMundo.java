public class EjercicioHolaMundo extends Thread {
    private int id;

    public EjercicioHolaMundo(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Hola Mundo! desde el hilo " + id);
    }

    public static void main(String[] args) {
        int numHilos = 5;
        EjercicioHolaMundo[] hilos = new EjercicioHolaMundo[numHilos];

        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new EjercicioHolaMundo(i + 1);
            hilos[i].start();
        }

        for (int i = 0; i < numHilos; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Todos los hilos han finalizado.");
    }
}
