import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class LoginPage {

	private JFrame frmBookstorageLogin;
	private JTextField textUsername;
	private JTextField textPassword;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frmBookstorageLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LoginPage() {
		initialize();
	}
	
	
	private void initialize() {
		
		
		frmBookstorageLogin = new JFrame();
		frmBookstorageLogin.setTitle("BOOKSTORAGE LOGIN");
		frmBookstorageLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(("h9GsKGxOUVubT0D0apVg--1--nzr9q.jpg"))));
		frmBookstorageLogin.getContentPane().setForeground(Color.WHITE);
		frmBookstorageLogin.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frmBookstorageLogin.setBounds(100, 100, 450, 300);
		frmBookstorageLogin.setLocationRelativeTo(null); 
		frmBookstorageLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBookstorageLogin.getContentPane().setLayout(null);
		
		JLabel lbUsername = new JLabel("Username:");
		lbUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbUsername.setBounds(45, 95, 78, 28);
		lbUsername.setBackground(new Color(240, 240, 240));
		lbUsername.setHorizontalAlignment(SwingConstants.LEFT);
		frmBookstorageLogin.getContentPane().add(lbUsername);
		
		JLabel lbPassword = new JLabel("Password:");
		lbPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbPassword.setBackground(SystemColor.menu);
		lbPassword.setBounds(45, 128, 78, 28);
		frmBookstorageLogin.getContentPane().add(lbPassword);
		
		JLabel lblLogin = new JLabel("BOOKSTORAGE LOGIN PAGE");
		lblLogin.setForeground(new Color(80, 40, 100));
		lblLogin.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogin.setBackground(SystemColor.menu);
		lblLogin.setBounds(45, 36, 298, 28);
		frmBookstorageLogin.getContentPane().add(lblLogin);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textUsername.setBounds(117, 101, 226, 20);
		frmBookstorageLogin.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(DataBase.login(textUsername.getText(),textPassword.getText()))
				{
					if(DataBase.isAdmin(textUsername.getText())) {
					MainPage mp = new MainPage();
					mp.show();
					frmBookstorageLogin.dispose();
					}
					else {
						PaginaClient pc = new PaginaClient();
						pc.show();
						frmBookstorageLogin.dispose();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Combinatia de utilizator si parola nu exista in baza de date.");
				}
	
			}


		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setBounds(117, 164, 107, 23);
		frmBookstorageLogin.getContentPane().add(btnLogin);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				RegisterPage rp = new RegisterPage();
				rp.show();
				
			}
		});
		btnRegister.setForeground(Color.BLACK);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegister.setBounds(236, 164, 107, 23);
		frmBookstorageLogin.getContentPane().add(btnRegister);
		
		textPassword = new JTextField();
		textPassword.setFont(new Font("Wingdings 3", Font.PLAIN, 15));
		textPassword.setColumns(10);
		textPassword.setBounds(117, 133, 226, 20);
		frmBookstorageLogin.getContentPane().add(textPassword);
	}
}
