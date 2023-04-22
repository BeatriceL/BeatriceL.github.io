
import java.awt.event.*;

import javax.swing.JOptionPane;

public class EmployeeChangeInfo extends SetEmployeeInfo {

	public EmployeeChangeInfo() {
		try {
			Tester.connectSQL();
			Tester.rs = Tester.statement.executeQuery("SELECT * FROM User WHERE id = " + Tester.ID);
			while (Tester.rs.next()) {
				iAccount.setText(Tester.rs.getString("account"));
				iPassword.setText(Tester.rs.getString("password"));
				iName.setText(Tester.rs.getString("name"));
				iMajor.setText(Tester.rs.getString("major"));
				iLocation.setText(Tester.rs.getString("location"));
				iEmail.setText(Tester.rs.getString("email"));
				iEducation.setText(Tester.rs.getString("education"));
				iExperience.setText(Tester.rs.getString("experience"));
				iAbout.setText(Tester.rs.getString("about"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		iAccount.setEnabled(false);
		iPassword.setEnabled(false);
		frame.setTitle("更改資料");
		button.setText("更改個人資訊");
		button.addActionListener(new EmployeeChangeInfoListener());
	}

	public void changeInfo(String name, String major, String location, String email, String education,
			String experience, String about) {
		try {
			Tester.connectSQL();
			Tester.statement.executeUpdate("UPDATE User SET name = \"" + name + "\", major = \"" + major
					+ "\", location = \"" + location + "\", email = \"" + email + "\", education = \"" + education
					+ "\", experience = \"" + experience + "\", about = \"" + about + "\" WHERE id = " + Tester.ID);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(frame, "更改失敗!", "", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
		JOptionPane.showMessageDialog(frame, "更改完成\n請重新登入", "", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	class EmployeeChangeInfoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (isFilled()) {
				changeInfo(iName.getText(), iMajor.getText(), iLocation.getText(), iEmail.getText(),
						iEducation.getText(), iExperience.getText(), iAbout.getText());
			}
		}
	}
}