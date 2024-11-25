package aula4;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class LerComScanner {

    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(Path.of("./teste.txt"));

            while (s.hasNextLine()) {
                String r = s.nextLine();
                System.out.println(r);
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
