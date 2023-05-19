
import java.awt.event.*;
import javax.swing.*;

public class EmployeeChangePassword extends ChangePassword {

	public EmployeeChangePassword() {
		class EmployeeChangePasButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (checkOldPas(Tester.ID, new String(inputOld.getPassword()), new String(inputNew.getPassword()))) {
					if (new String(inputNew.getPassword()).equals(new String(inputAgain.getPassword()))) {
						try {
							Tester.connectSQL();
							Tester.statement.executeUpdate("UPDATE User SET password = '"
									+ new String(inputNew.getPassword()) + "' WHERE id =" + Tester.ID);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(frame, "新密碼輸入不相同", "錯誤", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		button.addActionListener(new EmployeeChangePasButtonListener());
	}

	public boolean checkOldPas(int id, String oldPas, String newPas) {
		try {
			Tester.connectSQL();
			Tester.rs = Tester.statement.executeQuery("SELECT password FROM User WHERE id = " + id);
			while (Tester.rs.next()) {
				if (!new String(oldPas).equals(newPas)) {
					if (oldPas.equals(Tester.rs.getString("password"))) {
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