package aula5;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class Serializacao {

    public static void main(String[] args) {

        // Objeto criado
        Pessoa p = new Pessoa("Ciro", LocalDate.parse("1986-09-22"));

        System.out.println(p);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pessoa.ser"))) {
            oos.writeObject(p);
        } catch (IOException e) {
            System.out.println("Erro ao serializar o objeto: " + e.getMessage());
        }
    }
}
