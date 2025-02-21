import java.io.*;

public class Ejercicio2 {
    
    static class Cola {
        private Character caracter;
        private boolean disponible = false;

        public synchronized char get() {
            while (!disponible) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    // Opcional: manejo de la interrupción
                }
            }
            disponible = false;
            notifyAll(); // Notifica a todos los hilos en espera
            return caracter;
        }

        public synchronized void put(char valor) {
            while (disponible) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    // Opcional: manejo de la interrupción
                }
            }
            caracter = valor;
            disponible = true;
            notifyAll(); // Notifica a todos los hilos en espera
        }
    }

    static class Productor extends Thread {
        private Cola cola;
        private String archivo;
        private int numConsumidores; // Para enviar la señal de fin a cada consumidor

        public Productor(Cola c, String archivo, int numConsumidores) {
            cola = c;
            this.archivo = archivo;
            this.numConsumidores = numConsumidores;
        }

        public void run() {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                int caracter;
                while ((caracter = reader.read()) != -1) {
                    cola.put((char) caracter);
                }
                // Enviar una señal de fin para cada consumidor
                for (int i = 0; i < numConsumidores; i++) {
                    cola.put('\0');
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Productor ha finalizado.");
        }
    }

    static class Consumidor extends Thread {
        private Cola cola;
        private int id;

        public Consumidor(Cola c, int id) {
            cola = c;
            this.id = id;
        }

        public void run() {
            char c;
            while ((c = cola.get()) != '\0') {
                System.out.println("Consumidor " + id + " consume: " + c);
            }
            System.out.println("Consumidor " + id + " ha finalizado.");
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java Ejercicio2 <archivo>");
            return;
        }

        String archivo = args[0];
        Cola cola = new Cola();

        int numConsumidores = 3;
        Productor productor = new Productor(cola, archivo, numConsumidores);
        Consumidor consumidor1 = new Consumidor(cola, 1);
        Consumidor consumidor2 = new Consumidor(cola, 2);
        Consumidor consumidor3 = new Consumidor(cola, 3);

        productor.start();
        consumidor1.start();
        consumidor2.start();
        consumidor3.start();

        try {
            productor.join();
            consumidor1.join();
            consumidor2.join();
            consumidor3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Todos los consumidores han finalizado.");
    }
}
