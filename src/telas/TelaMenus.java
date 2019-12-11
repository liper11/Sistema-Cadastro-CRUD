package telas;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;

public class TelaMenus extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenus frame = new TelaMenus();
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
	public TelaMenus() {
		setResizable(false);
		setTitle("Cadastro de Clientes - Tela de Acesso aos Menus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 589, 413);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.GRAY);
		menuBar.setBounds(0, 0,589, 21);
		contentPane.add(menuBar);
		
		JMenuItem mntmCadstrar = new JMenuItem("Novo Cadastro");
		mntmCadstrar.setForeground(Color.RED);
		mntmCadstrar.setBackground(Color.LIGHT_GRAY);
		mntmCadstrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/* chamada da tela de cadastro ao clicar no menu de Novo Cadastro*/
				new TelaCadastro().setVisible(true);dispose();
				
			}
		});
		mntmCadstrar.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mntmCadstrar);
		
		JMenuItem mntmAtualizarCadastro = new JMenuItem("Atualizar Cadastro");
		mntmAtualizarCadastro.setForeground(Color.RED);
		mntmAtualizarCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/* chama a tela de atualização de cadastro ao clicar no menu atualizar cadastro*/
				new TelaAtualizar().setVisible(true);dispose();
			}
		});
		mntmAtualizarCadastro.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mntmAtualizarCadastro);
		
		JMenuItem mntmExcluirCadastro = new JMenuItem("Excluir Cadastro");
		mntmExcluirCadastro.setForeground(Color.RED);
		mntmExcluirCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/* chamada da tela de Exclusão de cadastro ao clicar no menu Excluir Cadastro*/
				new TelaExcluir().setVisible(true);dispose();
			}
		});
		mntmExcluirCadastro.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mntmExcluirCadastro);
		
		JMenuItem menuConsultar = new JMenuItem("Consultar Clientes ");
		menuConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new TelaConsulta().setVisible(true);dispose();
			}
		});
		menuConsultar.setForeground(Color.RED);
		menuConsultar.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(menuConsultar);
		
		JLabel lblSistemaDeCadastro = new JLabel("SISTEMA DE CADASTRO DE CLIENTES");
		lblSistemaDeCadastro.setForeground(Color.RED);
		lblSistemaDeCadastro.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		lblSistemaDeCadastro.setBounds(57, 110, 491, 152);
		contentPane.add(lblSistemaDeCadastro);
		
		
	}
}
