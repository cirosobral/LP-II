package aula3;

public class AppCarro {

    public static void main(String[] args) {
        Carro c1 = new Carro();

        try {
            c1.setMarca("Toyota");
            c1.setMarca("");
        } catch (Exception e) {
            try {
                c1.setMarca("Marca desconhecida");
            } catch (Exception e1) {

            }

        }

        c1.setNome("Corolla");

        System.out.println(c1.getMarca() + " " + c1.getNome());
    }
}
