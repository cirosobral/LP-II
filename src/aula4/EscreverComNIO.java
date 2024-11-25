package aula4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class EscreverComNIO {

    public static void main(String[] args) {
        try {
            Files.writeString(Path.of("./teste.txt"), "Isso é uma merda com NIO\ntnc!", StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
