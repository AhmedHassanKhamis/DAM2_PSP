
public class Ejercicio4_2 extends Thread {
    Ejercicio4_2(String nom) {
        this.setName(nom);
    }
    
    public void run() {
        System.out.println("Ejecutando [" + getName() + "]");
        for (int i = 1; i <= 5; i++) 
            System.out.println("\t("+getName()+": " + i+")");   
    } 
    
    public static void main(String[] args) {
       Ejercicio4_2 h1 = new Ejercicio4_2("Uno");
       Ejercicio4_2 h2 = new Ejercicio4_2("Dos");
       Ejercicio4_2 h3 = new Ejercicio4_2("Tres");
       Ejercicio4_2 h4 = new Ejercicio4_2("Cuatro");
       Ejercicio4_2 h5 = new Ejercicio4_2("Cinco");    
       //asignamos prioridad
       h1.setPriority(10);
       h2.setPriority(3); 
       h3.setPriority(6);
       h4.setPriority(7); 
       h5.setPriority(1);
       //se ejecutan los hilos
       h1.start();
       h2.start();
       h3.start();
       h4.start();
       h5.start();
    }
}//EjemploHiloPrioridad2
