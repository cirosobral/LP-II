package aula6;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import aula5.Pessoa;

// Classe principal para gerenciar um menu com funcionalidade de salvar/ler dados em um arquivo binário
public class MenuComSalvamento {

    // Constante com o nome do arquivo -- É uma boa prática para evitar errar na digitação do nome do arquivo
    private static final String NOME_ARQUIVO = "pessoas.bin";

    public static void main(String[] args) {
        // Carrega a lista de pessoas do arquivo ao iniciar o programa. Uma vez carregados os dados ficam na memória RAM
        List<Pessoa> pessoas = carregar();

        // Cria o objeto Scanner para capturar entradas do usuário
        Scanner in = new Scanner(System.in);

        while (true) {
            // Apresenta o menu de opções para o usuário
            System.out.println(); // Apresenta uma linha em branco antes do menu
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
                    try {
                        // Caso o usuário escolha cadastrar uma nova pessoa
                        System.out.print("Digite o nome do elemento: ");
                        String nome = in.nextLine();
                        System.out.print("Digite a data de naçimento (aaaa-mm-dd): ");
                        LocalDate data = LocalDate.parse(in.nextLine());

                        // Cria e adiciona a nova pessoa à lista
                        pessoas.add(new Pessoa(nome, data));

                        // Salva a lista atualizada no arquivo
                        salvar(pessoas);
                    } catch (DateTimeParseException e) {
                        // Trata os casos em que a data é inválida
                        System.err.println("Data inválida");
                    }

                    break;
                case "2":
                    // Caso o usuário escolha listar todas as pessoas
                    int idx_pessoa = 0;

                    if (pessoas.isEmpty()) {
                        // Exibe mensagem se não houverem pessoas a serem exibidas
                        System.out.println("\nNão há pessoas a exibir");
                    } else {
                        // Percorre a lista das pessoas e exibe na tela
                        for (Pessoa p : pessoas) {
                            System.out.println("\nPessoa " + ++idx_pessoa);
                            System.out.println(p);
                        }
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
                    in.close();
                    return;
                default:
                    // Caso o usuário digite uma opção inválida
                    System.err.println("Entrada inválida");
            }
        }
    }

    // Método responsável por entregar a lista de pessoas, seja carregando do arquivo ou criando um novo ArrayList
    private static List<Pessoa> carregar() {
        List<Pessoa> pessoas = null;

        // Tenta abrir o arquivo para leitura
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO))) {
            // Lê a lista de pessoas do arquivo
            pessoas = (ArrayList<Pessoa>) ois.readObject();

            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            // Caso haja erro (por exemplo, arquivo não existir), cria uma nova lista vazia
            pessoas = new ArrayList<>();
        }

        return pessoas;
    }

    // Método responsável por salvar a lista de pessoas no arquivo
    private static void salvar(List<Pessoa> pessoas) {
        // Tenta abrir o arquivo para escrita
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pessoas.bin"))) {
            // Serializa e salva a lista no arquivo
            oos.writeObject(pessoas);

            oos.close();
        } catch (IOException e) {
            // Trata erros durante o processo de escrita no arquivo
            System.out.println("Erro ao serializar o objeto: " + e.getMessage());
        }
    }
}
