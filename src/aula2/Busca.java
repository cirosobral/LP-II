package aula2;

import java.util.Scanner;

public class Busca {

    // Método para busca em array
    public static <T> int busca(T[] array, T valor) {
        for (int i = 0; i < array.length; i++) {
            if (valor == array[i]) {
                return i;
            }

        }
        return - 1; // Retorna -1 se o valor não for encontrado
    }

    public static void main(String[] args) {
        System.out.print("Digite o valor a ser encontrado: ");
        Scanner in = new Scanner(System.in);
        int valorProcurado = in.nextInt();

        Integer[] array = {10, 20, 30, 40, 50};
        // String[] array = {"Jose", "João", "Lucas", "Matheus", "Marcos"};
        // String valorProcurado = "Lucas";
        // Pessoa[] array = {new Pessoa("José"), new Pessoa("João"), new Pessoa("Lucas"), new Pessoa("Matheus"), new Pessoa("Marcos")};
        // Pessoa valorProcurado = new Pessoa("Lucas");

        int indice = busca(array, valorProcurado);

        if (indice != -1) {
            System.out.println("Valor encontrado no índice: " + indice);
        } else {

            System.out.println("Valor não encontrado.");
        }
    }
}

class Pessoa {

    String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object obj) {
        return this.nome.equals(((Pessoa) obj).nome);
    }

}
