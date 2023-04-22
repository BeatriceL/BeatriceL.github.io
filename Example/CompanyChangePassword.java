
import java.awt.event.*;
import javax.swing.*;

public class CompanyChangePassword extends ChangePassword {

	public CompanyChangePassword() {
		class CompanyChangePasButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (checkOldPas(Tester.ID, new String(inputOld.getPassword()), new String(inputNew.getPassword()))) {
					if (new String(inputNew.getPassword()).equals(new String(inputAgain.getPassword()))) {
						try {
							Tester.connectSQL();
							Tester.statement.executeUpdate("UPDATE Company SET password = '"
									+ new String(inputNew.getPassword()) + "' WHERE id = " + Tester.ID);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(frame, "新密碼輸入不相同", "錯誤", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		button.addActionListener(new CompanyChangePasButtonListener());
	}

	public boolean checkOldPas(int id, String oldPas, String newPas) {
		try {
			Tester.connectSQL();
			Tester.rs = Tester.statement.executeQuery("SELECT password FROM Company WHERE id = " + id);
			while (Tester.rs.next()) {
				if (!oldPas.equals(newPas)) {
					if (Tester.rs.getString("password").equals(oldPas)) {
						return true;
					} else {
						JOptionPane.showMessageDialog(frame, "舊密碼錯誤", "錯誤", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "新舊密碼不可相同", "錯誤", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}