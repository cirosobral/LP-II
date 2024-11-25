package aula1;

import java.util.Scanner;

public class RecepcionistaIfElse {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int hora = in.nextInt();
        if (hora < 6) {
            System.out.println("Boa madrugada!");
        } else if (hora < 12) {
            System.out.println("Bom dia!");
        } else if (hora < 18) {
            System.out.println("Boa tarde!");
        } else if (hora < 24) {
            System.out.println("Boa noite!");
        } else {
            System.err.println("Hora inválida!");
        }
    }
}
