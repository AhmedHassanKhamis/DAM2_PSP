public class Ejercicio7S {

    public static void main(String[] args) {
        Cola cola = new Cola();

        Productor p = new Productor(cola, 1);
        Consumidor c = new Consumidor(cola, 1);

        p.start();
        c.start();
    }
}

class Cola {
    private int numero;
    private boolean disponible = false; // inicialmente cola vacía

    public synchronized int get() {
        while (!disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Se consume: " + numero);
        disponible = false;
        notify();
        return numero;
    }

    public synchronized void put(int valor) {
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        numero = valor;
        disponible = true;
        System.out.println("Se produce: " + numero);
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
        int valor = 0;
        for (int i = 0; i < 5; i++) {
            valor = cola.get(); // recoge el número
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
        for (int i = 0; i < 5; i++) {
            cola.put(i); // pone el número
            try {
                sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}
