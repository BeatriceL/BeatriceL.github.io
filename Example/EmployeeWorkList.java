
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class EmployeeWorkList extends EmployeeMenu {

	public EmployeeWorkList() {
		workList.setText("重整工作清單");
		loadList();
		searchButton.addActionListener(new searchListener());
		elementList.addListSelectionListener(new listListener());

		frame.setVisible(true);
	}

	public void loadList() {
		try {
			l.removeAllElements();
			idList.removeAll(idList);
			Tester.connectSQL();
			Tester.rs = Tester.statement.executeQuery(
					"SELECT Work.id, Work.title, department, name FROM Work, Company WHERE Work.company = Company.id");
			while (Tester.rs.next()) {
				l.addElement("<html><br><font size= '6'; font color= '#2C64BC'><b>" + Tester.rs.getString("title")
						+ "</b></font><br><font size= '4'; font color= '#666666'>" + Tester.rs.getString("name")
						+ "	" + Tester.rs.getString("department") + "<br><br></font></html>");
				idList.add(Tester.rs.getInt("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setWork(int id) {
		try {
			Tester.connectSQL();
			Tester.rs = Tester.statement.executeQuery(
					"SELECT Work.*, name FROM Work, Company WHERE Work.company = Company.id AND Work.id = " + id);
			String content = null;
			while (Tester.rs.next()) {
				content = "<html>" + "<p style='font-size: 50; color: #2C64BC; font-family: sans-serif'><b>"
						+ Tester.rs.getString("title") + "</b></p>" + "<p style='font-family: sans-serif;'>"
						+ "<span style='font-size: 23'><b>" + Tester.rs.getString("name") + "		"
						+ Tester.rs.getString("location") + "</b><br>" + "<b>" + Tester.rs.getString("type")
						+ "</b><br>" + "<b>薪資: " + Tester.rs.getString("wage") + "</b><br><br><br></span>"
						+ "<span style='font-size: 17'><b>經驗要求:</b><br>"
						+ Tester.rs.getString("experience").replaceAll("\n", "<br>") + "<br><br>" + "<b>學歷要求:</b><br>"
						+ Tester.rs.getString("major").replaceAll("\n", "<br>") + "<br><br>" + "<b>語言要求:</b><br>"
						+ Tester.rs.getString("language").replaceAll("\n", "<br>") + "<br><br>" + "<b>技能要求:</b><br>"
						+ Tester.rs.getString("skills").replaceAll("\n", "<br>") + "<br><br>" + "<b>其他要求:</b><br>"
						+ Tester.rs.getString("other").replaceAll("\n", "<br>") + "<br><br>" + "<b>工作介紹:</b><br>"
						+ Tester.rs.getString("description").replaceAll("\n", "<br>") + "<br><br>" + "<b>職責:</b><br>"
						+ Tester.rs.getString("responsibilities").replaceAll("\n", "<br>") + "<br><br>"
						+ "<b>聯絡方式:  </b>" + Tester.rs.getString("contact") + "</p><br><br><br>";
			}
			Tester.connectSQL();
			Tester.rs = Tester.statement.executeQuery(
					"SELECT Company.* FROM Company, Work WHERE Company.id = Work.company AND Work.id = " + id);
			while (Tester.rs.next()) {
				content += "<p style='font-family: sans-serif; border-left: 5; padding: 30'>"
						+ "<span style='font-size: 23'><b>關於此公司</b><br><br></span>"
						+ "<span style='font-size: 45; color: #2C64BC'><b>" + Tester.rs.getString("name")
						+ "</b></span><br>" + "<span style='font-size: 17; color: #666666'>"
						+ Tester.rs.getString("industry") + "・" + Tester.rs.getString("location")
						+ "</span><br><br><br>" + "<span style='font-size: 17'><b>總覽:</b><br>"
						+ Tester.rs.getString("about") + "<br><br>" + "<b>網站:</b><br>" + Tester.rs.getString("website")
						+ "<br><br>" + "<b>領域:</b><br>" + Tester.rs.getString("field") + "</span></p></html>";
			}
			panel.setText(content);
			panel.setCaretPosition(0);
			panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			setCollectionButton(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	class searchListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				l.removeAllElements();
				idList.removeAll(idList);
				Tester.connectSQL();
				Tester.rs = Tester.statement
						.executeQuery("SELECT Work.*, Company.name FROM Work, Company WHERE Company.name LIKE '%"
								+ search.getText() + "%' AND Work.company = Company.id OR Work.title LIKE '%"
								+ search.getText() + "%' AND Work.company = Company.id OR Work.department LIKE '%"
								+ search.getText() + "%' AND Work.company = Company.id");
				while (Tester.rs.next()) {
					l.addElement("<html><br><font size= '6'; font color= '#2C64BC'><b>" + Tester.rs.getString("title")
					+ "</b></font><br><font size= '4'; font color= '#666666'>" + Tester.rs.getString("name")
					+ "	" + Tester.rs.getString("department") + "<br><br></font></html>");
					idList.add(Tester.rs.getInt("id"));
				}
				if (idList.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "查無資料!", "錯誤", JOptionPane.WARNING_MESSAGE);
					search.setText("");
					loadList();
				}
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}

	class listListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			try {
				if (e.getValueIsAdjusting()) {
					setWork(idList.get(elementList.getSelectedIndex()));
				}
			} catch (IndexOutOfBoundsException exce) {
				exce.printStackTrace();
			}
		}
	}
}