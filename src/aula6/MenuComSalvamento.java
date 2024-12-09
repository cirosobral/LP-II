package aula6;

import aula5.Pessoa;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

// Classe principal para gerenciar um menu com funcionalidade de salvar/ler dados em um arquivo binário
public class MenuComSalvamento {

    // Constante com o nome do arquivo -- É uma boa prática para evitar errar na digitação do nome do arquivo
    private static String NOME_ARQUIVO = "pessoas.bin";

    public static void main(String[] args) {
        // Carrega a lista de pessoas do arquivo ao iniciar o programa. Uma vez carregados os dados ficam na memória RAM
        List<Pessoa> pessoas = carregar();

        while (true) {
            // Cria o objeto Scanner para capturar entradas do usuário
            Scanner in = new Scanner(System.in);

            // Apresenta o menu de opções para o usuário
            System.out.println("1 - Cadastrar pessoa");
            System.out.println("2 - Listar pessoas");
            System.out.println("3 - Excluir pessoa");
            System.out.println("4 - Sair");
            System.out.print("Digite sua opção: ");

            // Lê a entrada do usuário
            String entrada = in.nextLine();

            // Processa a opção escolhida pelo usuário
            switch (entrada) {
                case "1":
                    // Caso o usuário escolha cadastrar uma nova pessoa
                    System.out.println("Digite o nome do elemento: ");
                    String nome = in.nextLine();
                    System.out.println("Digite a data de naçimento (aaaa-mm-dd): ");
                    LocalDate data = LocalDate.parse(in.nextLine());

                    // Cria e adiciona a nova pessoa à lista
                    pessoas.add(new Pessoa(nome, data));

                    // Salva a lista atualizada no arquivo
                    salvar(pessoas);

                    break;
                case "2":
                    // Caso o usuário escolha listar todas as pessoas
                    int idx_pessoa = 0;
                    for (Pessoa p : pessoas) {
                        System.out.println("Pessoa " + ++idx_pessoa);
                        System.out.println(p);
                        System.out.println();
                    }

                    break;
                case "3":
                    // Caso o usuário escolha excluir uma pessoa da lista
                    System.out.print("Digite o índice da pessoa: ");

                    try {
                        // Lê o índice da pessoa a ser excluída
                        idx_pessoa = Integer.parseInt(in.nextLine());
                        pessoas.remove(idx_pessoa - 1);

                        // Salva a lista atualizada no arquivo
                        salvar(pessoas);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        // Trata casos em que o índice é inválido
                        System.err.println("Índice inválido");
                    }

                    break;
                case "4":
                    // Caso o usuário escolha sair do programa
                    return;
                default:
                    // Caso o usuário digite uma opção inválida
                    System.err.println("Entrada inválida");
            }
        }
    }

    // Método responsável por entregar a lista de pessoas, seja carregando do arquivo ou criando um novo ArrayList
    private static List<Pessoa> carregar() {
        List<Pessoa> pessoas;

        try {
            // Tenta abrir o arquivo para leitura
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO));

            // Lê a lista de pessoas do arquivo
            pessoas = (ArrayList<Pessoa>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Caso haja erro (por exemplo, arquivo não existir), cria uma nova lista vazia
            pessoas = new ArrayList<Pessoa>();
        }

        return pessoas;
    }

    // Método responsável por salvar a lista de pessoas no arquivo
    private static void salvar(List<Pessoa> pessoas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pessoas.bin"))) {
            // Serializa e salva a lista no arquivo
            oos.writeObject(pessoas);
        } catch (IOException e) {
            // Trata erros durante o processo de escrita no arquivo
            System.out.println("Erro ao serializar o objeto: " + e.getMessage());
        }
    }
}
