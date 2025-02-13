package aula8;

import java.sql.*;
import java.util.Scanner;

/* 
 * Essa classe tem por objetivo demonstrar o uso do conceito de TRANSAÇÕES.
 * 
 * Nesse classe NÃO são utilizados PreparedStatements nem o Padrão DAO.
 */
public class Transacao {

    public static void main(String[] args) {

        // Primeiro TRY apenas para gerenciar os erros da conexão
        try (Connection c = Conexao.getConnection()) {
            // Desativa o auto-commit
            c.setAutoCommit(false);

            // Cria o banco de dados e as tabelas
            Conexao.createDB(c);
            Conexao.createTables(c);

            // Segundo TRY para gerenciar os erros das transações
            try {
                // Define uma declaração (Statement)
                Statement stmt = c.createStatement();

                // Insere um registro na tabela usuário com ID = 1
                String sql = "INSERT INTO pessoas VALUES (1, 'abc', '2002-02-20')";
                stmt.executeUpdate(sql);

                // Recupera os registros da tabela usuário (apenas 1)
                sql = "SELECT * FROM pessoas";
                ResultSet rs = stmt.executeQuery(sql);

                // Exibe o registro recuperado
                while (rs.next()) {
                    System.out.println("ID\t: " + rs.getString(1));
                    System.out.println("Nome\t: " + rs.getString(2));
                    System.out.println("Data de Nascimento\t: " + rs.getString(3));
                    System.out.println();
                }

                Scanner in = new Scanner(System.in);

                System.out.println("Digite 0 para operação inválida e 1 para operação válida");

                laco:
                while (true) {
                    switch (in.nextInt()) {
                        case 0:
                            sql = "INSERT INTO endereco VALUES (1, 2, 'Rua X', '45600000')";
                            break laco;
                        case 1:
                            sql = "INSERT INTO endereco VALUES (1, 1, 'Rua X', '45600000')";
                            break laco;
                        default:
                            System.err.println("Opção inválida");
                    }
                }

                // Realiza a operação de inserção
                stmt.executeUpdate(sql);

                // Confirma as operações CASO não tenha ocorrido nenhuma exceção
                c.commit();
            } catch (SQLException exTransacoes) {
                // Desfaz as operações CASO tenha ocorrido alguma exceção
                c.rollback();
                System.err.println("Volta que deu merda!");
            } finally {
                c.close();
            }
        } catch (SQLException exConexao) {
            System.err.println("Falha na conexão com o banco de dados");
        }

    }

}
