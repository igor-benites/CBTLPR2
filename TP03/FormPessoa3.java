import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Exercicio 02 - FormPessoa (versao 03): sexo escolhido em JRadioButton.
 * Nome: Igor Flores
 */

public class FormPessoa3 extends JFrame {

    private JTextField txtNumero = new JTextField();
    private JTextField txtNome = new JTextField();
    private JRadioButton rdoM = new JRadioButton("M");
    private JRadioButton rdoF = new JRadioButton("F");
    private ButtonGroup grupoSexo = new ButtonGroup();
    private JTextField txtIdade = new JTextField();
    private Pessoa UmaPessoa = new Pessoa();

    public FormPessoa3() {
        setTitle("Semana 06 - Exercicio 01");
        setSize(400, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        txtNumero.setEditable(false);
        grupoSexo.add(rdoM);
        grupoSexo.add(rdoF);

        JPanel painelSexo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelSexo.add(rdoM);
        painelSexo.add(rdoF);

        JPanel campos = new JPanel(new GridLayout(4, 2));
        campos.add(new JLabel("Numero:"));
        campos.add(txtNumero);
        campos.add(new JLabel("Nome:"));
        campos.add(txtNome);
        campos.add(new JLabel("Sexo:"));
        campos.add(painelSexo);
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
                String idade = txtIdade.getText().trim();

                if (nome.equals("") || idade.equals("") || (!rdoM.isSelected() && !rdoF.isSelected())) {
                    JOptionPane.showMessageDialog(FormPessoa3.this, "Nome, Sexo e Idade sao obrigatorios.");
                    return;
                }
                try {
                    int idadeInt = Integer.parseInt(idade);
                    UmaPessoa.setNome(nome);
                    UmaPessoa.setSexo(rdoM.isSelected() ? 'M' : 'F');
                    UmaPessoa.setIdade(idadeInt);
                    UmaPessoa.setKp();
                    txtNumero.setText(String.valueOf(UmaPessoa.getKp()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FormPessoa3.this, "Idade invalida.");
                }
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtNumero.setText("");
                txtNome.setText("");
                grupoSexo.clearSelection();
                txtIdade.setText("");
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(FormPessoa3.this,
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
        new FormPessoa3();
    }
}
