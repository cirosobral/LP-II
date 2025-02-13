package aula8;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/* 
 * Essa classe tem por objetivo demonstrar o uso do padrão DAO.
 * 
 * Nesse classe NÃO são utilizados PreparedStatements nem Transações.
 */
public class PessoaDAO {

    private final Connection connection;

    public PessoaDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserir(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO pessoas (nome, data_nascimento) VALUES ('" + pessoa.nome + "', '" + Date.valueOf(pessoa.dataNascimento) + "')";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();

        pessoa.id = rs.getInt(1);

        for (Endereco endereco : pessoa.enderecos) {
            sql = "INSERT INTO endereco (id_pessoa, logradouro, cep) VALUES (" + pessoa.id + ", '" + endereco.logradouro + "', " + endereco.cep + ")";

            stmt.executeUpdate(sql);
        }
    }

    public Pessoa buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pessoas WHERE id = " + id;

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            String nome = rs.getString("nome");
            LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();

            Pessoa p = new Pessoa(id, nome, dataNascimento);

            return p;
        }

        return null;
    }

    public List<Pessoa> listarTodos() throws SQLException {
        String sql = "SELECT * FROM pessoas";
        List<Pessoa> pessoas = new ArrayList<>();

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();

            Pessoa p = new Pessoa(id, nome, dataNascimento);

            pessoas.add(p);
        }

        return pessoas;
    }

    public void atualizar(Pessoa pessoa) throws SQLException {
        String sql = "UPDATE pessoas SET nome = '" + pessoa.nome + "', data_nascimento = '" + Date.valueOf(pessoa.dataNascimento) + "' WHERE id = " + pessoa.id;

        Statement stmt = connection.createStatement();
        int affectedRows = stmt.executeUpdate(sql);

        if (affectedRows == 0) {
            throw new SQLException("ID inexistente");
        }
    }

    public void excluir(Pessoa pessoa) throws SQLException {
        String sql = "DELETE FROM pessoas WHERE id = " + pessoa.id;

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
    }
}
