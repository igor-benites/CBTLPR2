import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * TP05 - Formulario principal (JFrame). Duas threads de contagem com prioridades distintas
 * (valores de 1 a 10), cada uma preenchendo a sua lista. Botoes Iniciar e Limpar.
 * Nome: Igor Flores
 */

public class FormThreads extends JFrame {

    private JTextField txtMaximo = new JTextField("1000");
    private JTextField txtPrioridade1 = new JTextField("1");
    private JTextField txtPrioridade2 = new JTextField("10");
    private PainelContagem painel1 = new PainelContagem();
    private PainelContagem painel2 = new PainelContagem();

    public FormThreads() {
        setTitle("Trabalho Prático 5");
        setSize(560, 440);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel campos = new JPanel(new GridLayout(3, 2, 10, 10));
        campos.add(new JLabel("Máximo:"));
        campos.add(txtMaximo);
        campos.add(new JLabel("Prioridade 1:"));
        campos.add(txtPrioridade1);
        campos.add(new JLabel("Prioridade 2:"));
        campos.add(txtPrioridade2);

        JPanel formulario = new JPanel(new BorderLayout(10, 0));
        formulario.add(campos, BorderLayout.CENTER);
        formulario.add(new JLabel("Número entre 1 e 10"), BorderLayout.EAST);

        JButton btnIniciar = new JButton("Iniciar");
        JPanel painelIniciar = new JPanel(new FlowLayout());
        painelIniciar.add(btnIniciar);

        JPanel topo = new JPanel(new BorderLayout(0, 10));
        topo.add(formulario, BorderLayout.CENTER);
        topo.add(painelIniciar, BorderLayout.SOUTH);
        topo.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));

        JPanel listas = new JPanel(new GridLayout(1, 2, 40, 0));
        listas.add(painel1);
        listas.add(painel2);
        listas.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 60));

        JButton btnLimpar = new JButton("Limpar");
        JPanel painelLimpar = new JPanel(new FlowLayout());
        painelLimpar.add(btnLimpar);

        add(topo, BorderLayout.NORTH);
        add(listas, BorderLayout.CENTER);
        add(painelLimpar, BorderLayout.SOUTH);

        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int maximo = Integer.parseInt(txtMaximo.getText().trim());
                    int p1 = Integer.parseInt(txtPrioridade1.getText().trim());
                    int p2 = Integer.parseInt(txtPrioridade2.getText().trim());

                    if (maximo <= 0) {
                        JOptionPane.showMessageDialog(FormThreads.this, "O valor máximo deve ser maior que zero.");
                        return;
                    }
                    if (p1 < 1 || p1 > 10 || p2 < 1 || p2 > 10) {
                        JOptionPane.showMessageDialog(FormThreads.this, "As prioridades devem estar entre 1 e 10.");
                        return;
                    }

                    painel1.iniciar(maximo, p1);
                    painel2.iniciar(maximo, p2);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FormThreads.this, "Digite apenas números inteiros.");
                }
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                painel1.limpar();
                painel2.limpar();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FormThreads();
    }
}
