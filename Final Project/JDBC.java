import java.sql.*;

public class JDBC {
	private static final String server = "jdbc:mysql://140.119.19.73:3315/";
	private static final String database = "111304058"; // change to your own database
	private static final String url = server + database + "?useSSL=false";
	private static final String username = "111304058"; // change to your own user name
	private static final String password = "gveiz"; // change to your own password		
	
	private Connection conn;
	private PreparedStatement stat;
	private ResultSet rs;
	
	public JDBC(){
		getConnection();
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		}catch(Exception e){
			e.printStackTrace();
		}
	}