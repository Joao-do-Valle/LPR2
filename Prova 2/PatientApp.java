import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PatientApp {
    private JFrame frame;
    private JTextField txtNome, txtIdade, txtPeso, txtAltura;
    private Database db;

    public PatientApp() {
        db = new Database();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Cadastro de Pacientes - Trabalho prático");
        frame.setBounds(100, 100, 520, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0; c.gridy = 0;
        form.add(new JLabel("Nome:"), c);
        c.gridx = 1;
        txtNome = new JTextField(25);
        form.add(txtNome, c);

        c.gridx = 0; c.gridy = 1;
        form.add(new JLabel("Idade:"), c);
        c.gridx = 1;
        txtIdade = new JTextField(10);
        form.add(txtIdade, c);

        c.gridx = 0; c.gridy = 2;
        form.add(new JLabel("Peso (kg):"), c);
        c.gridx = 1;
        txtPeso = new JTextField(10);
        form.add(txtPeso, c);

        c.gridx = 0; c.gridy = 3;
        form.add(new JLabel("Altura (m):"), c);
        c.gridx = 1;
        txtAltura = new JTextField(10);
        form.add(txtAltura, c);

        frame.add(form, BorderLayout.CENTER);

        JPanel buttons = new JPanel();

        JButton btnIncluir = new JButton("Incluir");
        btnIncluir.addActionListener(e -> onIncluir());
        buttons.add(btnIncluir);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(e -> onLimpar());
        buttons.add(btnLimpar);

        JButton btnApresenta = new JButton("Apresenta Dados");
        btnApresenta.addActionListener(e -> onApresenta());
        buttons.add(btnApresenta);

        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.addActionListener(e -> onPesquisar());
        buttons.add(btnPesquisar);

        JButton btnCreditos = new JButton("Créditos");
        btnCreditos.addActionListener(e -> onCreditos());
        buttons.add(btnCreditos);

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> System.exit(0));
        buttons.add(btnSair);

        frame.add(buttons, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void onIncluir() {
        String nome = txtNome.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nome é obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int idade;
        double peso, altura;
        try {
            idade = Integer.parseInt(txtIdade.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Idade deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            peso = Double.parseDouble(txtPeso.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Peso deve ser um número (float).", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            altura = Double.parseDouble(txtAltura.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Altura deve ser um número (float).", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean ok = db.insertPatient(nome, idade, peso, altura);
        if (ok) {
            JOptionPane.showMessageDialog(frame, "Paciente incluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            onLimpar();
        } else {
            JOptionPane.showMessageDialog(frame, "Erro ao inserir paciente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onLimpar() {
        txtNome.setText("");
        txtIdade.setText("");
        txtPeso.setText("");
        txtAltura.setText("");
    }

    private void onApresenta() {
        List<String> all = db.getAllPatients();
        if (all.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nenhum paciente cadastrado.", "Dados", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String s : all) {
            sb.append(s).append("\n");
        }
        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(480, 300));
        JOptionPane.showMessageDialog(frame, scroll, "Todos os Pacientes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void onPesquisar() {
        String nome = JOptionPane.showInputDialog(frame, "Digite parte do nome para pesquisar (LIKE):");
        if (nome == null) return; // cancelou
        nome = nome.trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Pesquisa vazia.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        List<String> res = db.searchByName(nome);
        if (res.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nenhum resultado encontrado para: " + nome, "Pesquisar", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String s : res) sb.append(s).append("\n");
        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(480, 300));
        JOptionPane.showMessageDialog(frame, scroll, "Resultados da Pesquisa", JOptionPane.INFORMATION_MESSAGE);
    }

    private void onCreditos() {
        JPanel p = new JPanel(new GridLayout(0,1));
        p.add(new JLabel("Trabalho prático"));
        p.add(new JLabel("Aluno 1: João do Valle Seixas Paula"));
        JOptionPane.showMessageDialog(frame, p, "Créditos", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args) {
        // Executa o UI na EDT
        SwingUtilities.invokeLater(() -> new PatientApp());
    }
}
