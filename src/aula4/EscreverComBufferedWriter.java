package aula4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscreverComBufferedWriter {

    public static void main(String[] args) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./teste.txt"))) {
            bw.write("Isso é uma merda com BW!");
            bw.append("\nTnc!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
