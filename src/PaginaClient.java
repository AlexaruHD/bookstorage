import java.awt.EventQueue;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;  
import java.text.SimpleDateFormat;
	



public class PaginaClient extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JTextField criteriu;
	static float price=0;
	static float pricef=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaginaClient frame = new PaginaClient();
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
	public PaginaClient() {
		setMinimumSize(new Dimension(1000, 500));
		setTitle("BOOKSTORAGE CLIENT PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(("h9GsKGxOUVubT0D0apVg--1--nzr9q.jpg"))));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 966, 393);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		criteriu = new JTextField();
		criteriu.setColumns(10);
		criteriu.setBounds(10, 435, 105, 18);
		contentPane.add(criteriu);
		
		final JComboBox comboColoana = new JComboBox();
		comboColoana.setModel(new DefaultComboBoxModel(new String[] {"Titlu", "Autor", "Editura"}));
		comboColoana.setMaximumRowCount(3);
		comboColoana.setBounds(10, 413, 105, 18);
		contentPane.add(comboColoana);
		
		JButton Cauta = new JButton("CAUTA");
		Cauta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resettable();
				String x = String.valueOf(comboColoana.getSelectedItem());
				cauta(x.toLowerCase(),criteriu.getText());
						
			}
		});
		Cauta.setBounds(125, 413, 105, 40);
		contentPane.add(Cauta);
		
		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(470, 413, 134, 40);
		Date min = new Date(System.currentTimeMillis());
		dateChooser.setMinSelectableDate(min);
		contentPane.add(dateChooser);
		
		JButton resetCautare = new JButton("RESET");
		resetCautare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resettable();
				inserttable();
				criteriu.setText(null);
			}
		});
		resetCautare.setBounds(240, 413, 105, 40);
		contentPane.add(resetCautare);
		
		JButton Inchiriaza = new JButton("Inchiriaza");
		Inchiriaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pattern = "yyyy-MM-dd";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String date1 = simpleDateFormat.format(dateChooser.getDate());
				//data de azi
				Date min = new Date(System.currentTimeMillis());
				long diff = (dateChooser.getDate().getTime() - min.getTime()) / (24 * 60 * 60 * 1000);
				int idcarte = getid(); 
				inchiriaza(idcarte,date1);
				pricef= getPrice(idcarte)*(diff+1);
				JOptionPane.showMessageDialog(null,"Cartea a fost inchiriata cu succes. Pretul este de: "+pricef);
				resettable();
				inserttable();
			}
		});
		Inchiriaza.setBounds(614, 413, 105, 40);
		contentPane.add(Inchiriaza);
		
		JButton btnEditeazaDatele = new JButton("Editeaza datele");
		btnEditeazaDatele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientData.main(null);
				
			}
		});
		btnEditeazaDatele.setBounds(871, 413, 105, 40);
		contentPane.add(btnEditeazaDatele);
		
		JButton btnVeziComenzi = new JButton("Vezi comenzi");
		btnVeziComenzi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RentedPage.main(null);
			}
		});
		btnVeziComenzi.setBounds(729, 413, 132, 40);
		contentPane.add(btnVeziComenzi);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginPage.main(null);
			}
		});
		btnLogOut.setBounds(355, 413, 105, 40);
		contentPane.add(btnLogOut);
		inserttable();
	}
	
	
	public static void inserttable() {
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
			
			DefaultTableModel model =(DefaultTableModel) table.getModel();
			int cols=rsmd.getColumnCount()-1;
			
			String[] colName = new String[cols];
			for(int i=0; i<cols; i++)
			{
				colName[i]=rsmd.getColumnName(i+2);
			}
			model.setColumnIdentifiers(colName);

			while(result.next()) {
				String titlu = result.getString("titlu");
				String autor = result.getString("autor");
				String editura = result.getString("editura");
				String stoc = result.getString("stoc");
				String pret = result.getString("pret");
				String[] row = {titlu,autor,editura,stoc,pret};
				if(Integer.valueOf(stoc)>0)
				{model.addRow(row);}
				table.getColumnModel().getColumn(0).setPreferredWidth(304);
				table.getColumnModel().getColumn(1).setPreferredWidth(270);
				table.getColumnModel().getColumn(2).setPreferredWidth(250);
				table.getColumnModel().getColumn(3).setPreferredWidth(43);
				table.getColumnModel().getColumn(4).setPreferredWidth(70);
			}
			
			statement2.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Nu se poate conecta la baza de date.");
			e.printStackTrace();
		}
	}
	
	public static void resettable() {
		table.setModel(new DefaultTableModel());
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
			
			DefaultTableModel model =(DefaultTableModel) table.getModel();
			int cols=rsmd.getColumnCount()-1;
			
			String[] colName = new String[cols];
			for(int i=0; i<cols; i++)
			{
				colName[i]=rsmd.getColumnName(i+2);
			}
			model.setColumnIdentifiers(colName);

			while(result.next()) {
				String titlu = result.getString("titlu");
				String autor = result.getString("autor");
				String editura = result.getString("editura");
				String stoc = result.getString("stoc");
				String pret = result.getString("pret");
				String[] row = {titlu,autor,editura,stoc,pret};
				if(Integer.valueOf(stoc)>0)
				{model.addRow(row);}
				table.getColumnModel().getColumn(0).setPreferredWidth(304);
				table.getColumnModel().getColumn(1).setPreferredWidth(270);
				table.getColumnModel().getColumn(2).setPreferredWidth(250);
				table.getColumnModel().getColumn(3).setPreferredWidth(43);
				table.getColumnModel().getColumn(4).setPreferredWidth(70);
			}
			
			statement2.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Nu se poate conecta la baza de date.");
			e.printStackTrace();
		}
	}
	
	public static void inchiriaza(int idcarte,String data) {
		String url ="jdbc:mysql://localhost:3306/bookstoragedb";
		String username = "root";
		String password = "office";
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to the database");
			String read = "INSERT INTO carte_imprumutata (idcarte, iduser, data) VALUES ('"+idcarte+"', '"+DataBase.ID_user+"', '"+data+"' );";
			PreparedStatement statement = connection.prepareStatement(read);
			statement.executeUpdate();
		
			String read1 = "UPDATE carti SET stoc = stoc-1 WHERE id = "+idcarte+";";
			PreparedStatement statement1 = connection.prepareStatement(read1);
			statement1.executeUpdate();
			statement.close();
			statement1.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Nu se poate conecta la baza de date.");
			e.printStackTrace();
		}
	}
	public static int getid() {
		int x = table.getSelectedRow();
		String value = table.getModel().getValueAt(x, 0).toString();
		String value1 = table.getModel().getValueAt(x, 1).toString();
		String url ="jdbc:mysql://localhost:3306/bookstoragedb";
		String username1 = "root";
		String password1 = "office";
		
		try {
			Connection connection = DriverManager.getConnection(url, username1, password1);
			System.out.println("Connected to the database");
			String read = "SELECT id FROM carti WHERE titlu = '"+value+"' AND autor = '"+value1+"';";
			Statement statement2 = connection.createStatement();
			ResultSet result = statement2.executeQuery(read);
			while(result.next()) {
				return Integer.valueOf(result.getString("id"));
			}
			
			statement2.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Nu se poate conecta la baza de date.");
			e.printStackTrace();
		}
		return 0;
	}
	public static float getPrice(int idcarte) {
		String url ="jdbc:mysql://localhost:3306/bookstoragedb";
		String username = "root";
		String password = "office";
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to the database");
			String read = "select pret from carti where id ="+idcarte;
			Statement statement2 = connection.createStatement();
			ResultSet result = statement2.executeQuery(read);
			
			while(result.next()) {
				price = result.getFloat("pret");
			}
			
			
			statement2.close();
			connection.close();
			
			
			return price;
			
		} catch (SQLException e) {
			System.out.println("Nu se poate conecta la baza de date.");
			e.printStackTrace();
		}
		return 0;
	}
}
