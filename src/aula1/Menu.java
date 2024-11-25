package aula1;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        menuInicial:
        while (true) {
            System.out.print("1. Cadastrar\n2. Consultar\n3. Sair\nEscolha uma opção: ");
            int opcao = in.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Voce escolheu cadastrar!");
                    break;
                case 2:
                    System.out.println("Voce escolheu consultar!");
                    break;
                case 3:
                    System.out.println("Voce escolheu sair!");
                    break menuInicial;
                default:
                    System.err.println("Opção inválida!");
            }
        }
    }
}
