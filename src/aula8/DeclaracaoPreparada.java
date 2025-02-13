package aula8;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

/* 
 * Essa classe tem por objetivo demonstrar o uso da classe PreparedStatement.
 * 
 * Nesse classe NÃO são utilizadas Transações nem o Padrão DAO.
 */
public class DeclaracaoPreparada {

    public static void main(String[] args) {
        try {
            // Estabelece uma conexão com o banco de dados utilizando a classe Conexao
            Connection c = Conexao.getConnection();
            Scanner in = new Scanner(System.in);  // Cria um scanner para ler dados do usuário

            // Solicita os dados do usuário para preencher as informações de uma nova pessoa
            System.out.println("Digite os dados a 'seguir");
            System.out.print("Nome: ");
            String nome = in.nextLine();  // Lê o nome fornecido pelo usuário
            System.out.print("Data de nascimento (aaaa-mm-dd): ");
            LocalDate data = LocalDate.parse(in.nextLine());  // Lê e converte a data de nascimento para LocalDate

            // Cria um objeto Pessoa com os dados fornecidos
            Pessoa p = new Pessoa(nome, data);

            // Define a consulta SQL para inserir os dados de uma nova pessoa na tabela "pessoas"
            String sql = "INSERT INTO pessoas (nome, data_nascimento) VALUES (?, ?)";

            // Prepara a declaração SQL usando PreparedStatement, passando a consulta SQL e a opção de retornar a chave gerada
            PreparedStatement pstmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Atribui os valores dos parâmetros (nome e data de nascimento) na consulta SQL
            pstmt.setString(1, p.nome);
            pstmt.setDate(2, Date.valueOf(p.dataNascimento));  // Converte LocalDate para Date para compatibilidade com SQL

            // Executa a atualização no banco de dados
            pstmt.executeUpdate();

            // Recupera a chave gerada (ID da pessoa inserida) após a execução da inserção
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();  // Move o cursor para a primeira linha do resultado
            p.id = rs.getInt(1);  // Atribui o ID gerado à pessoa

            // Solicita e lê o endereço da pessoa
            System.out.print("Endereço: ");
            String endereco = in.nextLine();  // Lê o endereço fornecido pelo usuário
            System.err.print("CEP: ");
            int cep = Integer.parseInt(in.nextLine().replaceAll("\\D", ""));  // Lê o CEP e remove qualquer caractere não numérico

            // Cria um objeto Endereco associando a pessoa e o endereço
            Endereco e = new Endereco(p, endereco, cep);

            // Define a consulta SQL para inserir o endereço na tabela "endereco"
            sql = "INSERT INTO endereco (id_pessoa, logradouro, cep) VALUES (?, ?, ?)";

            // Prepara a declaração SQL para inserir o endereço no banco de dados
            pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, e.pessoa.id);  // Atribui o ID da pessoa à primeira coluna da consulta
            pstmt.setString(2, e.logradouro);  // Atribui o endereço à segunda coluna
            pstmt.setInt(3, cep);  // Atribui o CEP à terceira coluna

            // Executa a atualização no banco de dados para inserir o endereço
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Em caso de erro, exibe a pilha de exceções para depuração
            e.printStackTrace();
        }

    }
}
