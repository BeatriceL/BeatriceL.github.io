import java.sql.*;

public class JDBC {

	/// 連接資料庫
	String server = "jdbc:mysql://140.119.19.73:3315/";
	String database = "111304058"; // change to your own database
	String url = server + database + "?useSSL=false";
	String username = "111304058"; // change to your own user name
	String password = "gveiz"; // change to your own password

	private Connection conn;
	private PreparedStatement stat;
	private ResultSet rs;


//連接資料庫
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

///enrollButton/////////////////////////////////////////////////////////////////////////////////////////

	///建立帳號 
		public void enroll(String inputName, String inputPassword, String inputMail) {
			try {

				// 連接資料庫
				getConnection();

				// 在資料庫中建立帳號
				PreparedStatement stat = conn.prepareStatement("INSERT INTO `finalUsers` (Account, Password, Email) VALUES (?, ?, ?)");
				stat.setString(1, inputName);
				stat.setString(2, inputPassword);
				stat.setString(3, inputMail);
				stat.executeUpdate();

				// 建立成功要做的事 這邊可以改，預計跳出一個視窗/////////////////
				System.out.println("帳號建立成功！");
				/////////////////////////////////

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

// 檢查Account是否存在
	public boolean checkExistingAccount(String inputAccount) {
    	boolean accountExist = false; // 預設帳號不存在
		rs = null;
			try {
				// 連接資料庫
				getConnection();

				PreparedStatement stat = conn.prepareStatement("SELECT * FROM `finalUsers` WHERE Account = ?");
				stat.setString(1, inputAccount);
				rs = stat.executeQuery();// 若結果集中有下一行，表示帳號已存在，設置為 true
				if(rs.next()) {
					accountExist = true;
				}else {
					accountExist = false;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return accountExist; // 返回帳號是否存在的結果
		}

//檢查Email是否存在

public boolean checkExistingEmail(String inputEmail) {
	boolean emailExist = false; // 預設帳號不存在
	rs = null;
		try {
			// 連接資料庫
			getConnection();

			PreparedStatement stat = conn.prepareStatement("SELECT * FROM `finalUsers` WHERE Account = ?");
			stat.setString(1, inputEmail);
			rs = stat.executeQuery();// 若結果集中有下一行，表示帳號已存在，設置為 true
			if(rs.next()) {
				emailExist = true;
			}else {
				emailExist = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailExist; // 返回帳號是否存在的結果
	}	


//loginButton//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//登入
	public boolean login(String inputAccount, String inputPassword) {

		try {
			// 連接資料庫
			getConnection();

			// 檢查"帳號名稱inputName"是否存在
			if (!checkExistingAccount(inputAccount)) {// 如果沒帳號
				// 帳號不存在要做的事 這裡可以改
				System.out.println("帳號不存在");
				////
				return false;
			}

			// 獲取資料庫中該帳號的密碼
			String storedPassword = getPasswordFromAccount(inputAccount);

			if (inputPassword.equals(storedPassword)) {
				System.out.println("登入成功");
				return true;
			} else {
				System.out.println("密碼錯誤");
				return false;
			}

			/// 帳號存在要做的事，(已經寫好在loginFrame建立user)
			

			// 開啟mainFrame///////////////////////////////////////

			
			/////////////////////////////////////////////////////////

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//從Account獲取Password
	public String getPasswordFromAccount(String inputAccount) throws SQLException {
		PreparedStatement stat = conn.prepareStatement("SELECT Password FROM `finalUsers` WHERE Account = ?");
		stat.setString(1, inputAccount);
		ResultSet rs = stat.executeQuery();
		if (rs.next()) {
			return rs.getString("Password");
		}
		return null; // 若找不到該帳號，返回 null
	}

	//從Account名稱獲取Email
	public String getEmailFromAccount(String inputAccount) throws SQLException {
		PreparedStatement stat = conn.prepareStatement("SELECT Email FROM `finalUsers` WHERE Account = ?");
		stat.setString(1, inputAccount);
		ResultSet rs = stat.executeQuery();
		if (rs.next()) {
			return rs.getString("Email");
		}
		return null; // 若找不到該帳號，返回 null
	}

//更改個人資訊
	// public void editProfile(User user) {
	// 	try {
	// 		stat = conn.prepareStatement("Update `Users` SET `Username`= ?,`Password`= ? WHERE Email = ?");
	// 		stat.setString(1, user.getName());
	// 		stat.setString(2, user.getPassword());
	// 		stat.setString(3, user.getEmail());
	// 		stat.executeUpdate();
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 	}
	// }

}