public class FibonacciHilosAHK
 {

    // Variable compartida para los términos de la sucesión de Fibonacci
    private static final long[] fibonacci = new long[100];

    public static void main(String[] args) {
        
        // Crear e iniciar el hilo para calcular y mostrar la sucesión de Fibonacci
        Thread hiloFibonacci = new Thread(new Runnable() {
            @Override
            public void run() {
                // Calcular los primeros 100 términos de la sucesión Fibonacci
                fibonacci[0] = 0;
                fibonacci[1] = 1;

                System.out.println("Términos de Fibonacci:");
                System.out.print(fibonacci[0] + " " + fibonacci[1] + " ");

                for (int i = 2; i < 100; i++) {
                    fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
                    System.out.print(fibonacci[i] + " ");
                }
                System.out.println(); // Para un salto de línea al final
            }
        });

        // Crear e iniciar el hilo para sumar los términos de Fibonacci
        Thread hiloSuma = new Thread(new Runnable() {
            @Override
            public void run() {
                long suma = 0;
                for (int i = 0; i < 100; i++) {
                    suma += fibonacci[i];
                    System.out.println("Suma acumulada hasta el término " + (i + 1) + ": " + suma);
                    try {
                        // Hacemos una pequeña espera para que el hilo de la suma no consuma todos los recursos
                        Thread.sleep(50); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Iniciar los hilos
        hiloFibonacci.start();
        hiloSuma.start();
    }
}
