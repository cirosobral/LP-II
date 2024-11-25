package aula3;

public class App {

    public static void main(String[] args) {
        Banco b = new Banco();

        b.criarConta("Ciro");

        Conta c = b.localizaConta("Ciro");

        if (c == null) {
            System.err.println("NÃO EXISTE ESSA CONTA");
        } else {
            System.out.println(c);
        }

    }
}
