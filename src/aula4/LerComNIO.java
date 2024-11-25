package aula4;

import java.io.IOException;
import java.nio.file.*;

public class LerComNIO {

    public static void main(String[] args) {
        try {
            Files.readAllLines(Path.of("./teste.txt")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
