
import java.awt.event.*;

import javax.swing.JOptionPane;

public class CompanyChangeInfo extends SetCompanyInfo {

	public CompanyChangeInfo() {
		try {
			Tester.connectSQL();
			Tester.rs = Tester.statement.executeQuery("SELECT * FROM Company WHERE id = " + Tester.ID);
			while (Tester.rs.next()) {
				iAccount.setText(Tester.rs.getString("account"));
				iPassword.setText(Tester.rs.getString("password"));
				iName.setText(Tester.rs.getString("name"));
				iIndustry.setText(Tester.rs.getString("industry"));
				iLocation.setText(Tester.rs.getString("location"));
				iWebsite.setText(Tester.rs.getString("website"));
				iAbout.setText(Tester.rs.getString("about"));
				iField.setText(Tester.rs.getString("field"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		frame.setTitle("更改資料");
		button.setText("更改公司資料");
		iAccount.setEnabled(false);
		iPassword.setEnabled(false);
		button.addActionListener(new CompanyChangeInfoListener());
	}
	
	public void changeInfo(String name, String industry, String location, String website, String about, String field) {
		try {
			Tester.connectSQL();
			Tester.statement.executeUpdate("UPDATE Company SET name = \"" + name + "\", industry = \"" + industry
					+ "\", location = \"" + location + "\", website = \"" + website + "\", about = \"" + about + "\", field = \""
					+ field + "\" WHERE id = " + Tester.ID);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(frame, "更改失敗!", "", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
		JOptionPane.showMessageDialog(frame, "更改完成!\n請重新登入", "", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	class CompanyChangeInfoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (isFilled()) {
				changeInfo(iName.getText(), iIndustry.getText(), iLocation.getText(), iWebsite.getText(), iAbout.getText(), iField.getText());
			}
		}
	}
}