import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;

/*
 * TP04 - Frame de pesquisa de funcionarios. Conexao com o banco SQL Server aulajava via JDBC.
 * O botao Pesquisar executa um select com like (PreparedStatement) e preenche um recordset.
 * Os botoes Proximo e Anterior navegam pelo recordset ate os limites inicial e final.
 * Nome: Igor Flores
 */

public class FormFuncionarios extends JFrame {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=aulajava;encrypt=false;trustServerCertificate=true";
    private static final String USUARIO = "sa";
    private static final String SENHA = "Tp04@Aulajava"; // senha do SQL Server local (container Docker tp04-sqlserver)

    private JTextField txtBusca = new JTextField();
    private JTextField txtNome = new JTextField();
    private JTextField txtSalario = new JTextField();
    private JTextField txtCargo = new JTextField();

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public FormFuncionarios() {
        setTitle("TRABALHO PRATICO 04");
        setSize(500, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        txtNome.setEditable(false);
        txtSalario.setEditable(false);
        txtCargo.setEditable(false);

        JPanel painelBusca = new JPanel(new BorderLayout(5, 5));
        painelBusca.add(new JLabel("  Nome:"), BorderLayout.WEST);
        painelBusca.add(txtBusca, BorderLayout.CENTER);
        JButton btnPesquisar = new JButton("Pesquisar");
        JPanel painelBotao = new JPanel(new FlowLayout());
        painelBotao.add(btnPesquisar);
        JPanel topo = new JPanel(new BorderLayout());
        topo.add(painelBusca, BorderLayout.NORTH);
        topo.add(painelBotao, BorderLayout.CENTER);

        JPanel campos = new JPanel(new GridLayout(3, 2, 5, 5));
        campos.add(new JLabel("Nome:"));
        campos.add(txtNome);
        campos.add(new JLabel("Salário:"));
        campos.add(txtSalario);
        campos.add(new JLabel("Cargo:"));
        campos.add(txtCargo);

        JPanel navegacao = new JPanel(new GridLayout(1, 2));
        JButton btnAnterior = new JButton("Anterior");
        JButton btnProximo = new JButton("Próximo");
        navegacao.add(btnAnterior);
        navegacao.add(btnProximo);

        JPanel centro = new JPanel(new BorderLayout(0, 10));
        centro.add(topo, BorderLayout.NORTH);
        centro.add(campos, BorderLayout.CENTER);

        add(centro, BorderLayout.CENTER);
        add(navegacao, BorderLayout.SOUTH);

        try {
            conn = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar: " + e.getMessage());
        }

        btnPesquisar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pesquisar();
            }
        });

        btnAnterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (rs != null && !rs.isFirst()) {
                        rs.previous();
                        mostrar();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(FormFuncionarios.this, "Erro: " + ex.getMessage());
                }
            }
        });

        btnProximo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (rs != null && !rs.isLast()) {
                        rs.next();
                        mostrar();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(FormFuncionarios.this, "Erro: " + ex.getMessage());
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (conn != null) conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void pesquisar() {
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "Sem conexao com o banco.");
            return;
        }
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();

            String sql = "SELECT f.nome_func, f.sal_func, c.ds_cargo "
                       + "FROM tbfuncs f LEFT JOIN tbcargos c ON f.cod_cargo = c.cd_cargo "
                       + "WHERE f.nome_func LIKE ? ORDER BY f.cod_func";

            ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, "%" + txtBusca.getText().trim() + "%");
            rs = ps.executeQuery();

            if (rs.next()) {
                mostrar();
            } else {
                limpar();
                JOptionPane.showMessageDialog(this, "Nenhum registro encontrado.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro na pesquisa: " + ex.getMessage());
        }
    }

    private void mostrar() throws SQLException {
        String nome = rs.getString("nome_func");
        BigDecimal salario = rs.getBigDecimal("sal_func");
        String cargo = rs.getString("ds_cargo");

        txtNome.setText(nome == null ? "" : nome.trim());
        txtSalario.setText(salario == null ? "" : salario.setScale(2, RoundingMode.HALF_UP).toPlainString());
        txtCargo.setText(cargo == null ? "" : cargo.trim());
    }

    private void limpar() {
        txtNome.setText("");
        txtSalario.setText("");
        txtCargo.setText("");
    }

    public static void main(String[] args) {
        new FormFuncionarios();
    }
}
