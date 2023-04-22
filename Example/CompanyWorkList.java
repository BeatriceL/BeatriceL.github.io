
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class CompanyWorkList extends Menu {

	JMenu company;
	JMenuItem info = new JMenuItem("查看公司檔案");
	JMenuItem logout = new JMenuItem("登出");
	JMenu updateData = new JMenu("修改資料");
	JMenuItem updateInfo = new JMenuItem("修改檔案");
	JMenuItem updatePas = new JMenuItem("修改密碼");

	Action work = new AbstractAction("重整刊登清單") {
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			CompanyWorkList workListFrame = new CompanyWorkList();
		}
	};
	JToggleButton reload = new JToggleButton(work);

	Action add = new AbstractAction("新增工作") {
		public void actionPerformed(ActionEvent e) {
			CompanyCreateWork createWorkFrame = new CompanyCreateWork();
		}
	};
	JToggleButton newWork = new JToggleButton(add);

	Action companyAbout = new AbstractAction("說明") {
		public void actionPerformed(ActionEvent e) {
			About aboutFrame = new About();
		}
	};
	JToggleButton about = new JToggleButton(companyAbout);

	
	public CompanyWorkList() {
		company = new JMenu(Tester.name);
		menuBar.add(company);
		menuBar.add(reload);
		menuBar.add(newWork);
		menuBar.add(updateData);
		menuBar.add(about);

		info.addActionListener(new menuItemListener());
		info.setActionCommand("info");
		company.add(info);

		logout.addActionListener(new menuItemListener());
		logout.setActionCommand("logout");
		company.add(logout);

		reload.setBorder(new EmptyBorder(6, 7, 0, 7));
		reload.setFont(menuBarFont);

		newWork.setBorder(new EmptyBorder(6, 7, 0, 7));
		newWork.setFont(menuBarFont);

		updateInfo.addActionListener(new menuItemListener());
		updateInfo.setActionCommand("updateInfo");
		updateData.add(updateInfo);

		updatePas.addActionListener(new menuItemListener());
		updatePas.setActionCommand("updatePas");
		updateData.add(updatePas);

		about.setBorder(new EmptyBorder(6, 7, 0, 7));
		about.setFont(menuBarFont);

		loadList();
		elementList.addListSelectionListener(new ListListener());
		searchButton.addActionListener(new searchButtonListener());

		frame.setVisible(true);
	}

	public void loadList() {
		try {
			l.removeAllElements();
			idList.removeAll(idList);
			Tester.connectSQL();
			Tester.rs = Tester.statement.executeQuery(
					"SELECT Work.id, Work.title, department FROM Company, Work WHERE Work.company = Company.id AND Company.id = "
							+ Tester.ID);
			while (Tester.rs.next()) {
				l.addElement("<html><br><font size= '6'; font color= '#2C64BC'><b>" + Tester.rs.getString("title")
						+ "</b></font><br><font size= '4'; font color= '#666666'>" + Tester.rs.getString("department")
						+ "<br><br></font></html>");
				idList.add(Tester.rs.getInt("id"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void setWork(int id) {
		try {
			Tester.connectSQL();
			Tester.rs = Tester.statement.executeQuery("SELECT * FROM Work WHERE Work.id = " + id);
			while (Tester.rs.next()) {
				panel.setText("<html>" + "<p style='font-size: 50; color: #2C64BC; font-family: sans-serif'><b>"
						+ Tester.rs.getString("title") + "</b></p>" + "<p style='font-family: sans-serif;'>"
						+ "<span style='font-size: 23'><b>" + Tester.rs.getString("location") + "</b><br>" + "<b>"
						+ Tester.rs.getString("type") + "</b><br>" + "<b>薪資: " + Tester.rs.getString("wage")
						+ "</b><br><br><br></span>" + "<span style='font-size: 17'><b>經驗要求:</b><br>"
						+ Tester.rs.getString("experience").replaceAll("\n", "<br>") + "<br><br>" + "<b>學歷要求:</b><br>"
						+ Tester.rs.getString("major").replaceAll("\n", "<br>") + "<br><br>" + "<b>語言要求:</b><br>"
						+ Tester.rs.getString("language").replaceAll("\n", "<br>") + "<br><br>" + "<b>技能要求:</b><br>"
						+ Tester.rs.getString("skills").replaceAll("\n", "<br>") + "<br><br>" + "<b>其他要求:</b><br>"
						+ Tester.rs.getString("other").replaceAll("\n", "<br>") + "<br><br>" + "<b>工作介紹:</b><br>"
						+ Tester.rs.getString("description").replaceAll("\n", "<br>") + "<br><br>" + "<b>職責:</b><br>"
						+ Tester.rs.getString("responsibilities").replaceAll("\n", "<br>") + "<br><br>"
						+ "<b>聯絡方式:  </b>" + Tester.rs.getString("contact") + "</p><br><br><br>");
			}
			panel.setCaretPosition(0);
			panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			setDeleteButton(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setDeleteButton(int elementID) {
		try {
			for (ActionListener a : button.getActionListeners()) {
				button.removeActionListener(a);
			}
			button.setText("刪除工作");
			class deleteWorkListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					try {
						Tester.connectSQL();
						Tester.statement.executeUpdate("DELETE FROM Work WHERE id = " + elementID);
						Tester.statement.executeUpdate("DELETE FROM Collection WHERE work = " + elementID);
						frame.setVisible(false);
						CompanyWorkList workList = new CompanyWorkList();
					} catch (Exception excep) {
						excep.printStackTrace();
					}
				}
			}
			button.addActionListener(new deleteWorkListener());
			button.setVisible(true);
		} catch (Exception exce) {
			exce.printStackTrace();
		}
	}

	
	class ListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			try {
				if (e.getValueIsAdjusting()) {
					setWork(idList.get(elementList.getSelectedIndex()));
				}
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}

	class searchButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				l.removeAllElements();
				idList.removeAll(idList);
				Tester.connectSQL();
				Tester.rs = Tester.statement.executeQuery(
						"SELECT * FROM Work WHERE company = " + Tester.ID + " AND title LIKE '%" + search.getText()
								+ "%' OR company = " + Tester.ID + " AND department LIKE '%" + search.getText() + "%'");
				while (Tester.rs.next()) {
					l.addElement("<html><br><font size= '6'; font color= '#2C64BC'><b>" + Tester.rs.getString("title")
					+ "</b></font><br><font size= '4'; font color= '#666666'>" + Tester.rs.getString("department")
					+ "<br><br></font></html>");
					idList.add(Tester.rs.getInt("id"));
				}
				if (idList.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "查無工作!", "錯誤", JOptionPane.WARNING_MESSAGE);
					search.setText("");
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
	
	class menuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "info":
				CompanyProfile profileFrame = new CompanyProfile();
				break;
			case "logout":
				System.exit(0);
				break;
			case "updateInfo":
				CompanyChangeInfo changeInfoFrame = new CompanyChangeInfo();
				break;
			case "updatePas":
				CompanyChangePassword changePasFrame = new CompanyChangePassword();
				break;
			}
		}
	}
}