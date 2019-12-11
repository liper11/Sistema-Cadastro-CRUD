package telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

public class TelaCadastro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEndereco;
	private JTextField txtFone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
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
	public TelaCadastro() {
		setResizable(false);
		setTitle("Novo Cadastro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 570,368);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNovoCadastroDe = new JLabel("Novo Cadastro de Clientes");
		lblNovoCadastroDe.setForeground(Color.WHITE);
		lblNovoCadastroDe.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 21));
		lblNovoCadastroDe.setBounds(162, 11, 236, 25);
		contentPane.add(lblNovoCadastroDe);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblNome.setBounds(10, 97, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblCpf.setBounds(352, 97, 46, 14);
		contentPane.add(lblCpf);
		
		JLabel lblEn = new JLabel("End");
		lblEn.setForeground(Color.WHITE);
		lblEn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblEn.setBounds(10, 160, 46, 14);
		contentPane.add(lblEn);
		
		JLabel lblFone = new JLabel("Fone");
		lblFone.setForeground(Color.WHITE);
		lblFone.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblFone.setBounds(10, 209, 46, 14);
		contentPane.add(lblFone);
		
		txtNome = new JTextField();
		txtNome.setBounds(49, 94, 293, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtCpf = new JTextField();
		txtCpf.setBounds(388, 94, 140, 20);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(49, 157, 479, 20);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		txtFone = new JTextField();
		txtFone.setBounds(49, 206, 150, 20);
		contentPane.add(txtFone);
		txtFone.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setForeground(Color.RED);
		btnCadastrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				Connection conn = Conexao.conectar();
				PreparedStatement stmt = null;
				int linhasAfetadas = 0;
				
				try {
					
					/* o trecho abaixo do botao cadastrar , insere os dados digitados pelo usuario, utilizando os mesmos
					 *  como parametro do banco de dados.
					 */
					stmt = conn.prepareStatement("INSERT INTO clientes " + " (nome,cpf,telefone,endereco) " + " VALUES "+ " (?,?,?,?) ");
					stmt.setString(1,txtNome.getText());
					stmt.setString(2, txtCpf.getText());
					stmt.setString(3,txtFone.getText());
			        stmt.setString(4, txtEndereco.getText());
			        linhasAfetadas = stmt.executeUpdate();
			        
			        JOptionPane.showMessageDialog(null,"DADOS INSERIDOS COM SUCESSO: " + "\n"+"NOME: "+ txtNome.getText() +"\n"+ "CPF: "+ txtCpf.getText() + "\n"+ "ENDEREÇO: "+ txtEndereco.getText() +"\n"+ "TELEFONE: "+ txtFone.getText() +  
			        		                            "\n" +  "LINHAS AFETADAS : " + linhasAfetadas,null,JOptionPane.WARNING_MESSAGE);
			        
			        txtNome.setText(" ");
			        txtCpf.setText(" ");
			        txtFone.setText(" ");
			        txtEndereco.setText(" ");
				} catch (SQLException e) {
					
					throw new DbException(e.getMessage());
				}
				
				
			}
			
			
		});
		btnCadastrar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnCadastrar.setBounds(423, 203, 105, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setForeground(Color.RED);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				new TelaMenus().setVisible(true);dispose();
				
			}
		});
		btnVoltar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnVoltar.setBounds(253, 205, 89, 23);
		contentPane.add(btnVoltar);
	}
}
