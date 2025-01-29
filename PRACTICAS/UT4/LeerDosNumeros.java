import java.util.Scanner;

public class LeerDosNumeros {
    public static void main(String[] args) {
        int num1 = 0, num2 = 0;
        Scanner teclado = new Scanner(System.in);
        try {
            System.out.println("Introduce el primer numero: ");
            num1 = teclado.nextInt();
            System.out.println("Introduce el segundo numero: ");
            num2 = teclado.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("La suma de " + num1 + " y " + num2 + " es " + (num1 + num2));
    }
}
