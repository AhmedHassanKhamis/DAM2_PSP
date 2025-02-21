public class AdivinaNumeroJuego {
    public static void main(String[] args) {
        // Definimos el número de jugadores (puedes cambiarlo si lo deseas)
        int numeroJugadores = 3;
        Arbitro arbitro = new Arbitro(numeroJugadores);

        // Creamos y lanzamos los hilos de cada jugador
        Jugador[] jugadores = new Jugador[numeroJugadores];
        for (int i = 0; i < numeroJugadores; i++) {
            jugadores[i] = new Jugador(i + 1, arbitro);
            jugadores[i].start();
        }
    }
}

// Clase que representa al árbitro del juego
class Arbitro {
    private int numJugadores;
    private int turno;              // Número de jugador que tiene el turno
    private int numeroAdivinar;     // Número secreto a adivinar
    private boolean juegoTerminado;

    public Arbitro(int numJugadores) {
        this.numJugadores = numJugadores;
        this.turno = 1;
        this.juegoTerminado = false;
        // Genera un número aleatorio entre 1 y 10
        this.numeroAdivinar = 1 + (int) (10 * Math.random());
        System.out.println("Número a adivinar (secreto): " + numeroAdivinar);
    }

    // Devuelve el turno actual
    public synchronized int getTurno() {
        return turno;
    }

    // Indica si el juego ha finalizado
    public synchronized boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    // Método synchronized para que solo un jugador haga la jugada a la vez
    public synchronized void hacerJugada(int id, int jugada) {
        // Si el juego ya terminó, no se hace nada
        if (juegoTerminado) {
            return;
        }
        System.out.println("Jugador " + id + " juega: " + jugada);
        if (jugada == numeroAdivinar) {
            System.out.println("¡Jugador " + id + " ha acertado el número!");
            juegoTerminado = true;
        } else {
            System.out.println("Jugador " + id + " ha fallado.");
            // Actualizamos el turno: se pasa al siguiente jugador (de manera cíclica)
            turno = (turno % numJugadores) + 1;
            System.out.println("Es el turno del jugador " + turno);
        }
        // Notifica a todos los hilos que están esperando
        notifyAll();
    }
}

// Clase que representa a cada jugador (extiende de Thread)
class Jugador extends Thread {
    private int id;
    private Arbitro arbitro;

    public Jugador(int id, Arbitro arbitro) {
        this.id = id;
        this.arbitro = arbitro;
    }

    @Override
    public void run() {
        // Cada jugador intenta jugar mientras el juego no haya terminado
        while (!arbitro.isJuegoTerminado()) {
            synchronized (arbitro) {
                // Si no es el turno de este jugador, se pone a la espera
                while (!arbitro.isJuegoTerminado() && arbitro.getTurno() != id) {
                    try {
                        arbitro.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // Verificamos nuevamente si el juego ha finalizado para salir del ciclo
                if (arbitro.isJuegoTerminado()) {
                    break;
                }
                // Genera un número aleatorio entre 1 y 10 como jugada
                int jugada = 1 + (int) (10 * Math.random());
                // El jugador realiza la jugada
                arbitro.hacerJugada(id, jugada);
            }
            // Pequeño retardo para simular el tiempo de juego y permitir el cambio de turno
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Jugador " + id + " termina su ejecución.");
    }
}
