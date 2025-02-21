class Contador {
    public int contador = 0;
}

class HiloThread extends Thread {
    private Contador contador;

    public HiloThread(Contador contador) {
        this.contador = contador;
    }

    public void run() {
        for (int i = 0; i < 5000; i++) {
            contador.contador++;
        }
    }
}

public class Ejercicio5NoS {
    public static void main(String[] args) {
        Contador contador = new Contador();
        Thread[] hilos = new Thread[5];

        for (int i = 0; i < 5; i++) {
            hilos[i] = new HiloThread(contador);
            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Valor final del contador (sin sincronización): " + contador.contador);
    }
}
