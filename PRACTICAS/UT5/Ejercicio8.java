public class Ejercicio8 {

    public static void main(String[] args) {
        Cola cola = new Cola();

        Productor p = new Productor(cola, 1);
        Consumidor c1 = new Consumidor(cola, 1);
        Consumidor c2 = new Consumidor(cola, 2);

        p.start();
        c1.start();
        c2.start();
    }
}

class Cola {
    private String cadena;
    private boolean disponible = false; // inicialmente cola vacía

    public synchronized String get() {
        while (!disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        String valor = cadena;
        System.out.println("Consumidor: " + Thread.currentThread().getId() + ", consume: " + valor);
        disponible = false;
        notify();
        return valor;
    }

    public synchronized void put(String valor) {
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        cadena = valor;
        disponible = true;
        System.out.println("Productor: " + Thread.currentThread().getId() + ", produce: " + valor);
        notify();
    }
}

class Consumidor extends Thread {
    private Cola cola;
    private int n;

    public Consumidor(Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {  // Consumir 10 elementos
            String valor = cola.get(); // recoge la cadena
        }
    }
}

class Productor extends Thread {
    private Cola cola;
    private int n;

    public Productor(Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        String[] mensajes = {"PING", "PONG"};
        for (int i = 0; i < 20; i++) {  // Producir 20 elementos (10 pares PING/PONG)
            cola.put(mensajes[i % 2]); // Alterna entre "PING" y "PONG"
            try {
                sleep(100);  // Simula tiempo entre producción
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Fin productor...");
    }
}
