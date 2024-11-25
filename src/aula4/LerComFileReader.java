package aula4;

import java.io.FileReader;
import java.io.IOException;

public class LerComFileReader {

    public static void main(String[] args) {
        try (FileReader fr = new FileReader("./teste.txt")) {
            while (true) {
                try {
                    int r = fr.read();

                    if (r == -1) {
                        break;
                    }

                    System.out.print((char) r);
                } catch (IOException e) {
                    break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
