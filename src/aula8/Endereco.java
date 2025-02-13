package aula8;

public class Endereco {

    int id;
    Pessoa pessoa;
    String logradouro;
    int cep;

    public Endereco(Pessoa pessoa, String logradouro, int cep) {
        this.pessoa = pessoa;
        this.logradouro = logradouro;
        this.cep = cep;

        pessoa.enderecos.add(this);
    }

}
