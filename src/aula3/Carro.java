package aula3;

public class Carro {

    private String nome;
    private String marca;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMarca(String marca) throws Exception {
        if (marca.equals("")) {
            throw new Exception("A marca não pode ser vazia");
        }

        this.marca = marca;
    }

    public String getNome() {
        return this.nome;
    }

    public String getMarca() {
        return this.marca;
    }

}
