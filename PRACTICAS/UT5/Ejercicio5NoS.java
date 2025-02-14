public class Ejercicio5NoS {
    static int contador = 0; // Variable compartida por los hilos

    public static void main(String[] args) {
        // Creación de 5 hilos mediante la clase Thread
        for (int i = 0; i < 5; i++) {
            Thread hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5000; j++) {
                        contador++; // Incremento de la variable compartida
                    }
                }
            });
            hilo.start();
        }

        // Esperar a que todos los hilos terminen
        try {
            Thread.sleep(1000); // Esperamos un poco para que los hilos terminen
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final del contador: " + contador);
    }
}
