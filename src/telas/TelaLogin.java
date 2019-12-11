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

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import conexao.Conexao;
import conexao.DbException;
import java.awt.Color;

public class TelaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsu;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		setResizable(false);
		setForeground(Color.DARK_GRAY);
		setTitle("Cadastro de Clientes - Tela de Acesso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 355);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastroDeClientes = new JLabel("Cadastro de Clientes");
		lblCadastroDeClientes.setBackground(Color.WHITE);
		lblCadastroDeClientes.setForeground(Color.WHITE);
		lblCadastroDeClientes.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblCadastroDeClientes.setBounds(190, 11, 148, 32);
		contentPane.add(lblCadastroDeClientes);
		
		JLabel lblLogin = new JLabel("Usu\u00E1rio");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblLogin.setBounds(20, 141, 46, 14);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblSenha.setBounds(20, 223, 46, 14);
		contentPane.add(lblSenha);
		
		txtUsu = new JTextField();
		txtUsu.setBounds(92, 138, 340, 20);
		contentPane.add(txtUsu);
		txtUsu.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(92, 220, 340, 20);
		contentPane.add(txtSenha);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setForeground(Color.RED);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection conn = Conexao.conectar();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				try {
					
					/* esse codigo consulta o bd e verifica se existe algum usuario E senha iguais aos digitados pelo cliente ao
					 * clicar o botao de login
					 */
					stmt = conn.prepareStatement("SELECT * FROM" + " usuarios "+ " WHERE " + " usuario = ?" + " AND " + " senha = ?");
					stmt.setString(1, txtUsu.getText());
					stmt.setString(2, new String(txtSenha.getPassword()));
					rs = stmt.executeQuery();
					
					if (rs.next()) {
						
					    
				        new TelaMenus().setVisible(true);dispose();
				       
					}
					
				} catch (SQLException e) {
					
					throw new DbException(e.getMessage());
					
				}
			
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnLogin.setBounds(343, 274, 89, 23);
		contentPane.add(btnLogin);
	}
}
