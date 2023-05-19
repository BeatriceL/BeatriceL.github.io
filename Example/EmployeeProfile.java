
import javax.swing.*;
import javax.swing.border.*;

public class EmployeeProfile {
	
	JFrame frame = new JFrame("個人資料");
	JTextPane panel = new JTextPane();
	JScrollPane scrollPanel = new JScrollPane(panel);

	public EmployeeProfile() {
		frame.setSize(800, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel.setBorder(new EmptyBorder(20, 20, 5, 5));
		panel.setEditable(false);
		panel.setContentType("text/html");

		Tester.connectSQL();
		try {
			Tester.rs = Tester.statement.executeQuery("select * from User where id = " + Tester.ID);
			while (Tester.rs.next()) {
				panel.setText("<html><p style='font-family: sans-serif'>"
						+ "<span style='font-size: 45; color: #2C64BC'><b>" + Tester.rs.getString("name")
						+ "</b></span><br>" + "<span style='font-size: 17'><b>" + Tester.rs.getString("major")
						+ "</b></span><br>" + "<span style='font-size: 17; color: #666666'><b>"
						+ Tester.rs.getString("location") + "</b></span><br><br>"
						+ "<span style='font-size: 17'><b>電子信箱: </b>" + Tester.rs.getString("email") + "<br><br>"
						+ "<b>學歷:</b><br>" + Tester.rs.getString("education") + "<br><br>" + "<b>經歷:</b><br>"
						+ Tester.rs.getString("experience") + "<br><br>" + "<b>關於:</b><br>"
						+ Tester.rs.getString("about") + "</span></p></html>");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		panel.setCaretPosition(0);
		frame.add(scrollPanel);
		frame.setVisible(true);
	}
}