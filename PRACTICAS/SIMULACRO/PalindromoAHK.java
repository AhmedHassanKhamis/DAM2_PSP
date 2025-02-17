import java.util.Scanner;

public class PalindromoAHK {
    public static void main(String[] args) {
        String palabraAHK;
        System.out.println("Introduce una palabra o cadena:");
        Scanner tecladoAHK = new Scanner(System.in);
        StringBuilder reverseAHK = new StringBuilder();
        palabraAHK = tecladoAHK.nextLine();
        reverseAHK.append(palabraAHK);
        reverseAHK.reverse();
        if (palabraAHK.equals(reverseAHK.toString())) {
            System.out.println("Es palíndroma:"+ reverseAHK.toString());
        }else{
            System.out.println("no es palindroma" + reverseAHK.toString());
        }
    }
}