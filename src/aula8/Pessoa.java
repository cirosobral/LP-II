package aula8;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Pessoa {

    int id;
    String nome;
    LocalDate dataNascimento;
    int idade;
    List<Endereco> enderecos;

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.idade = calcularIdade();
        this.enderecos = new ArrayList<>();
    }

    public Pessoa(int id, String nome, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.idade = calcularIdade();
        this.enderecos = new ArrayList<>();
    }

    private int calcularIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "nome: " + nome + "\nidade: " + idade;
    }
}
