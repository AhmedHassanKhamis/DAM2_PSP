import java.util.concurrent.*;

class FibonacciProducer implements Runnable {
    private BlockingQueue<Integer> queue;
    
    public FibonacciProducer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    
    @Override
    public void run() {
        int a = 0, b = 1;
        for (int i = 0; i < 10; i++) {
            try {
                queue.put(a);
                int next = a + b;
                a = b;
                b = next;
                Thread.sleep(100); // Simula un pequeño retraso
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class FibonacciConsumer implements Runnable {
    private BlockingQueue<Integer> queue;
    
    public FibonacciConsumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    
    @Override
    public void run() {
        try {
            for (int i = 0; i < 50; i++) { // 5 hilos * 10 números cada uno
                int value = queue.take();
                System.out.println("Consumido: " + value);
                Thread.sleep(150); // Simula procesamiento
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class FibonacciHilosAHK2 {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        ExecutorService executor = Executors.newFixedThreadPool(6);

        // Lanzar los 5 hilos productores
        for (int i = 0; i < 5; i++) {
            executor.execute(new FibonacciProducer(queue));
        }

        // Lanzar el hilo consumidor
        executor.execute(new FibonacciConsumer(queue));

        executor.shutdown();
    }
}

