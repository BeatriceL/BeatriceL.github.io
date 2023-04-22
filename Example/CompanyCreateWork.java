
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CompanyCreateWork {

	Font inputFont = new Font("sans-serif", Font.PLAIN, 16);
	GridBagConstraints c = new GridBagConstraints();

	JFrame frame = new JFrame("新增工作");
	JPanel panel = new JPanel();
	JScrollPane scrollPanel = new JScrollPane(panel);

	JLabel title = new JLabel("職稱：");
	JLabel company = new JLabel("公司：");
	JLabel department = new JLabel("部門：");
	JLabel location = new JLabel("地點：");
	JLabel type = new JLabel("工作型態：");
	JLabel workingHours = new JLabel("工作時間：");
	JLabel wage = new JLabel("薪水：");
	JLabel experience = new JLabel("經驗要求：");
	JLabel major = new JLabel("學歷要求：");
	JLabel language = new JLabel("語言要求：");
	JLabel skills = new JLabel("技能要求：");
	JLabel other = new JLabel("其他要求：");
	JLabel description = new JLabel("工作介紹：");
	JLabel responsibilities = new JLabel("職責：");
	JLabel contact = new JLabel("聯絡方式：");

	JTextField iTitle = new JTextField();
	JTextField iCompany = new JTextField();
	JTextField iDepartment = new JTextField();
	JTextField iLocation = new JTextField();
	JComboBox<String> iType = new JComboBox<>();
	JTextField iWorkTime = new JTextField();
	JTextField iWage = new JTextField();
	JTextArea iExperience = new JTextArea();
	JTextArea iMajor = new JTextArea();
	JTextArea iLanguage = new JTextArea();
	JTextArea iSkills = new JTextArea();
	JTextArea iOther = new JTextArea();
	JTextArea iDescription = new JTextArea();
	JTextArea iResponse = new JTextArea();
	JTextField iContact = new JTextField();
	ArrayList<JTextArea> areaList = new ArrayList<>();

	JButton button = new JButton("新增");

	public CompanyCreateWork() {
		frame.setSize(800, 700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel.setLayout(new GridBagLayout());

		iType.addItem("全職");
		iType.addItem("兼職");
		iType.addItem("實習");

		areaList.add(iExperience);
		areaList.add(iMajor);
		areaList.add(iLanguage);
		areaList.add(iSkills);
		areaList.add(iOther);
		areaList.add(iDescription);
		areaList.add(iResponse);

		for (JTextArea a : areaList) {
			a.setSize(new Dimension(350, iExperience.getPreferredSize().height));
			a.setLineWrap(true);
			a.setWrapStyleWord(true);
			a.setFont(inputFont);
			a.setBorder(iTitle.getBorder());
		}

		iTitle.setPreferredSize(new Dimension(356, 16));
		iDepartment.setPreferredSize(new Dimension(356, 16));
		iLocation.setPreferredSize(new Dimension(356, 16));
		iWorkTime.setPreferredSize(new Dimension(356, 16));
		iWage.setPreferredSize(new Dimension(356, 16));
		iContact.setPreferredSize(new Dimension(356, 16));

		iTitle.setFont(inputFont);
		iDepartment.setFont(inputFont);
		iLocation.setFont(inputFont);
		iWorkTime.setFont(inputFont);
		iWage.setFont(inputFont);
		iContact.setFont(inputFont);

		c.gridwidth = c.gridheight = 1;
		c.gridx = c.gridy = 0;
		c.ipady = 20;
		c.anchor = GridBagConstraints.EAST;
		panel.add(title, c);

		c.gridy = 1;
		panel.add(department, c);

		c.gridy = 2;
		panel.add(location, c);

		c.gridy = 3;
		panel.add(type, c);

		c.gridy = 4;
		panel.add(workingHours, c);

		c.gridy = 5;
		panel.add(wage, c);

		c.gridy = 6;
		panel.add(experience, c);

		c.gridy = 7;
		panel.add(major, c);

		c.gridy = 8;
		panel.add(language, c);

		c.gridy = 9;
		panel.add(skills, c);

		c.gridy = 10;
		panel.add(other, c);

		c.gridy = 11;
		panel.add(description, c);

		c.gridy = 12;
		panel.add(responsibilities, c);

		c.gridy = 13;
		panel.add(contact, c);

		c.gridx = 1;
		c.gridy = c.ipadx = c.ipady = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.VERTICAL;
		c.insets.bottom = c.insets.left = 5;
		panel.add(iTitle, c);

		c.gridy = 1;
		panel.add(iDepartment, c);

		c.gridy = 2;
		panel.add(iLocation, c);

		c.gridy = 3;
		c.ipadx = 275;
		panel.add(iType, c);

		c.gridy = 4;
		c.ipadx = 0;
		panel.add(iWorkTime, c);

		c.gridy = 5;
		panel.add(iWage, c);

		c.gridy = 6;
		c.ipady = 0;
		c.insets.top = 5;
		panel.add(iExperience, c);

		c.gridy = 7;
		panel.add(iMajor, c);

		c.gridy = 8;
		panel.add(iLanguage, c);

		c.gridy = 9;
		panel.add(iSkills, c);

		c.gridy = 10;
		panel.add(iOther, c);

		c.gridy = 11;
		panel.add(iDescription, c);

		c.gridy = 12;
		panel.add(iResponse, c);

		c.gridy = 13;
		c.ipady = 16;
		panel.add(iContact, c);

		c.gridy = 14;
		c.ipadx = 50;
		c.ipady = 15;
		panel.add(button, c);

		frame.add(scrollPanel);
		frame.setVisible(true);
	}

	public boolean isFilled() {
		if (iTitle.getText().equals("") || iCompany.getText().equals("") || iDepartment.getText().equals("")
				|| iLocation.getText().equals("") || iWorkTime.getText().equals("") || iWage.getText().equals("")
				|| iExperience.getText().equals("") || iMajor.getText().equals("") || iLanguage.getText().equals("")
				|| iSkills.getText().equals("") || iOther.getText().equals("") || iDescription.getText().equals("")
				|| iResponse.getText().equals("") || iContact.getText().equals("")) {
			JOptionPane.showMessageDialog(frame, "請確實填寫每個欄位", "錯誤", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	public void addWork(String title, int company, String department, String location, String type, String workTime,
			String wage, String experience, String major, String language, String skills, String other,
			String description, String response, String contact) {
		try {
			Tester.connectSQL();
			Tester.statement.executeUpdate(
					"INSERT INTO Work (title, company, departament, location, type, working hours, wage, experience req, major req, language req, skills req, other req, description, responsibilities, contact info) VALUES ('"
							+ title + "', '" + company + "', '" + department + "', '" + location + "', '" + type
							+ "', '" + workTime + "', '" + wage + "', '" + experience + "', '" + major + "', '"
							+ language + "', '" + skills + "', '" + other + "', '" + description + "', '" + response
							+ "', '" + contact + "')");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (isFilled()) {
				addWork(iTitle.getText(), Tester.ID, iDepartment.getText(), iLocation.getText(),
						iType.getActionCommand(), iWorkTime.getText(), iWage.getText(), iExperience.getText(),
						iMajor.getText(), iLanguage.getText(), iSkills.getText(), iOther.getText(),
						iDescription.getText(), iResponse.getText(), iContact.getText());
				JOptionPane.showMessageDialog(frame, "新增成功", "系統提示", JOptionPane.INFORMATION_MESSAGE);
				frame.setVisible(false);
			}
		}
	}
}