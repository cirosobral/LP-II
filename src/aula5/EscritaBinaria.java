package aula5;

import java.io.FileOutputStream;
import java.io.IOException;

public class EscritaBinaria {

    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("dados.bin")) {
            fos.write(65); // Escreve o byte correspondente ao caractere 'A'  
            fos.write(66); // Escreve 'B'  
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}
