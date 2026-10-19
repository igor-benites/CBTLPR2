import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Exercicio 01 - Calculadora com as operacoes de divisao, multiplicacao, subtracao e adicao.
 * O botao C limpa a memoria e zera o campo de resultado. Com tratamento de erros (try, catch, finally).
 * Nome: Igor Flores
 */

public class Calculadora extends JFrame implements ActionListener {

    private JTextField txtResultado = new JTextField("0");
    private double acumulado = 0;
    private String operador = "";
    private boolean novoNumero = true;

    public Calculadora() {
        setTitle("Calcul..");
        setSize(250, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        txtResultado.setHorizontalAlignment(JTextField.RIGHT);
        txtResultado.setEditable(false);
        add(txtResultado, BorderLayout.NORTH);

        String[] teclas = {"7", "8", "9", "/",
                           "4", "5", "6", "*",
                           "1", "2", "3", "-",
                           "0", ".", "=", "+",
                           "C", "", "", ""};

        JPanel painel = new JPanel(new GridLayout(5, 4));
        for (int i = 0; i < teclas.length; i++) {
            if (teclas[i].equals("")) {
                painel.add(new JLabel());
            } else {
                JButton b = new JButton(teclas[i]);
                b.addActionListener(this);
                painel.add(b);
            }
        }
        add(painel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String tecla = e.getActionCommand();

        if (tecla.equals("C")) {
            acumulado = 0;
            operador = "";
            novoNumero = true;
            txtResultado.setText("0");
        } else if (tecla.equals("/") || tecla.equals("*") || tecla.equals("-") || tecla.equals("+")) {
            calcular();
            operador = tecla;
            novoNumero = true;
        } else if (tecla.equals("=")) {
            calcular();
            operador = "";
        } else {
            if (novoNumero) {
                txtResultado.setText(tecla.equals(".") ? "0." : tecla);
                novoNumero = false;
            } else {
                if (tecla.equals(".") && txtResultado.getText().contains(".")) {
                    return;
                }
                txtResultado.setText(txtResultado.getText() + tecla);
            }
        }
    }

    private void calcular() {
        try {
            double valor = Double.parseDouble(txtResultado.getText());

            if (operador.equals("")) {
                acumulado = valor;
            } else if (operador.equals("+")) {
                acumulado = acumulado + valor;
            } else if (operador.equals("-")) {
                acumulado = acumulado - valor;
            } else if (operador.equals("*")) {
                acumulado = acumulado * valor;
            } else if (operador.equals("/")) {
                if (valor == 0) {
                    throw new ArithmeticException("Divisao por zero");
                }
                acumulado = acumulado / valor;
            }

            if (acumulado == (long) acumulado) {
                txtResultado.setText(String.valueOf((long) acumulado));
            } else {
                txtResultado.setText(String.valueOf(acumulado));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor invalido.");
            acumulado = 0;
            operador = "";
            txtResultado.setText("0");
        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            acumulado = 0;
            operador = "";
            txtResultado.setText("0");
        } finally {
            novoNumero = true;
        }
    }

    public static void main(String[] args) {
        new Calculadora();
    }
}
