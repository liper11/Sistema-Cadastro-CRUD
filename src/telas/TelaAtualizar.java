package telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import conexao.Conexao;
import conexao.DbException;
import java.awt.Color;

public class TelaAtualizar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCpf;
	private JTextField txtNome;
	private JTextField txtCpfAtu;
	private JTextField txtEndereco;
	private JTextField txtFone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAtualizar frame = new TelaAtualizar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaAtualizar() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 373);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTelaDeAtualizao = new JLabel("Tela de Atualiza\u00E7\u00E3o de Cadastro - utlizando CPF");
		lblTelaDeAtualizao.setForeground(Color.WHITE);
		lblTelaDeAtualizao.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblTelaDeAtualizao.setBounds(107, 11, 373, 14);
		contentPane.add(lblTelaDeAtualizao);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblCpf.setBounds(116, 83, 46, 14);
		contentPane.add(lblCpf);
		
		txtCpf = new JTextField();
		txtCpf.setBounds(192, 80, 153, 20);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection conn = Conexao.conectar();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				try {
					/*  no trecho abaixo o sistema utliza a query disposta no no Prepared Statement stmt  a executa
					 * utilizando como numero de CPF o CPF digitado pelo usuario no txtCPF
					 */
					stmt = conn.prepareStatement("SELECT * FROM clientes "+ " WHERE "+ " cpf = ?");
					stmt.setString(1, txtCpf.getText());
					rs = stmt.executeQuery();
					
					if (rs.next()) {
					txtNome.setText(rs.getString(2));
					txtCpfAtu.setText(rs.getString(3));
					txtFone.setText(rs.getString(4));
					txtEndereco.setText(rs.getString(5));
					}
				} catch (SQLException e) {
					
					throw new DbException(e.getMessage());
				}
				
				
			}
		});
		btnConsultar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnConsultar.setBounds(355, 77, 104, 23);
		contentPane.add(btnConsultar);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblNome.setBounds(22, 142, 46, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(89, 139, 413, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblCpf_1 = new JLabel("CPF");
		lblCpf_1.setForeground(Color.WHITE);
		lblCpf_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblCpf_1.setBounds(22, 241, 46, 14);
		contentPane.add(lblCpf_1);
		
		txtCpfAtu = new JTextField();
		txtCpfAtu.setBounds(89, 238, 180, 20);
		contentPane.add(txtCpfAtu);
		txtCpfAtu.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setForeground(Color.WHITE);
		lblEndereo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblEndereo.setBounds(10, 195, 58, 14);
		contentPane.add(lblEndereo);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(88, 192, 414, 20);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		JLabel lblFone = new JLabel("Fone");
		lblFone.setForeground(Color.WHITE);
		lblFone.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblFone.setBounds(287, 241, 46, 14);
		contentPane.add(lblFone);
		
		txtFone = new JTextField();
		txtFone.setBounds(333, 238, 169, 20);
		contentPane.add(txtFone);
		txtFone.setColumns(10);
		
		JButton btnNewButton = new JButton("Atualizar");
		btnNewButton.setForeground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection conn = Conexao.conectar();
				PreparedStatement stmt = null;
				int linhasAfetadas = 0;
				
				try {
					
					stmt = conn.prepareStatement("UPDATE clientes " + " SET " + " nome = ? , cpf = ? , telefone = ?, endereco = ?  "+ "WHERE "+ " cpf = ?");
					stmt.setString(1, txtNome.getText());
					stmt.setString(2, txtCpfAtu.getText());
					stmt.setString(3, txtFone.getText());
					stmt.setString(4, txtEndereco.getText());
					stmt.setString(5, txtCpf.getText());
					linhasAfetadas = stmt.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "DADOS ATUALIZADOS COM SUCESSO !!" + "\n"+"NOME: "+ txtNome.getText() + "\n" +"CPF: "+ txtCpfAtu.getText()+
								"\n" + "ENDEREÇO: "+ txtEndereco.getText() + "\n" +"TELEFONE: "+ txtFone.getText()	 + "\n" + "LINHAS AFETADAS: "+linhasAfetadas, null, JOptionPane.WARNING_MESSAGE);
					
					txtNome.setText(" ");
					txtCpf.setText(" ");
					txtCpfAtu.setText(" ");
					txtFone.setText(" ");
					txtEndereco.setText(" ");
					
				} catch (SQLException e) {
					
					throw new DbException(e.getMessage());
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnNewButton.setBounds(376, 294, 104, 23);
		contentPane.add(btnNewButton);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setForeground(Color.RED);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new TelaMenus().setVisible(true);dispose();
			}
		});
		btnVoltar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnVoltar.setBounds(89, 294, 89, 23);
		contentPane.add(btnVoltar);
	}
}
