import javax.swing.*;
import java.awt.*;

/*
 * TP05 - Painel com lista e label "Final". Implementa Runnable: a thread conta de 0 ate o
 * valor maximo - 1 preenchendo a lista. O label Final so aparece ao termino da contagem.
 * Nome: Igor Flores
 */

public class PainelContagem extends JPanel implements Runnable {

    private DefaultListModel<Integer> modelo = new DefaultListModel<Integer>();
    private JList<Integer> lista = new JList<Integer>(modelo);
    private JLabel lblFim = new JLabel("Final");
    private int maximo;
    private volatile Thread atual;

    public PainelContagem() {
        setLayout(new BorderLayout(0, 10));

        lista.setPrototypeCellValue(Integer.valueOf(999999));
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setPreferredSize(new Dimension(90, 120));
        lblFim.setHorizontalAlignment(JLabel.CENTER);
        lblFim.setVisible(false);

        add(scroll, BorderLayout.CENTER);
        add(lblFim, BorderLayout.SOUTH);
    }

    public void iniciar(int maximo, int prioridade) {
        limpar();
        this.maximo = maximo;
        Thread t = new Thread(this);
        t.setPriority(prioridade);
        atual = t;
        t.start();
    }

    public void limpar() {
        atual = null;
        modelo.clear();
        lblFim.setVisible(false);
    }

    public void run() {
        final Thread eu = Thread.currentThread();

        for (int i = 0; i < maximo; i++) {
            if (atual != eu) {
                return;
            }
            final int valor = i;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    if (atual == eu) {
                        modelo.addElement(valor);
                    }
                }
            });
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (atual == eu) {
                    lblFim.setVisible(true);
                    validate();
                    lista.ensureIndexIsVisible(modelo.size() - 1);
                }
            }
        });
    }
}
