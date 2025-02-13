package aula8;

import java.sql.*;

/*
 * Esse classe tem por objetivo centralizar as principais
 * funcionalidades de gerenciamento do banco de dados.
 * 
 * Nesse classe iremos:
 * - Gerenciar a criação das conexões;
 * - Criar o banco de dados;
 * - Criar as tabelas do banco de dados;
 * 
 * O código de vocês NÃO PRECISA ter uma classe desse tipo.
 * 
 * Basta que vocês criem o banco de dados antes de usar a
 * aplicação.
 */
public class Conexao {

    // ! ATENÇÃO, PRESTE MUITA ATENÇÃO! A SENHA PODE SER DIFERENTE, ALTERE CASO NECESSÁRIO !
    private static String USERNAME = "root";
    private static String PASSWORD = "root";
    private static String DATABASE = "lpii";

    public static Connection getRootConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/", USERNAME, PASSWORD);
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE, USERNAME, PASSWORD);
    }

    // Função para criar o banco de dados
    public static void createDB(Connection c) throws SQLException {
        Statement stmt = c.createStatement();
        stmt.executeUpdate("DROP DATABASE IF EXISTS `" + DATABASE + "`;");
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS `" + DATABASE + "` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;");
        c.commit();
    }

    // Função para criar as tabelas no banco de dados
    public static void createTables(Connection c) throws SQLException {
        Statement stmt = c.createStatement();

        stmt.executeUpdate("USE `" + DATABASE + "`;");
        stmt.executeUpdate("CREATE TABLE `pessoas` (`id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT, `nome` varchar(255) NOT NULL, `data_nascimento` DATE NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
        stmt.executeUpdate("CREATE TABLE `endereco` (`id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT, `id_pessoa` int(11) NOT NULL, `logradouro` varchar(255) NOT NULL, `cep` int(11) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
        stmt.executeUpdate("ALTER TABLE `endereco` ADD KEY `id_pessoa_idx` (`id_pessoa`), ADD CONSTRAINT `id_pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;");
        c.commit();
    }

    public static boolean createStructure() {
        try {
            Connection c = getRootConnection();
            c.setAutoCommit(false);

            createDB(c);
            createTables(c);

            return true;
        } catch (SQLException e) {
            System.err.println("Não foi possível criar a estrutura do banco de dados");

            e.printStackTrace();

            return false;
        }
    }

}
