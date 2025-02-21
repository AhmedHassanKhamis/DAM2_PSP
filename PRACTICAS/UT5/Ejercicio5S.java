class ContadorSincronizado {
    private int contador = 0;

    public synchronized void incrementar() {
        contador++;
    }

    public int getContador() {
        return contador;
    }
}

class HiloRunnable implements Runnable {
    private ContadorSincronizado contador;

    public HiloRunnable(ContadorSincronizado contador) {
        this.contador = contador;
    }

    public void run() {
        for (int i = 0; i < 5000; i++) {
            contador.incrementar();
        }
    }
}

public class Ejercicio5S {
    public static void main(String[] args) {
        ContadorSincronizado contador = new ContadorSincronizado();
        Thread[] hilos = new Thread[5];

        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(new HiloRunnable(contador));
            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Valor final del contador (con sincronización): " + contador.getContador());
    }
}
