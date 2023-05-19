
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class EmployeeMenu extends Menu {

	JMenu user;
	JMenuItem info = new JMenuItem("查看個人檔案");
	JMenuItem logout = new JMenuItem("登出");
	JMenu updateData = new JMenu("修改資料");
	JMenuItem updateInfo = new JMenuItem("修改檔案");
	JMenuItem updatePas = new JMenuItem("修改密碼");

	Action work = new AbstractAction("所有工作") {
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			EmployeeWorkList workListFrame = new EmployeeWorkList();
		}
	};
	JToggleButton workList = new JToggleButton(work);

	Action userCollection = new AbstractAction("收藏列表") {
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			EmployeeCollection collectionFrame = new EmployeeCollection();
		}
	};
	JToggleButton collection = new JToggleButton(userCollection);

	Action userAbout = new AbstractAction("說明") {
		public void actionPerformed(ActionEvent e) {
			About aboutFrame = new About();
		}
	};
	JToggleButton about = new JToggleButton(userAbout);

	public EmployeeMenu() {
		user = new JMenu(Tester.name);

		info.addActionListener(new menuItemListener());
		info.setActionCommand("profile");
		user.add(info);

		logout.addActionListener(new menuItemListener());
		logout.setActionCommand("logout");
		user.add(logout);

		workList.setBorder(new EmptyBorder(6, 7, 0, 7));
		workList.setFont(menuBarFont);

		collection.setBorder(new EmptyBorder(6, 7, 0, 7));
		collection.setFont(menuBarFont);

		updateInfo.addActionListener(new menuItemListener());
		updateInfo.setActionCommand("updateInfo");
		updateData.add(updateInfo);

		updatePas.addActionListener(new menuItemListener());
		updatePas.setActionCommand("updatePas");
		updateData.add(updatePas);

		about.setBorder(new EmptyBorder(6, 7, 0, 0));
		about.setFont(menuBarFont);

		menuBar.add(user);
		menuBar.add(workList);
		menuBar.add(collection);
		menuBar.add(updateData);
		menuBar.add(about);
	}

	public void setCollectionButton(int elementID) {
		try {
			for (ActionListener a : button.getActionListeners()) {
				button.removeActionListener(a);
			}
			boolean set = false;
			Tester.connectSQL();
			Tester.rs = Tester.statement.executeQuery("SELECT * FROM Collection WHERE user = " + Tester.ID);
			while (Tester.rs.next()) {
				if (Tester.rs.getInt("work") == elementID) {
					set = true;
				}
			}
			if (set == true) {
				button.setText("取消收藏");
				class dropCollectionListener implements ActionListener {
					public void actionPerformed(ActionEvent e) {
						try {
							Tester.connectSQL();
							Tester.statement.execute(
									"DELETE FROM Collection WHERE user = " + Tester.ID + " AND work = " + elementID);
							setCollectionButton(elementID);
						} catch (Exception exception) {
							exception.printStackTrace();
						}
					}
				}
				button.addActionListener(new dropCollectionListener());
			} else {
				button.setText("加入收藏");
				class addCollectionListener implements ActionListener {
					public void actionPerformed(ActionEvent e) {
						try {
							Tester.connectSQL();
							Tester.statement
									.execute("INSERT INTO Collection VALUES (" + Tester.ID + ", " + elementID + ")");
							setCollectionButton(elementID);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
				button.addActionListener(new addCollectionListener());
			}
			button.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class menuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "profile":
				EmployeeProfile porfileFrame = new EmployeeProfile();
				break;
			case "logout":
				System.exit(0);
				break;
			case "updateInfo":
				EmployeeChangeInfo changeInfoFrame = new EmployeeChangeInfo();
				break;
			case "updatePas":
				EmployeeChangePassword changePasswordFrame = new EmployeeChangePassword();
				break;
			}
		}
	}
}