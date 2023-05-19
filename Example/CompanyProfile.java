
import javax.swing.*;
import javax.swing.border.*;

public class CompanyProfile {

	JFrame frame = new JFrame("公司資料");
	JTextPane panel = new JTextPane();
	JScrollPane scrollPanel = new JScrollPane(panel);

	public CompanyProfile() {
		frame.setSize(800, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel.setBorder(new EmptyBorder(20, 20, 5, 5));
		panel.setContentType("text/html");
		panel.setEditable(false);

		try {
			Tester.connectSQL();
			Tester.rs = Tester.statement.executeQuery("SELECT * FROM Company WHERE id = " + Tester.ID);
			while (Tester.rs.next()) {
				panel.setText("<html><p style=' font-family: sans-serif'>"
						+ "<span style='font-size: 45; color: #2C64BC'><b>" + Tester.rs.getString("name")
						+ "</b></span><br>" + "<span style='font-size: 17; color: #666666'><b>"
						+ Tester.rs.getString("industry") + "・" + Tester.rs.getString("location")
						+ "</b></span><br><br><br>" + "<span style='font-size: 17'><b>總覽:</b><br>"
						+ Tester.rs.getString("about") + "<br><br>" + "<b>網站: </b>" + Tester.rs.getString("website")
						+ "<br><br>" + "<b>領域:</b><br>" + Tester.rs.getString("field") + "</span></p></html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		panel.setCaretPosition(0);
		frame.add(scrollPanel);
		frame.setVisible(true);
	}
}