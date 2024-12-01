package aula5;

import java.io.FileInputStream;
import java.io.IOException;

public class LeituraBinaria {

    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("dados.bin")) {
            int byteLido;
            while ((byteLido = fis.read()) != -1) {
                System.out.println((char) byteLido);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
