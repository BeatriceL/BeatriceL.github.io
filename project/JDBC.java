
import java.sql.*;
import javax.swing.JOptionPane;

public class JDBC {

	String server = "jdbc:mysql://140.119.19.73:3315/";
	String database = "111304058";
	String url = server + database + "?useSSL=false";
	String username = "111304058";
	String password = "gveiz";

	private Connection conn;
	private PreparedStatement stat;
	private ResultSet rs;

	public JDBC() {
		connection();
	}

	public void connection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return conn;
	}

	public boolean enroll(String inputName, String inputPassword, String inputMail) {
		try {
			if (checkExistingAccount(inputName)) {

				// 帳號存在要做的事 這邊可以改
				JOptionPane.showMessageDialog(null, "該帳號已存在，無法建立重複帳號！", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}

			// 建立帳號
			stat = conn.prepareStatement(String.format("INSERT INTO Users (Name, Password, Email) VALUES ('&s', '&s', '&s')", inputName, inputPassword, inputMail));
			stat.executeUpdate();

			// 建立成功要做的事 這邊可以改
			JOptionPane.showMessageDialog(null, "帳號建立成功！", null, JOptionPane.INFORMATION_MESSAGE);
			/////////////////////////////////

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;

	}

	//登入
	public boolean login(String inputName, String inputPassword) {

		try {
			// 檢查"帳號名稱inputName"是否存在
			if (!checkExistingAccount(inputName)) {// 如果沒帳號
				// 帳號不存在要做的事 這裡可以改
				JOptionPane.showMessageDialog(null, "帳號不存在", "Error", JOptionPane.ERROR_MESSAGE);
				////
				return false;
			}

			if (!checkPassword(inputName, inputPassword)) {// 帳號存在但密碼錯
				// 帳號存在但密碼錯誤要做的事 這裡可以改
				JOptionPane.showMessageDialog(null, "密碼錯誤", "Error", JOptionPane.ERROR_MESSAGE);
				///
				return false;
			}

			/// 帳號存在要做的事
			// 開啟mainFrame
			return true;
			//

			///

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// 用inputName檢查帳號是否存在
	public boolean checkExistingAccount(String inputName) throws SQLException {
		stat = conn.prepareStatement(String.format("SELECT * FROM Users WHERE Name = '%s'", inputName));
		rs = stat.executeQuery();
		return rs.next(); // 若結果集中有下一行，表示帳號已存在，返回 true；否則返回 false
	}

	// 用inputName和inputPassword檢查帳號是否存在
	public boolean checkPassword(String inputName, String inputPassword) throws SQLException {
		stat = conn.prepareStatement(String.format("SELECT * FROM Users WHERE Name = '%s' AND Password = '%s'", inputName, inputPassword));
		rs = stat.executeQuery();
		return rs.next(); // 若結果集中有下一行，表示密碼正確，返回 true；否則返回 false
	}

	//更改個人資訊
	public void editProfile(User user) {
		try {
			stat = conn.prepareStatement(String.format("Update Users SET `Password`= '%s',`Email`= '%s' WHERE Username = '%s'", user.getPassword(), user.getEmail(), user.getName()));
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}