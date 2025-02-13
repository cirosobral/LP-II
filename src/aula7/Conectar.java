package aula7;

import java.sql.*;

public class Conectar {

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lp2", "root", "root")) {
            String sql = "INSERT INTO pessoas (nome, data_nascimento) VALUES ('fulano', '1900-01-01')";

            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Um erro aconteceu.");
            e.printStackTrace();
        }
    }
}
