
import java.awt.event.*;
import javax.swing.*;

public class CreateCompany extends SetCompanyInfo {

	public CreateCompany() {
		
		button.addActionListener(new CreateCompanyListener());
		button.setText("新增公司");
	}
	
	
	class CreateCompanyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (isFilled()) {
				try {
					Tester.connectSQL();
					Tester.rs = Tester.statement.executeQuery("SELECT account FROM Company");
					boolean existed = false;
					while (Tester.rs.next()) {
						if (Tester.rs.getString("account").equals(iAccount.getText())) {
							existed = true;
						}
					}
					if (!existed) {
						Tester.connectSQL();
						Tester.statement.executeUpdate(
								"INSERT INTO Company(account, password, name, industry, location, website, about, field) VALUES (\""
										+ iAccount.getText() + "\", \"" + getPassword() + "\", \""
										+ iName.getText() + "\", \"" + iIndustry.getText() + "\", \""
										+ iLocation.getText() + "\", \"" + iWebsite.getText() + "\", \""
										+ iAbout.getText() + "\", \"" + iField.getText() + "\")");
						JOptionPane.showMessageDialog(frame, "註冊成功\n請重新登入", "成功!", JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);
					} else {
						JOptionPane.showMessageDialog(frame, "帳號已存在\n請重新輸入", "創建失敗", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}