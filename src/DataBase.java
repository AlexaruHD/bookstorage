import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DataBase {
	
	public static int ID_user;
	public static void insertindb(String titlu, String autor, String editura, int stoc, float pret) {
	String url ="jdbc:mysql://localhost:3306/bookstoragedb";
	String username = "root";
	String password = "office";
	
	try {
		Connection connection = DriverManager.getConnection(url, username, password);
		System.out.println("Connected to the database");
		String insert = "INSERT INTO carti (titlu, autor, editura, stoc, pret) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(insert);
		statement.setString(1, titlu);
		statement.setString(2, autor);
		statement.setString(3, editura);
		statement.setInt(4,stoc);
		statement.setFloat(5, pret);
		statement.executeUpdate();
		statement.close();
		connection.close();
	} catch (SQLException e) {
		System.out.println("Nu se poate conecta la baza de date.");
		e.printStackTrace();
		}
	}
	
	public static void insertindbuser(String user, String pass, int tip) {
		String url ="jdbc:mysql://localhost:3306/bookstoragedb";
		String username = "root";
		String password = "office";
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			String insert = "INSERT INTO user (username, password, tip) VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, user);
			statement.setString(2, pass);
			statement.setInt(3, tip);
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Nu se poate conecta la baza de date.");
			e.printStackTrace();
			}
		}
	
	public static void readdb() {
		String url ="jdbc:mysql://localhost:3306/bookstoragedb";
		String username = "root";
		String password = "office";
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to the database");
			String read = "SELECT * FROM carti";
			Statement statement2 = connection.createStatement();
			ResultSet result = statement2.executeQuery(read);
			int count = 0;
			while(result.next()) {
				String titlu = result.getString("titlu");
				String autor = result.getString("autor");
				String editura = result.getString("editura");
				int stoc = result.getInt("stoc");
				float pret = result.getFloat("pret");
				count++;
				System.out.println("Cartea numarul "+count +": "+titlu+" "+autor+" "+editura+" "+stoc+" "+pret);
			}
			
			statement2.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Nu se poate conecta la baza de date.");
			e.printStackTrace();
		}
		
	}
	
	public static void deletebd(String id) {
		String url ="jdbc:mysql://localhost:3306/bookstoragedb";
		String username = "root";
		String password = "office";
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			String del = "DELETE FROM carti WHERE ID="+id;
			PreparedStatement statement = connection.prepareStatement(del);
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Nu se poate conecta la baza de date.");
			e.printStackTrace();
			}
		}
	
	public static boolean login(String user,String pass) {
		String url ="jdbc:mysql://localhost:3306/bookstoragedb";
		String username1 = "root";
		String password1 = "office";
		
		try {
			Connection connection = DriverManager.getConnection(url, username1, password1);
			System.out.println("Connected to the database");
			String read = "SELECT * FROM user";
			Statement statement2 = connection.createStatement();
			ResultSet result = statement2.executeQuery(read);
			while(result.next()) {
				String username = result.getString("username");
				String password = result.getString("password");
				if(user.equals(username) && pass.equals(password))
				{
					ID_user =result.getInt("id");
					statement2.close();
					connection.close();
					return true;	
				}
			}
			
			statement2.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Nu se poate conecta la baza de date.");
			e.printStackTrace();
		}
		return false;
		
	}
	public static boolean isAdmin(String user) {
		String url ="jdbc:mysql://localhost:3306/bookstoragedb";
		String username1 = "root";
		String password1 = "office";
		
		try {
			Connection connection = DriverManager.getConnection(url, username1, password1);
			System.out.println("Connected to the database");
			String read = "SELECT tip FROM user WHERE username = '"+user+"';";
			Statement statement2 = connection.createStatement();
			ResultSet result = statement2.executeQuery(read);
			while(result.next()) {
				if(result.getInt("tip") == 1)
				{
					statement2.close();
					connection.close();
					return true;
				}
			}
			
			statement2.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Nu se poate conecta la baza de date.");
			e.printStackTrace();
		}
		
		
		
		return false;
	}
	
}
