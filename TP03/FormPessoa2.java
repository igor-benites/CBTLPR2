import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Exercicio 02 - FormPessoa (versao 02): sexo escolhido em um JComboBox.
 * Nome: Igor Flores
 */

public class FormPessoa2 extends JFrame {

    private JTextField txtNumero = new JTextField();
    private JTextField txtNome = new JTextField();
    private JComboBox<String> cmbSexo = new JComboBox<String>(new String[]{"", "M", "F"});
    private JTextField txtIdade = new JTextField();
    private Pessoa UmaPessoa = new Pessoa();

    public FormPessoa2() {
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
        campos.add(cmbSexo);
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
                String sexo = (String) cmbSexo.getSelectedItem();
                String idade = txtIdade.getText().trim();

                if (nome.equals("") || sexo.equals("") || idade.equals("")) {
                    JOptionPane.showMessageDialog(FormPessoa2.this, "Nome, Sexo e Idade sao obrigatorios.");
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
                    JOptionPane.showMessageDialog(FormPessoa2.this, "Idade invalida.");
                }
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtNumero.setText("");
                txtNome.setText("");
                cmbSexo.setSelectedIndex(0);
                txtIdade.setText("");
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(FormPessoa2.this,
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
        new FormPessoa2();
    }
}
