package aula9;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.*;

public class Telinha extends JFrame {

    public Telinha(String title, int botoes) {
        super();
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        double ratio = Double.valueOf(HEIGHT) / WIDTH;
        int colunas = (int) Math.sqrt(ratio * botoes);
        int linhas = (int) Math.ceil(Double.valueOf(botoes) / colunas);

        System.out.println("---\nO layout terá " + linhas + " linhas e " + colunas + " colunas\n---");

        GridLayout gl = new GridLayout(linhas, colunas);

        setLayout(gl);

        for (int i = 0; i < botoes; i++) {
            JButton jb = new javax.swing.JButton("Botão " + (i + 1));
            jb.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    System.out.println(jb.getText());
                }

            });
            System.out.println("Adicionado botao " + (i + 1));
            add(jb);
        }

        setVisible(true);
        setSize(600, 400);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Diga qual o nome você quer para a telinha: ");
        String nome = scanner.nextLine();

        System.out.println("Diga quantos botões você quer: ");
        int botoes = scanner.nextInt();

        new Telinha(nome, botoes);
    }

}
