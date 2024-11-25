package aula4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LerComBufferedReader {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("./teste.txt"))) {
            while (true) {
                String r = br.readLine();

                if (r == null) {
                    break;
                }

                System.out.println(r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
