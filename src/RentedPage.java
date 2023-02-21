import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class RentedPage {

	private JFrame frmBookstorageRentedBooks;
	private static JTable table;
	static float pret =0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentedPage window = new RentedPage();
					window.frmBookstorageRentedBooks.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RentedPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBookstorageRentedBooks = new JFrame();
		frmBookstorageRentedBooks.setMinimumSize(new Dimension(1000, 500));
		frmBookstorageRentedBooks.setTitle("BOOKSTORAGE RENTED BOOKS");
		frmBookstorageRentedBooks.setBounds(100, 100, 450, 300);
		frmBookstorageRentedBooks.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBookstorageRentedBooks.getContentPane().setLayout(null);
		frmBookstorageRentedBooks.setLocationRelativeTo(null);
		frmBookstorageRentedBooks.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(("h9GsKGxOUVubT0D0apVg--1--nzr9q.jpg"))));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 20, 929, 383);
		frmBookstorageRentedBooks.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Inapoi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBookstorageRentedBooks.dispose();
			}
		});
		btnNewButton.setBounds(29, 413, 100, 40);
		frmBookstorageRentedBooks.getContentPane().add(btnNewButton);
		/**
		JLabel lblNewLabel = new JLabel("Valoare totala");
		lblNewLabel.setBounds(798, 420, 160, 26);
		frmBookstorageRentedBooks.getContentPane().add(lblNewLabel);
		String prt = "Valoare totala: "+pret+"";
		lblNewLabel.setText(prt);
		*/
		insertintable();
	}
	
	public static void insertintable() {
		pret=0;
		String url ="jdbc:mysql://localhost:3306/bookstoragedb";
		String username = "root";
		String password = "office";
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to the database");
			String read = "SELECT carti.titlu, carti.autor, carti.editura, carte_imprumutata.data, carti.pret from carte_imprumutata JOIN carti ON carti.id = carte_imprumutata.idcarte join user on carte_imprumutata.iduser = user.id where user.id = "+DataBase.ID_user+"";
			Statement statement2 = connection.createStatement();
			ResultSet result = statement2.executeQuery(read);
			ResultSetMetaData rsmd = result.getMetaData();
			
			DefaultTableModel model =(DefaultTableModel) table.getModel();
			int cols=rsmd.getColumnCount()-1;
			
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
				Date data = result.getDate("data");
				float p = result.getFloat("pret");
				pret =pret +p;
				String[] row = {titlu,autor,editura,data.toString()};
				model.addRow(row);
				table.getColumnModel().getColumn(0).setPreferredWidth(304);
				table.getColumnModel().getColumn(1).setPreferredWidth(270);
				table.getColumnModel().getColumn(2).setPreferredWidth(250);
				table.getColumnModel().getColumn(3).setPreferredWidth(100);
			}
			
			statement2.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Nu se poate conecta la baza de date.");
			e.printStackTrace();
		}
	}
}
