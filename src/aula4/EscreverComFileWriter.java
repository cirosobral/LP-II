package aula4;

import java.io.FileWriter;
import java.io.IOException;

public class EscreverComFileWriter {

    public static void main(String[] args) {
        try (FileWriter fw = new FileWriter("./teste.txt")) {
            fw.write("Isso é uma merda!");
            fw.append("\nTnc!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
