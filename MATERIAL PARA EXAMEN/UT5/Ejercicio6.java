import java.util.Random;

class Saldo {
    private int saldo;

    public Saldo(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized void añadirSaldo(String nombre, int cantidad) {
        int saldoInicial = saldo;
        System.out.println(nombre + " añade " + cantidad + " al saldo.");
        System.out.println("Saldo antes de la operación: " + saldoInicial);
        saldo += cantidad;
        System.out.println("Saldo después de la operación: " + saldo);
        System.out.println("-----------------------------");
    }

    public int getSaldo() {
        try {
            Thread.sleep(new Random().nextInt(500)); // Simula retraso
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return saldo;
    }

    public void setSaldo(int saldo) {
        try {
            Thread.sleep(new Random().nextInt(500)); // Simula retraso
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.saldo = saldo;
    }
}

class HiloSaldo extends Thread {
    private Saldo saldo;
    private int cantidad;

    public HiloSaldo(Saldo saldo, String nombre, int cantidad) {
        super(nombre);
        this.saldo = saldo;
        this.cantidad = cantidad;
    }

    public void run() {
        saldo.añadirSaldo(getName(), cantidad);
    }
}

public class Ejercicio6 {
    public static void main(String[] args) {
        Saldo saldoCompartido = new Saldo(1000);
        System.out.println("Saldo inicial: " + saldoCompartido.getSaldo());

        HiloSaldo h1 = new HiloSaldo(saldoCompartido, "Hilo 1", 500);
        HiloSaldo h2 = new HiloSaldo(saldoCompartido, "Hilo 2", 300);
        HiloSaldo h3 = new HiloSaldo(saldoCompartido, "Hilo 3", 700);
        HiloSaldo h4 = new HiloSaldo(saldoCompartido, "Hilo 4", 400);
        HiloSaldo h5 = new HiloSaldo(saldoCompartido, "Hilo 5", 600);

        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();

        try {
            h1.join();
            h2.join();
            h3.join();
            h4.join();
            h5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Saldo final: " + saldoCompartido.getSaldo());
    }
}
