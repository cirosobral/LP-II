package aula5;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserializacao {

    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pessoa.ser"))) {
            Pessoa p = (Pessoa) ois.readObject();
            System.out.println(p);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao deserializar o objeto: " + e.getMessage());
        }
    }
}
