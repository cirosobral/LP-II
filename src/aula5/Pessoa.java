package aula5;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;

public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nome;
    private LocalDate dataNascimento;
    private transient int idade;

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.idade = calcularIdade();
    }

    private int calcularIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        // Chama a implementação padrão
        in.defaultReadObject();
        // Reconfigura o campo transiente
        this.idade = calcularIdade(); // Chama o método para calcular a idade
    }

    @Override
    public String toString() {
        return "nome: " + nome + "\nidade: " + idade;
    }
}
