import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegisterPage extends JFrame {

	public JPanel contentPane;
	private JTextField textUsername;
	private JTextField textPassUser;
	public static JPasswordField passwordAdmin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPage frame = new RegisterPage();
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
	public RegisterPage() {
		setTitle("BOOKSTORAGE REGISTER");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(("h9GsKGxOUVubT0D0apVg--1--nzr9q.jpg"))));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 290, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Parola ADMIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(25, 40, 110, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblUserNou = new JLabel("User nou");
		lblUserNou.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserNou.setBounds(25, 70, 110, 20);
		contentPane.add(lblUserNou);
		
		JLabel lblParola = new JLabel("Parola");
		lblParola.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblParola.setBounds(25, 100, 110, 20);
		contentPane.add(lblParola);
		
		textUsername = new JTextField();
		textUsername.setBounds(145, 70, 100, 20);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		final JComboBox comboTip = new JComboBox();
		comboTip.setModel(new DefaultComboBoxModel(new String[] {"Client", "Admin"}));
		comboTip.setBounds(145, 130, 100, 21);
		contentPane.add(comboTip);
		
		
		JButton btnInapoi = new JButton("Inapoi");
		btnInapoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnInapoi.setBounds(25, 163, 100, 30);
		contentPane.add(btnInapoi);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( String.valueOf(comboTip.getSelectedItem()) == "Client" ) {
					DataBase.insertindbuser(textUsername.getText(), textPassUser.getText(), 0 );
					JOptionPane.showMessageDialog(null,"Cont creat cu succes.");
				}
				else
				{
					if(PasswordCK())
					{DataBase.insertindbuser(textUsername.getText(), textPassUser.getText(), 1 );
					JOptionPane.showMessageDialog(null,"Cont creat cu succes.");
					}
					else {
						JOptionPane.showMessageDialog(null,"Parola ADMIN gresita.");
					}
				}
				
				
			}
		});
		btnRegister.setBounds(145, 163, 100, 30);
		contentPane.add(btnRegister);
		
		
		textPassUser = new JTextField();
		textPassUser.setFont(new Font("Wingdings 2", Font.PLAIN, 16));
		textPassUser.setColumns(10);
		textPassUser.setBounds(145, 100, 100, 20);
		contentPane.add(textPassUser);
		
		passwordAdmin = new JPasswordField();
		passwordAdmin.setBounds(145, 40, 100, 20);
		contentPane.add(passwordAdmin);
		
		
		JLabel lblTipCont = new JLabel("Tip cont");
		lblTipCont.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTipCont.setBounds(25, 130, 110, 20);
		contentPane.add(lblTipCont);
	}
	
	public static boolean PasswordCK() {
		char[] parola = new char[] {'A','L','E','X'};
		char [] parolaintrodusa = passwordAdmin.getPassword();
		try {
			for(int i=0; i< parola.length; i++)
			{	if(parolaintrodusa[i] != parola[i] || parolaintrodusa.length != parola.length)
				{return false;}				
			}
		} catch (Exception e){
			return false;
		}
		return true;	
	}
}
