package aula3;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private List<Conta> contas = new ArrayList<>();

    public void criarConta(String nome) {
        Conta conta = new Conta();
        try {
            conta.setName(nome);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        contas.add(conta); // Adicionar
    }

    public int numeroDeContas() {
        // Retorne o número de contas criadas
        return contas.size();
    }

    public Conta localizaConta(String nome) {
        // Localize a conta com base no nome:
        // - Caso a conta exista, retorne a conta
        // - Caso não exista, retorne nulo
        for (Conta elem : contas) {
            if (elem.getName().equals(nome)) {
                return elem;
            }
        }

        return null;
    }

}
