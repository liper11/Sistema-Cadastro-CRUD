package telas;

import java.awt.Color;
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




public class TelaExcluir extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCPF;
	private JTextField txtNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaExcluir frame = new TelaExcluir();
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
	public TelaExcluir() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 547, 326);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTelaDeExclusao = new JLabel("Tela de Exclusao de Cadastro do Cliente ");
		lblTelaDeExclusao.setForeground(Color.WHITE);
		lblTelaDeExclusao.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblTelaDeExclusao.setBounds(142, 11, 341, 14);
		contentPane.add(lblTelaDeExclusao);
		
		JLabel lblCpf = new JLabel("por CPF");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblCpf.setBounds(75, 58, 57, 14);
		contentPane.add(lblCpf);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(142, 55, 129, 20);
		contentPane.add(txtCPF);
		txtCPF.setColumns(10);
		
		JButton btnPesquisar = new JButton("Consultar");
		btnPesquisar.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent arg0 ) {
				
				Connection conn = Conexao.conectar();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				try {
					/* esse codigo consulta no banco de dados utilizando como parametro o cpf digitado pelo usuario ao clicar no botao 
					 * consultar
					 */
					stmt = conn.prepareStatement("SELECT * FROM clientes "+ " WHERE "+" cpf = ? ");
					stmt.setString(1, txtCPF.getText());
					rs = stmt.executeQuery();
					
					if (rs.next()) {
					
				   
				     
				     
				     
				     txtNome.setText(rs.getString(2));
				    
				      
					}
				} catch (SQLException e) {
					
					throw new DbException(e.getMessage());
				}
				
			
			}
		});
		btnPesquisar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnPesquisar.setBounds(295, 52, 115, 23);
		contentPane.add(btnPesquisar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setForeground(Color.RED);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new TelaMenus().setVisible(true);dispose();
			}
		});
		btnVoltar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnVoltar.setBounds(142, 194, 115, 23);
		contentPane.add(btnVoltar);
		
		txtNome = new JTextField();
		txtNome.setBounds(142, 112, 268, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblNome.setBounds(85, 115, 46, 14);
		contentPane.add(lblNome);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setForeground(Color.RED);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection conn = Conexao.conectar();
				PreparedStatement stmt = null;
				int linhasAfetadas = 0;
				
				try {
					/* esse codigo deleta o registro utilizando como parametro o CPf digitado pelo cliente ao clicar no botao
					 * excluir
					 */
					stmt = conn.prepareStatement(" DELETE FROM clientes " + " WHERE "+ " cpf = ?");
					stmt.setString(1, txtCPF.getText());
					linhasAfetadas = stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "CADASTRO EXCLUIDO COM SUCESSO" + "\n"+ "NOME: "+ txtNome.getText()+
							 "\n"+ "CPF: "+ txtCPF.getText() + "\n"+"LINHAS AFETADAS: "+ linhasAfetadas,null,JOptionPane.WARNING_MESSAGE);
					
					txtNome.setText(" ");
					txtCPF.setText(" ");
				} catch(SQLException e) {
					
					throw new DbException(e.getMessage());
				}
			}
		});
		btnExcluir.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnExcluir.setBounds(306, 194, 104, 23);
		contentPane.add(btnExcluir);
	}
}
