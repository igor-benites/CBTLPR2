import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * Exercicio - Formulario de cadastro de alunos com interface grafica Swing
 * Nome: Igor Flores
 */

public class FormAluno extends JFrame {

    private JTextField txtNome;
    private JTextField txtIdade;
    private JTextField txtEndereco;
    private List<Aluno> alunos = new ArrayList<Aluno>();

    public FormAluno() {
        setTitle("TP02 - LP2I4");
        setSize(400, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // painel superior: GridLayout 3x2 com hgap e vgap 10
        JPanel painelSuperior = new JPanel(new GridLayout(3, 2, 10, 10));
        painelSuperior.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelSuperior.add(txtNome);
        painelSuperior.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        painelSuperior.add(txtIdade);
        painelSuperior.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        painelSuperior.add(txtEndereco);

        // painel inferior: 4 botoes em GridLayout
        JPanel painelInferior = new JPanel(new GridLayout(1, 4));
        JButton btnOk = new JButton("Ok");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnMostrar = new JButton("Mostrar");
        JButton btnSair = new JButton("Sair");
        painelInferior.add(btnOk);
        painelInferior.add(btnLimpar);
        painelInferior.add(btnMostrar);
        painelInferior.add(btnSair);

        add(painelSuperior, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Aluno a = new Aluno();
                    a.setNome(txtNome.getText());
                    a.setIdade(Integer.parseInt(txtIdade.getText()));
                    a.setEndereco(txtEndereco.getText());
                    a.setUuid(UUID.randomUUID());
                    alunos.add(a);
                    JOptionPane.showMessageDialog(FormAluno.this, "Aluno cadastrado!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FormAluno.this, "Idade invalida.");
                }
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtNome.setText("");
                txtIdade.setText("");
                txtEndereco.setText("");
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (alunos.isEmpty()) {
                    JOptionPane.showMessageDialog(FormAluno.this, "Nenhum aluno cadastrado.");
                    return;
                }
                StringBuilder sb = new StringBuilder("Resultado\n");
                for (Aluno a : alunos) {
                    sb.append("Id: ").append(a.getUuid())
                      .append("  Nome: ").append(a.getNome()).append("\n");
                }
                JOptionPane.showMessageDialog(FormAluno.this, sb.toString());
            }
        });

        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FormAluno();
    }
}
