package aula8;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

// Classe principal para gerenciar um menu com funcionalidade de salvar/ler dados em um arquivo binário
public class MenuComBD {

    public static void main(String[] args) {
        try {
            // Cria o objeto PessoaDAO passando para ele uma conexão com o banco de dados
            PessoaDAO pessoaDAO = new PessoaDAO(Conexao.getConnection());

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

                            // Cria e adiciona a nova pessoa ao banco de dados
                            pessoaDAO.inserir(new Pessoa(nome, data));
                        } catch (DateTimeParseException e) {
                            // Trata os casos em que a data é inválida
                            System.err.println("Data inválida");
                        }

                        break;
                    case "2":
                        // Caso o usuário escolha listar todas as pessoas
                        int idx_pessoa = 0;

                        // Pega a lista de todas as pessoas no banco de dados e verifica se está vazia
                        if (pessoaDAO.listarTodos().isEmpty()) {
                            // Exibe mensagem se não houverem pessoas a serem exibidas
                            System.out.println("\nNão há pessoas a exibir");
                        } else {
                            // Percorre a lista das pessoas e exibe na tela
                            for (Pessoa p : pessoaDAO.listarTodos()) {
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
                            Pessoa p = pessoaDAO.listarTodos().get(idx_pessoa - 1);

                            // Exclui a pessoa do banco de dados
                            pessoaDAO.excluir(p);
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
        } catch (SQLException e) {
            System.err.println("Falha na conexão com o banco de dados");
            System.err.println("Tentando criar a estrutura do banco de dados");
            if (Conexao.createStructure()) {
                System.err.println("Estrutura criada com sucesso");
            }
        }
    }

}
