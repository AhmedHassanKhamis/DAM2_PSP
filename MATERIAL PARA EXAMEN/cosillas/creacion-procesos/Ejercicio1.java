public class Ejercicio1 {
    public static void main(String[] args) {
        // Si el número de argumentos es menor que 1, salir con código 1.
        if (args.length < 1) {
            System.exit(1);
        }
        
        try {
            // Se intenta convertir el primer argumento a entero.
            int num = Integer.parseInt(args[0]);
            // Si es un número entero menor que 0, salir con código 3.
            if (num < 0) {
                System.exit(3);
            } else {
                // En cualquier otro caso (número entero no negativo), salir con código 0.
                System.exit(0);
            }
        } catch (NumberFormatException e) {
            // Si no es convertible a entero (es una cadena no numérica), salir con código 2.
            System.exit(2);
        }
    }
}
 