public class SolicitaSuspender {
    private boolean suspender;

    // Establece el estado de suspensión
    public synchronized void set(boolean b) {
        suspender = b; // Cambio de estado sobre el objeto
        notifyAll();
    }

    // Espera a que se reanude el hilo
    public synchronized void esperandoParaReanudar() throws InterruptedException {
        while (suspender)
            wait(); // Suspende el hilo hasta recibir un notify()
    }
}
