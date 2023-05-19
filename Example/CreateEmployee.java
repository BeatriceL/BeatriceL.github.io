
import java.awt.event.*;
import javax.swing.*;

public class CreateEmployee extends SetEmployeeInfo {

	public CreateEmployee() {
		button.addActionListener(new NewEmployeeAction());
		button.setText("新增個人檔案");
	}

	class NewEmployeeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				if (isFilled()) {
					Tester.connectSQL();
					Tester.rs = Tester.statement.executeQuery("SELECT account FROM User");
					boolean existed = false;
					while (Tester.rs.next()) {
						if (Tester.rs.getString("account").equals(iAccount.getText())) {
							existed = true;
						}
					}
					if (!existed) {
						Tester.statement.close();
						Tester.connectSQL();
						Tester.statement.executeUpdate(
								"INSERT INTO User(account, password, name, major, location, email, education, experience, about) VALUES (\""
										+ iAccount.getText() + "\", \"" + getPassword() + "\", \"" + iName.getText()
										+ "\", \"" + iMajor.getText() + "\", \"" + iLocation.getText() + "\", \""
										+ iEmail.getText() + "\", \"" + iEducation.getText() + "\", \""
										+ iExperience.getText() + "\", \"" + iAbout.getText() + "\")");
						JOptionPane.showMessageDialog(frame, "註冊成功\n請重新登入", "成功!", JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);
					} else {
						JOptionPane.showMessageDialog(frame, "帳號已存在\n請重新輸入", "創建失敗", JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}