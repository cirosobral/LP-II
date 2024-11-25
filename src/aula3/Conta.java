package aula3;

// Figura 3.1: Account.java
// Classe Account que contém uma variável de instância name 
// e métodos para configurar e obter seu valor.
class Conta {

    private String name; // variável de instância

// método para definir o nome no objeto 
    public void setName(String name) throws Exception {
        if (name.equals("disponivel") || name.equals("indisponivel")) {
            this.name = name;
        } else {
            throw new Exception("O status só pode ser 'disponpivel' ou 'indisponivel'.");
        }
        if (name.equals("")) {
            
        }
        this.name = name; // armazena o nome 
    }
// método para recuperar o nome do objeto 

    public String getName() {
        return name; // retorna valor do nome para o chamador
    }

    @Override
    public String toString() {
        return name;
    }

} // fim da classe Account
