public class MyHilo extends Thread {
    private SolicitaSuspender suspender = new SolicitaSuspender();
    private int contador = 0; // Contador inicializado a 0
    private volatile boolean continuar = true; // Variable para controlar el bucle

    // Petición de suspender hilo
    public void suspende() {
        suspender.set(true);
    }

    // Petición de continuar hilo
    public void reanuda() {
        suspender.set(false);
    }

    // Método que devuelve el valor del contador
    public int getContador() {
        return contador;
    }

    // Método run() que ejecuta el hilo
    public void run() {
        try {
            while (continuar) {
                // Incrementar el contador
                contador++;

                // Mostrar el valor del contador
                System.out.println("Contador: " + contador);

                // Dormir para que se vea el valor
                Thread.sleep(500); // 0.5 segundos

                // Esperar a que se reanude si está suspendido
                suspender.esperandoParaReanudar();
            }
        } catch (InterruptedException exception) {
            System.out.println("El hilo fue interrumpido.");
        }
        // Cuando termina el bucle, muestra el mensaje
        System.out.println("El hilo ha terminado.");
    }

    // Método para finalizar el bucle
    public void detener() {
        continuar = false;
    }
}
