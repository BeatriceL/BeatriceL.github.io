
public class User {
	private String name;
	private String password;
	private String email;
	
	
	protected JDBC jdbc;
	
	public User(String name, String password, String email) {//enroll介面第一次創建帳號時用的
		
		//連接資料庫
		jdbc = new JDBC();
		
		this.name = name;
		this.password = password;
		this.email = email;
		
	}
	
	public User(String name, String password) {//login介面登入，這邊只能用帳號密碼，還要讓email儲存
		
		//連接資料庫
		jdbc = new JDBC();
		
		this.name = name;
		this.password = password;
		//this.email = email;
		
	}
	
	public String getName() {
		return name;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	
	// public void editProfile(String name, String password,String email) {
	// 	this.name = name;
	// 	this.password = password;
	// 	this.email = email;
		
	// 	jdbc.editProfile(this);
	// }
	
}
	
	
	
