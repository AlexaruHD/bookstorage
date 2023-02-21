import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Comenzi {

	private JFrame frmBookstorageOrders;
	private static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Comenzi window = new Comenzi();
					window.frmBookstorageOrders.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Comenzi() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBookstorageOrders = new JFrame();
		frmBookstorageOrders.setTitle("BOOKSTORAGE ORDERS");
		frmBookstorageOrders.setMinimumSize(new Dimension(1000, 500));
		frmBookstorageOrders.setBounds(100, 100, 450, 300);
		frmBookstorageOrders.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBookstorageOrders.getContentPane().setLayout(null);
		frmBookstorageOrders.setLocationRelativeTo(null);
		frmBookstorageOrders.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(("h9GsKGxOUVubT0D0apVg--1--nzr9q.jpg"))));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 966, 402);
		frmBookstorageOrders.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Inapoi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBookstorageOrders.dispose();
			}
		});
		btnNewButton.setBounds(10, 413, 100, 40);
		frmBookstorageOrders.getContentPane().add(btnNewButton);
		insertdata();
	}
	
	
	public static void insertdata() {
		String url ="jdbc:mysql://localhost:3306/bookstoragedb";
		String username = "root";
		String password = "office";
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to the database");
			String read = "SELECT carti.titlu, carti.autor, carti.editura, dateclient.nume, dateclient.prenume, dateclient.nrtelefon, carte_imprumutata.data from carte_imprumutata JOIN carti ON carti.id = carte_imprumutata.idcarte join user on carte_imprumutata.iduser = user.id join dateclient on dateclient.id = user.id order by dateclient.nume";
			Statement statement2 = connection.createStatement();
			ResultSet result = statement2.executeQuery(read);
			ResultSetMetaData rsmd = result.getMetaData();
			
			DefaultTableModel model =(DefaultTableModel) table.getModel();
			int cols=rsmd.getColumnCount();
			
			String[] colName = new String[cols];
			for(int i=0; i<cols; i++)
			{
				colName[i]=rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);

			while(result.next()) {
				String titlu = result.getString("titlu");
				String autor = result.getString("autor");
				String editura = result.getString("editura");
				String stoc = result.getString("nume");
				String pret = result.getString("prenume");
				String id = result.getString("nrtelefon");
				String id1 = result.getString("data");
				id="0"+id;
				String[] row = {titlu,autor,editura,stoc,pret,id,id1};
				model.addRow(row);

			}
			
			statement2.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Nu se poate conecta la baza de date.");
			e.printStackTrace();
		}
	}
	
}
