public class Ejercicio2AHK {
    public static void main(String[] args) {
        // Definimos el número de jugadores (puedes cambiarlo si lo deseas)
        int numeroCajeros = 5;
        CuentaAHK cuentaAHK = new CuentaAHK(numeroCajeros);

        // Creamos y lanzamos los hilos de cada jugador
        CajeroAHK[] cajeros = new CajeroAHK[numeroCajeros];
        for (int i = 0; i < numeroCajeros; i++) {
            cajeros[i] = new CajeroAHK(i + 1, cuentaAHK);
            cajeros[i].start();
        }
    }
}

// Clase que representa al árbitro del juego
class CuentaAHK {
    private int saldo;
    private int turno;              // Número de jugador que tiene el turno
    private int numCajeros;     // Número secreto a adivinar
    private boolean saldoSuficiente;

    public CuentaAHK(int numCajeros) {
        this.numCajeros = numCajeros;
        this.turno = 1;
        this.saldoSuficiente = false;
        this.saldo = 1000;
    }

    // Devuelve el saldo actual
    public synchronized int getSaldo() {
        return saldo;
    }

     // Devuelve el turno actual
    public synchronized int getTurno() {
        return turno;
    }

    // Indica si hay saldo suficiente
    public synchronized boolean saldoSuficienteActual() {
        if(saldo <= 0){
            saldoSuficiente = false;
            return false;
        }else{
            saldoSuficiente = true;
            return true;
        }
        
    }

    public synchronized void retirar(int id, int cantidad) {
        if (saldoSuficiente == false) {
            return;
        }
        if (cantidad <= saldo) {
            System.out.println("Cajero " + id + " retiró: " + cantidad + "€. Saldo actual: "+ saldo);
            if(saldo == cantidad){
                saldoSuficiente = false;
            }else{
                saldoSuficiente = true;
            }
            saldo -= cantidad;            
        } else {
            System.out.println("Cajero " + id + " retiró: " + cantidad + "€. Saldo insuficiente");
        }
        turno = (turno % numCajeros) + 1;
        // Notifica a todos los hilos que están esperando
        notifyAll();
    }

    public synchronized void depositar(int id, int cantidad) {
        if (cantidad <= 0) {
            return;
        }
        System.out.println("Cajero " + id + " depositó: " + cantidad + "€. Saldo actual: "+ saldo);
        saldo += cantidad;            
        turno = (turno % numCajeros) + 1;
        // Notifica a todos los hilos que están esperando
        notifyAll();
    }
}

// Clase que representa a cada cajero (extiende de Thread)
class CajeroAHK extends Thread {
    private int id;
    private CuentaAHK cuentaAHK;

    public CajeroAHK(int id, CuentaAHK cuentaAHK) {
        this.id = id;
        this.cuentaAHK = cuentaAHK;
    }

    @Override
    public void run() {
        // Cada jugador intenta jugar mientras el juego no haya terminado
        int veces = 10;
        while (cuentaAHK.saldoSuficienteActual() && veces > 0) {
            veces--;
            synchronized (cuentaAHK) {
                // Si no es el turno de este jugador, se pone a la espera
                while (cuentaAHK.saldoSuficienteActual() && cuentaAHK.getTurno() != id) {
                    try {
                        cuentaAHK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // Verificamos nuevamente si el juego ha finalizado para salir del ciclo
                if (!cuentaAHK.saldoSuficienteActual()) {
                    break;
                }
                // Genera un número aleatorio entre 1 y 100 como cantidad
                int cantidad = 1 + (int) (100 * Math.random());
                // El jugador realiza la cantidad
                if(id > 3){
                    cuentaAHK.depositar(id, cantidad);
                }else{
                    cuentaAHK.retirar(id, cantidad);
                }
                
            }
            // Pequeño retardo para simular el tiempo de juego y permitir el cambio de turno
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Cajero " + id + " termina su ejecución.");
    }
}
