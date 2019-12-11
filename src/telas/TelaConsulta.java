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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexao.Conexao;
import conexao.DbException;
import javax.swing.JTextField;

public class TelaConsulta extends JFrame {

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
					TelaConsulta frame = new TelaConsulta();
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
	public TelaConsulta() {
		setTitle("Cadastro de Clientes - Tela de Consulta");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 397);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsultarClientes = new JLabel("Consultar Clientes - Utilizando CPF");
		lblConsultarClientes.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		lblConsultarClientes.setForeground(Color.WHITE);
		lblConsultarClientes.setBounds(109, 11, 361, 27);
		contentPane.add(lblConsultarClientes);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection conn = Conexao.conectar();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				try {
					
					stmt = conn.prepareStatement("SELECT * FROM clientes " + " WHERE cpf = ? ");
					stmt.setString(1, txtCPF.getText());
					rs = stmt.executeQuery();
					
					
					while (rs.next()) {
						
                    txtNome.setText(rs.getString("nome"));

					}
				}catch(SQLException e) {
					
					throw new DbException(e.getMessage());
				}
			}
		});
		btnConsultar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnConsultar.setBounds(425, 67, 108, 23);
		contentPane.add(btnConsultar);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(140, 70, 275, 20);
		contentPane.add(txtCPF);
		txtCPF.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setBounds(84, 73, 46, 14);
		contentPane.add(lblCpf);
		
		txtNome = new JTextField();
		txtNome.setBounds(140, 158, 275, 23);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome ");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNome.setBounds(84, 161, 46, 14);
		contentPane.add(lblNome);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new TelaMenus().setVisible(true);dispose();
			}
		});
		btnVoltar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnVoltar.setBounds(84, 257, 89, 23);
		contentPane.add(btnVoltar);
	}
}
