package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CalculadoraForm extends JFrame {

    private JTextField txtNum1 = new JTextField(10);
    private JTextField txtNum2 = new JTextField(10);
    private JTextField txtResultado = new JTextField(15);

    public CalculadoraForm() {
        super("Calculadora");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,200);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.gridx = 0; c.gridy = 0;
        panel.add(new JLabel("Número 1:"), c);
        c.gridx = 1;
        panel.add(txtNum1, c);

        c.gridx = 0; c.gridy = 1;
        panel.add(new JLabel("Número 2:"), c);
        c.gridx = 1;
        panel.add(txtNum2, c);

        c.gridx = 0; c.gridy = 2;
        panel.add(new JLabel("Resultado:"), c);
        c.gridx = 1;
        txtResultado.setEditable(false);
        panel.add(txtResultado, c);

        JPanel btnPanel = new JPanel();
        JButton bAdd = new JButton("+");
        JButton bSub = new JButton("-");
        JButton bMul = new JButton("*");
        JButton bDiv = new JButton("/");
        JButton bClear = new JButton("C");

        bAdd.addActionListener(e -> realizarCalculo('+'));
        bSub.addActionListener(e -> realizarCalculo('-'));
        bMul.addActionListener(e -> realizarCalculo('*'));
        bDiv.addActionListener(e -> realizarCalculo('/'));
        bClear.addActionListener(e -> clear());

        btnPanel.add(bAdd);
        btnPanel.add(bSub);
        btnPanel.add(bMul);
        btnPanel.add(bDiv);
        btnPanel.add(bClear);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(panel, BorderLayout.CENTER);
        cp.add(btnPanel, BorderLayout.SOUTH);
    }

    public void realizarCalculo(char operacao) {
        double num1 = 0;
        double num2 = 0;
        double resultado = 0;

        try {
            num1 = Double.parseDouble(txtNum1.getText());
            num2 = Double.parseDouble(txtNum2.getText());

            switch (operacao) {
                case '+': resultado = num1 + num2; break;
                case '-': resultado = num1 - num2; break;
                case '*': resultado = num1 * num2; break;
                case '/':
                    if (num2 == 0) throw new ArithmeticException("Divisão por zero não é permitida.");
                    resultado = num1 / num2; break;
            }
            txtResultado.setText(String.valueOf(resultado));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Erro de formato: Digite apenas números válidos.",
                "Erro de Entrada",
                JOptionPane.ERROR_MESSAGE);
            txtResultado.setText("");
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(this,
                e.getMessage(),
                "Erro de Cálculo",
                JOptionPane.ERROR_MESSAGE);
            txtResultado.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Ocorreu um erro inesperado: " + e.getMessage(),
                "Erro Geral",
                JOptionPane.ERROR_MESSAGE);
        } finally {
            txtNum1.setText("");
            txtNum2.setText("");
        }
    }

    public void clear() {
        txtNum1.setText("");
        txtNum2.setText("");
        txtResultado.setText("");
    }
}
