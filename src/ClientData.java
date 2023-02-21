import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientData {

	private JFrame frmBookstorageClientData;
	private static JTextField textNume;
	private static JTextField textPrenume;
	private static JTextField textEmail;
	private static JTextField textNrtelefon;
	private static JTextField textOras;
	private static JTextField textAdresa;
	static int validare = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientData window = new ClientData();
					window.frmBookstorageClientData.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientData() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBookstorageClientData = new JFrame();
		frmBookstorageClientData.setMinimumSize(new Dimension(200, 0));
		frmBookstorageClientData.setTitle("CLIENT DATA");
		frmBookstorageClientData.setBounds(100, 100, 385, 425);
		frmBookstorageClientData.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBookstorageClientData.getContentPane().setLayout(null);
		frmBookstorageClientData.setLocationRelativeTo(null);
		frmBookstorageClientData.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(("h9GsKGxOUVubT0D0apVg--1--nzr9q.jpg"))));
		
		textNume = new JTextField();
		textNume.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		textNume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textNume.setBounds(147, 30, 200, 40);
		frmBookstorageClientData.getContentPane().add(textNume);
		textNume.setColumns(10);
		
		textPrenume = new JTextField();
		textPrenume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPrenume.setColumns(10);
		textPrenume.setBounds(147, 80, 200, 40);
		frmBookstorageClientData.getContentPane().add(textPrenume);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textEmail.setColumns(10);
		textEmail.setBounds(147, 130, 200, 40);
		frmBookstorageClientData.getContentPane().add(textEmail);
		
		textNrtelefon = new JTextField();
		textNrtelefon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textNrtelefon.setColumns(10);
		textNrtelefon.setBounds(147, 180, 200, 40);
		frmBookstorageClientData.getContentPane().add(textNrtelefon);
		
		textOras = new JTextField();
		textOras.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textOras.setColumns(10);
		textOras.setBounds(147, 230, 200, 40);
		frmBookstorageClientData.getContentPane().add(textOras);
		
		textAdresa = new JTextField();
		textAdresa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textAdresa.setColumns(10);
		textAdresa.setBounds(147, 280, 200, 40);
		frmBookstorageClientData.getContentPane().add(textAdresa);
		
		JButton btnInapoi = new JButton("Inapoi");
		btnInapoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBookstorageClientData.dispose();
			}
		});
		btnInapoi.setBounds(25, 340, 95, 40);
		frmBookstorageClientData.getContentPane().add(btnInapoi);
		
		JButton btnSalveaza = new JButton("Salveaza");
		btnSalveaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validare==1) {
					String url ="jdbc:mysql://localhost:3306/bookstoragedb";
					String username1 = "root";
					String password1 = "office";
					
					try {
						Connection connection = DriverManager.getConnection(url, username1, password1);
						System.out.println("Connected to the database");
						String read = "UPDATE dateclient SET nume = '"+textNume.getText()+"', prenume = '"+textPrenume.getText()+"' , email = '"+textEmail.getText()+"', adresa ='"+textAdresa.getText()+"' ,nrtelefon = '"+Integer.valueOf(textNrtelefon.getText())+"' , oras = '"+textOras.getText()+"' WHERE id = "+DataBase.ID_user+";";
						PreparedStatement statement = connection.prepareStatement(read);
						statement.executeUpdate();
						connection.close();
					} catch (SQLException e1) {
						System.out.println("eroare Schimbare");
						
					}
					
				}
				else
				{
					String url ="jdbc:mysql://localhost:3306/bookstoragedb";
					String username = "root";
					String password = "office";
					
					try {
						Connection connection = DriverManager.getConnection(url, username, password);
						String insert = "INSERT INTO dateclient (id, nume, prenume, email, adresa, nrtelefon, oras) VALUES (?, ?, ?, ?, ?, ?, ?)";
						PreparedStatement statement = connection.prepareStatement(insert);
						statement.setInt(1, DataBase.ID_user);
						statement.setString(2, textNume.getText());
						statement.setString(3, textPrenume.getText());
						statement.setString(4, textEmail.getText());
						statement.setString(5, textAdresa.getText());
						statement.setInt(6, Integer.valueOf(textNrtelefon.getText()));
						statement.setString(7, textOras.getText());
						
						
						statement.executeUpdate();
						statement.close();
						connection.close();
					} catch (SQLException e1) {
						
						}
				}
				
			}
			
		}
		
		);
		btnSalveaza.setBounds(252, 340, 95, 40);
		frmBookstorageClientData.getContentPane().add(btnSalveaza);
		
		JLabel lblNewLabel = new JLabel("Nume");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(25, 29, 100, 40);
		frmBookstorageClientData.getContentPane().add(lblNewLabel);
		
		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrenume.setBounds(25, 80, 100, 40);
		frmBookstorageClientData.getContentPane().add(lblPrenume);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(25, 130, 100, 40);
		frmBookstorageClientData.getContentPane().add(lblEmail);
		
		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTelefon.setBounds(25, 179, 100, 40);
		frmBookstorageClientData.getContentPane().add(lblTelefon);
		
		JLabel lblOras = new JLabel("Oras");
		lblOras.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOras.setBounds(25, 229, 100, 40);
		frmBookstorageClientData.getContentPane().add(lblOras);
		
		JLabel lblAdresa = new JLabel("Adresa");
		lblAdresa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAdresa.setBounds(25, 274, 100, 40);
		frmBookstorageClientData.getContentPane().add(lblAdresa);
		inserttable();
		if(textNume.getText().isEmpty() ) {
				JOptionPane.showMessageDialog(null,"Introduceti datele personale.");
			}
			else
			{
			validare =1;
			}

	}
	
	
	public static void inserttable() {
		String url ="jdbc:mysql://localhost:3306/bookstoragedb";
		String username = "root";
		String password = "office";
		int a =0;
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			String read = "SELECT * FROM dateclient WHERE id="+DataBase.ID_user+";";
			Statement statement2 = connection.createStatement();
			ResultSet result = statement2.executeQuery(read);
			
			while(result.next()) {
				String nume = result.getString("nume");
				String prenume = result.getString("prenume");
				String email = result.getString("email");
				String nrtelefon = result.getString("nrtelefon");
				String oras = result.getString("oras");
				String adresa = result.getString("adresa");
				textNume.setText(nume);
				textPrenume.setText(prenume);
				textEmail.setText(email);
				textNrtelefon.setText(nrtelefon);
				textOras.setText(oras);
				textAdresa.setText(adresa);
			
			}
			statement2.close();
			connection.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Introduceti datele personale.");
			e.printStackTrace();
		}
	}
	
	
}
