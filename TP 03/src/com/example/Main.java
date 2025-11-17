package com.example;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Projeto Pessoa - Menu");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400,200);
            frame.setLocationRelativeTo(null);

            JPanel p = new JPanel();
            JButton bCalc = new JButton("Calculadora");
            JButton bF1 = new JButton("FormPessoa v1");
            JButton bF2 = new JButton("FormPessoa v2");
            JButton bF3 = new JButton("FormPessoa v3");

            bCalc.addActionListener(e -> new CalculadoraForm().setVisible(true));
            bF1.addActionListener(e -> new FormPessoa1().setVisible(true));
            bF2.addActionListener(e -> new FormPessoa2().setVisible(true));
            bF3.addActionListener(e -> new FormPessoa3().setVisible(true));

            p.add(bCalc); p.add(bF1); p.add(bF2); p.add(bF3);

            frame.getContentPane().add(p, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
