import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.xdevapi.Table;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPage extends JFrame {
	public static JTable tablebd;
	private JTextField criteriu;
	private JTextField textTitlu;
	private JTextField textAutor;
	private JTextField textEditura;
	private JTextField textStoc;
	private JTextField textPret;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				 ;
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public MainPage() {
		setTitle("BOOKSTORAGE PAGINA ADMIN");
		setMinimumSize(new Dimension(1200, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 372);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(("h9GsKGxOUVubT0D0apVg--1--nzr9q.jpg"))));
		
		JButton adaugaCarte = new JButton("ADAUGA O CARTE");
		adaugaCarte.setToolTipText("");
		adaugaCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBook.main(null);
			}
		});
		adaugaCarte.setBounds(10, 415, 105, 40);
		getContentPane().add(adaugaCarte);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		scrollPane.setBounds(10, 20, 970, 370);
		getContentPane().add(scrollPane);
		
		tablebd = new JTable();
		tablebd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = tablebd.getSelectedRow();
				String value = tablebd.getModel().getValueAt(x, 1).toString();
				textTitlu.setText(value);
				String value1 = tablebd.getModel().getValueAt(x, 2).toString();
				textAutor.setText(value1);
				String value2 = tablebd.getModel().getValueAt(x, 3).toString();
				textEditura.setText(value2);
				String value3 = tablebd.getModel().getValueAt(x, 4).toString();
				textStoc.setText(value3);
				String value4 = tablebd.getModel().getValueAt(x, 5).toString();
				textPret.setText(value4);
				
				
				
			}
		});
		
		insertdata();
		scrollPane.setViewportView(tablebd);
		tablebd.setName("tabelbd");
		tablebd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tablebd.getColumnModel().getColumn(0).setPreferredWidth(30);
		tablebd.getColumnModel().getColumn(1).setPreferredWidth(304);
		tablebd.getColumnModel().getColumn(2).setPreferredWidth(270);
		tablebd.getColumnModel().getColumn(3).setPreferredWidth(250);
		tablebd.getColumnModel().getColumn(4).setPreferredWidth(43);
		tablebd.getColumnModel().getColumn(5).setPreferredWidth(70);
		
		JButton Sterge = new JButton("STERGE");
		Sterge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selection [] = tablebd.getSelectedRows();
				
				for(int i=0;i<selection.length;i++)
				{
				int row = selection[i];
				String val=(tablebd.getModel().getValueAt(row,0).toString() );
				DataBase.deletebd(val);
				}
				MainPage.resettable();
				MainPage.insertdata();
			}
		});
		
		Sterge.setBounds(125, 415, 105, 40);
		getContentPane().add(Sterge);
		
		criteriu = new JTextField();
		criteriu.setBounds(280, 437, 105, 18);
		getContentPane().add(criteriu);
		criteriu.setColumns(10);
		
		final JComboBox comboColoana = new JComboBox();
		comboColoana.setModel(new DefaultComboBoxModel(new String[] {"Titlu", "Autor", "Editura"}));
		comboColoana.setMaximumRowCount(3);
		comboColoana.setBounds(280, 415, 105, 18);
		getContentPane().add(comboColoana);
		
		JButton Cauta = new JButton("CAUTA");
		Cauta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPage.resettable();
				String x = String.valueOf(comboColoana.getSelectedItem());
				cauta(x.toLowerCase(),criteriu.getText());
						
			}
		});
		Cauta.setBounds(395, 415, 105, 40);
		getContentPane().add(Cauta);
		
		JButton resetCautare = new JButton("RESET");
		resetCautare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			resettable();
			insertdata();
			criteriu.setText(null);
			}
		});
		resetCautare.setBounds(510, 415, 105, 40);
		getContentPane().add(resetCautare);
		
		textTitlu = new JTextField();
		textTitlu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textTitlu.setBounds(990, 40, 190, 25);
		getContentPane().add(textTitlu);
		textTitlu.setColumns(10);
		
		textAutor = new JTextField();
		textAutor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textAutor.setColumns(10);
		textAutor.setBounds(990, 70, 190, 25);
		getContentPane().add(textAutor);
		
		textEditura = new JTextField();
		textEditura.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEditura.setColumns(10);
		textEditura.setBounds(990, 100, 190, 25);
		getContentPane().add(textEditura);
		
		textStoc = new JTextField();
		textStoc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textStoc.setColumns(10);
		textStoc.setBounds(990, 130, 190, 25);
		getContentPane().add(textStoc);
		
		textPret = new JTextField();
		textPret.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPret.setColumns(10);
		textPret.setBounds(990, 160, 190, 25);
		getContentPane().add(textPret);
		
		lblNewLabel = new JLabel("Panou editare");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(990, 20, 119, 13);
		getContentPane().add(lblNewLabel);
		
		JButton btnSalveaza = new JButton("SALVEAZA");
		btnSalveaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int x = tablebd.getSelectedRow();
				int id = Integer.parseInt(tablebd.getModel().getValueAt(x, 0).toString());
				String url ="jdbc:mysql://localhost:3306/bookstoragedb";
				String username1 = "root";
				String password1 = "office";
				
				try {
					Connection connection = DriverManager.getConnection(url, username1, password1);
					System.out.println("Connected to the database");
					String read = "UPDATE carti SET titlu = '"+textTitlu.getText()+"', autor = '"+textAutor.getText()+"', editura = '"+textEditura.getText()+"', stoc = '"+Integer.valueOf(textStoc.getText())+"', pret = '"+Float.parseFloat(textPret.getText())+"' WHERE id = "+id+";";
					PreparedStatement statement = connection.prepareStatement(read);
					statement.executeUpdate();
					connection.close();
				} catch (SQLException e1) {
					System.out.println("eroare Schimbare");
					
				}
				MainPage.resettable();
				MainPage.insertdata();
			}
		});
		btnSalveaza.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnSalveaza.setBounds(990, 195, 105, 40);
		getContentPane().add(btnSalveaza);
		
		JButton btnVeziComenzi = new JButton("Vezi comenzi");
		btnVeziComenzi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Comenzi.main(null);
			}
		});
		btnVeziComenzi.setBounds(990, 415, 186, 40);
		getContentPane().add(btnVeziComenzi);
		
		JButton btnLogout = new JButton("Log Out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginPage.main(null);
			}
		});
		btnLogout.setBounds(875, 415, 105, 40);
		getContentPane().add(btnLogout);
		

	}
	
	public static void insertdata() {
		String url ="jdbc:mysql://localhost:3306/bookstoragedb";
		String username = "root";
		String password = "office";
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to the database");
			String read = "SELECT * FROM carti";
			Statement statement2 = connection.createStatement();
			ResultSet result = statement2.executeQuery(read);
			ResultSetMetaData rsmd = result.getMetaData();
			
			DefaultTableModel model =(DefaultTableModel) tablebd.getModel();
			int cols=rsmd.getColumnCount();
			
			String[] colName = new String[cols];
			for(int i=0; i<cols; i++)
			{
				colName[i]=rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);

			while(result.next()) {
				String id = result.getString("id");
				String titlu = result.getString("titlu");
				String autor = result.getString("autor");
				String editura = result.getString("editura");
				String stoc = result.getString("stoc");
				String pret = result.getString("pret");
				String[] row = {id,titlu,autor,editura,stoc,pret};
				model.addRow(row);
				tablebd.getColumnModel().getColumn(0).setPreferredWidth(30);
				tablebd.getColumnModel().getColumn(1).setPreferredWidth(304);
				tablebd.getColumnModel().getColumn(2).setPreferredWidth(270);
				tablebd.getColumnModel().getColumn(3).setPreferredWidth(250);
				tablebd.getColumnModel().getColumn(4).setPreferredWidth(43);
				tablebd.getColumnModel().getColumn(5).setPreferredWidth(70);
			}
			
			statement2.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Nu se poate conecta la baza de date.");
			e.printStackTrace();
		}
	}
	
	public static void resettable() {
		tablebd.setModel(new DefaultTableModel());
	}
	
	public static void cauta(String col,String term) {
		String url ="jdbc:mysql://localhost:3306/bookstoragedb";
		String username = "root";
		String password = "office";
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to the database");
			String read = "SELECT * FROM carti WHERE "+col+" LIKE '%"+term+"%'";
			System.out.println("Connected to the database");
			Statement statement2 = connection.createStatement();
			ResultSet result = statement2.executeQuery(read);
			ResultSetMetaData rsmd = result.getMetaData();
			
			DefaultTableModel model =(DefaultTableModel) tablebd.getModel();
			
			int cols=rsmd.getColumnCount();
			
			String[] colName = new String[cols];
			for(int i=0; i<cols; i++)
			{
				colName[i]=rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);

			while(result.next()) {
				String id = result.getString("id");
				String titlu = result.getString("titlu");
				String autor = result.getString("autor");
				String editura = result.getString("editura");
				String stoc = result.getString("stoc");
				String pret = result.getString("pret");
				String[] row = {id,titlu,autor,editura,stoc,pret};
				model.addRow(row);
				tablebd.getColumnModel().getColumn(0).setPreferredWidth(30);
				tablebd.getColumnModel().getColumn(1).setPreferredWidth(304);
				tablebd.getColumnModel().getColumn(2).setPreferredWidth(270);
				tablebd.getColumnModel().getColumn(3).setPreferredWidth(250);
				tablebd.getColumnModel().getColumn(4).setPreferredWidth(43);
				tablebd.getColumnModel().getColumn(5).setPreferredWidth(70);
			}
			
			statement2.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Nu se poate conecta la baza de date.");
			e.printStackTrace();
		}
	}
}
