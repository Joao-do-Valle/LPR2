package com.example;

import javax.swing.*;
import java.awt.*;

public class FormPessoa2 extends JFrame {

    private Pessoa UmaPessoa = null;

    private JTextField txtNumero = new JTextField(5);
    private JTextField txtNome = new JTextField(15);
    private JComboBox<String> cmbSexo = new JComboBox<>(new String[] { "M", "F" });
    private JTextField txtIdade = new JTextField(5);

    public FormPessoa2() {
        super("FormPessoa - Versão 2 (JComboBox)");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,220);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);

        c.gridx=0; c.gridy=0; p.add(new JLabel("Número:"), c);
        c.gridx=1; p.add(txtNumero, c);
        txtNumero.setEditable(false);

        c.gridx=0; c.gridy=1; p.add(new JLabel("Nome:"), c);
        c.gridx=1; p.add(txtNome, c);

        c.gridx=0; c.gridy=2; p.add(new JLabel("Sexo:"), c);
        c.gridx=1; p.add(cmbSexo, c);

        c.gridx=0; c.gridy=3; p.add(new JLabel("Idade:"), c);
        c.gridx=1; p.add(txtIdade, c);

        JButton btnOk = new JButton("OK");
        JButton btnMostrar = new JButton("Mostrar");

        btnOk.addActionListener(e -> {
            okButtonAction(txtNome.getText(), cmbSexo.getSelectedItem().toString(), txtIdade.getText());
            txtNumero.setText(String.valueOf(Pessoa.getKp()));
        });

        btnMostrar.addActionListener(e -> mostrarButtonAction());

        JPanel bp = new JPanel();
        bp.add(btnOk); bp.add(btnMostrar);

        getContentPane().add(p, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.SOUTH);
    }

    private boolean validarCampos(String nome, String sexo, String idadeStr) {
        if (nome.trim().isEmpty() || sexo.trim().isEmpty() || idadeStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Os campos Nome, Sexo e Idade são de preenchimento obrigatório.",
                "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            int idade = Integer.parseInt(idadeStr);
            if (idade <= 0) {
                JOptionPane.showMessageDialog(this, "Idade deve ser um número positivo.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            char sexoChar = Character.toUpperCase(sexo.charAt(0));
            if (sexoChar != 'M' && sexoChar != 'F') {
                JOptionPane.showMessageDialog(this, "O campo Sexo só deve aceitar 'M' ou 'F'.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Idade deve ser um número inteiro válido.", "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void okButtonAction(String nome, String sexo, String idadeStr) {
        if (validarCampos(nome, sexo, idadeStr)) {
            if (UmaPessoa == null) {
                UmaPessoa = new Pessoa();
            }
            UmaPessoa.setNome(nome);
            UmaPessoa.setSexo(sexo.charAt(0));
            UmaPessoa.setIdade(Integer.parseInt(idadeStr));
            JOptionPane.showMessageDialog(this, "Dados transferidos para UmaPessoa com sucesso.");
        }
    }

    public void mostrarButtonAction() {
        if (UmaPessoa != null) {
            JOptionPane.showMessageDialog(this, UmaPessoa.toString(), "Dados da Pessoa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum objeto Pessoa foi criado (Pressione OK primeiro).");
        }
    }
}
