import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Exercicio 02 - FormPessoa (versao 01): sexo digitado em um TextField (somente M ou F).
 * Numero nao editavel; Nome, Sexo e Idade obrigatorios.
 * Nome: Igor Flores
 */

public class FormPessoa extends JFrame {

    private JTextField txtNumero = new JTextField();
    private JTextField txtNome = new JTextField();
    private JTextField txtSexo = new JTextField();
    private JTextField txtIdade = new JTextField();
    private Pessoa UmaPessoa = new Pessoa();

    public FormPessoa() {
        setTitle("Semana 06 - Exercicio 01");
        setSize(400, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        txtNumero.setEditable(false);

        JPanel campos = new JPanel(new GridLayout(4, 2));
        campos.add(new JLabel("Numero:"));
        campos.add(txtNumero);
        campos.add(new JLabel("Nome:"));
        campos.add(txtNome);
        campos.add(new JLabel("Sexo:"));
        campos.add(txtSexo);
        campos.add(new JLabel("Idade:"));
        campos.add(txtIdade);

        JPanel botoes = new JPanel(new GridLayout(1, 4));
        JButton btnOk = new JButton("OK");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnMostrar = new JButton("Mostrar");
        JButton btnSair = new JButton("Sair");
        botoes.add(btnOk);
        botoes.add(btnLimpar);
        botoes.add(btnMostrar);
        botoes.add(btnSair);

        add(campos, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText().trim();
                String sexo = txtSexo.getText().trim().toUpperCase();
                String idade = txtIdade.getText().trim();

                if (nome.equals("") || sexo.equals("") || idade.equals("")) {
                    JOptionPane.showMessageDialog(FormPessoa.this, "Nome, Sexo e Idade sao obrigatorios.");
                    return;
                }
                if (!sexo.equals("M") && !sexo.equals("F")) {
                    JOptionPane.showMessageDialog(FormPessoa.this, "Sexo deve ser M ou F.");
                    return;
                }
                try {
                    int idadeInt = Integer.parseInt(idade);
                    UmaPessoa.setNome(nome);
                    UmaPessoa.setSexo(sexo.charAt(0));
                    UmaPessoa.setIdade(idadeInt);
                    UmaPessoa.setKp();
                    txtNumero.setText(String.valueOf(UmaPessoa.getKp()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FormPessoa.this, "Idade invalida.");
                }
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtNumero.setText("");
                txtNome.setText("");
                txtSexo.setText("");
                txtIdade.setText("");
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(FormPessoa.this,
                        "Numero (kp): " + UmaPessoa.getKp() + "\n" +
                        "Nome: " + UmaPessoa.getNome() + "\n" +
                        "Sexo: " + UmaPessoa.getSexo() + "\n" +
                        "Idade: " + UmaPessoa.getIdade());
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
        new FormPessoa();
    }
}
