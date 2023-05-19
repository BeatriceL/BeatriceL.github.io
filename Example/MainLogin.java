
import javax.swing.*;
import java.awt.*;

public class MainLogin {

	GridBagConstraints c = new GridBagConstraints();

	JFrame frame = new JFrame("登入");
	JLabel account = new JLabel("帳號：");
	JLabel password = new JLabel("密碼：");
	JTextField inputAccount = new JTextField();
	JPasswordField inputPas = new JPasswordField();
	JButton login = new JButton("登入");
	JButton newAccount = new JButton("新增帳號");

	public MainLogin() {
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());

		c.gridx = c.gridy = 0;
		c.gridwidth = c.gridheight = 1;
		c.ipady = 20;
		frame.add(account, c);

		c.gridy = 1;
		frame.add(password, c);

		c.gridx = 1;
		c.gridy = 0;
		c.ipady = 10;
		c.ipadx = 150;
		frame.add(inputAccount, c);

		c.gridy = 1;
		frame.add(inputPas, c);

		c.gridy = 3;
		c.ipady = 7;
		c.ipadx = 40;
		c.insets.top = 10;
		c.fill = GridBagConstraints.BOTH;
		frame.add(login, c);

		c.gridy = 4;
		c.insets.top = 0;
		frame.add(newAccount, c);

		frame.setVisible(true);
	}

	public boolean userLogin(String account, String password) {
		try {
			Tester.connectSQL();
			Tester.rs = Tester.statement.executeQuery("SELECT * FROM User WHERE account LIKE '" + account + "'");
			while (Tester.rs.next()) {
				if (Tester.rs.getString("password").equals(password)) {
					Tester.ID = Tester.rs.getInt("id");
					Tester.name = Tester.rs.getString("name");
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean comapnyLogin(String account, String password) {
		try {
			Tester.connectSQL();
			Tester.rs = Tester.statement.executeQuery("SELECT * FROM Company WHERE account LIKE '" + account + "'");
			while (Tester.rs.next()) {
				if (Tester.rs.getString("password").equals(password)) {
					Tester.ID = Tester.rs.getInt("id");
					Tester.name = Tester.rs.getString("name");
					return true;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public void loginFailed() {
		inputPas.setText("");
		JOptionPane.showMessageDialog(frame, "登入失敗！", "Login Failed", JOptionPane.ERROR_MESSAGE);
	}

	public String getPassword() {
		return String.valueOf(inputPas.getPassword());
	}

}