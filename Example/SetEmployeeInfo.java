
import java.awt.*;
import javax.swing.*;

public class SetEmployeeInfo {

	GridBagConstraints c = new GridBagConstraints();
	Font inputFont = new Font("sans-serif", Font.PLAIN, 16);

	JFrame frame = new JFrame("新增個人帳號");
	JPanel panel = new JPanel();
	JScrollPane scrollPanel = new JScrollPane(panel);
	JButton button = new JButton();

	JLabel account = new JLabel("帳號：");
	JLabel password = new JLabel("密碼：");
	JLabel name = new JLabel("姓名：");
	JLabel major = new JLabel("最高學歷：");
	JLabel location = new JLabel("所在地區：");
	JLabel email = new JLabel("電子信箱：");
	JLabel education = new JLabel("學歷：");
	JLabel experience = new JLabel("經歷：");
	JLabel about = new JLabel("關於：");

	JTextField iAccount = new JTextField();
	JPasswordField iPassword = new JPasswordField();
	JTextField iName = new JTextField();
	JTextField iMajor = new JTextField();
	JTextField iLocation = new JTextField();
	JTextField iEmail = new JTextField();
	JTextArea iEducation = new JTextArea();
	JTextArea iExperience = new JTextArea();
	JTextArea iAbout = new JTextArea();

	public SetEmployeeInfo() {
		frame.setSize(700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(new GridBagLayout());

		iEducation.setSize(new Dimension(350, iEducation.getPreferredSize().height));
		iEducation.setLineWrap(true);
		iEducation.setWrapStyleWord(true);
		iEducation.setFont(inputFont);
		iEducation.setBorder(iAccount.getBorder());

		iExperience.setSize(new Dimension(350, iExperience.getPreferredSize().height));
		iExperience.setLineWrap(true);
		iExperience.setWrapStyleWord(true);
		iExperience.setFont(inputFont);
		iExperience.setBorder(iAccount.getBorder());

		iAbout.setSize(new Dimension(350, iAbout.getPreferredSize().height));
		iAbout.setLineWrap(true);
		iAbout.setWrapStyleWord(true);
		iAbout.setFont(inputFont);
		iAbout.setBorder(iAccount.getBorder());

		c.gridx = c.gridy = 0;
		c.gridwidth = c.gridheight = 1;
		c.ipady = 20;
		c.ipadx = 0;
		c.insets.left = 5;
		c.anchor = GridBagConstraints.EAST;
		panel.add(account, c);

		c.gridy = 1;
		panel.add(password, c);

		c.gridy = 2;
		panel.add(name, c);

		c.gridy = 3;
		panel.add(major, c);

		c.gridy = 4;
		panel.add(location, c);

		c.gridy = 5;
		panel.add(email, c);

		c.gridy = 6;
		panel.add(education, c);

		c.gridy = 7;
		panel.add(experience, c);

		c.gridy = 8;
		panel.add(about, c);

		c.gridx = 1;
		c.gridy = c.ipadx = c.ipady = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.VERTICAL;
		iAccount.setPreferredSize(new Dimension(356, 15));
		iAccount.setFont(inputFont);
		panel.add(iAccount, c);

		c.gridy = 1;
		iPassword.setPreferredSize(new Dimension(356, 15));
		iPassword.setFont(inputFont);
		panel.add(iPassword, c);

		c.gridy = 2;
		iName.setPreferredSize(new Dimension(356, 15));
		iName.setFont(inputFont);
		panel.add(iName, c);

		c.gridy = 3;
		iMajor.setPreferredSize(new Dimension(356, 15));
		iMajor.setFont(inputFont);
		panel.add(iMajor, c);

		c.gridy = 4;
		iLocation.setPreferredSize(new Dimension(356, 15));
		iLocation.setFont(inputFont);
		panel.add(iLocation, c);

		c.gridy = 5;
		iEmail.setPreferredSize(new Dimension(356, 15));
		iEmail.setFont(inputFont);
		panel.add(iEmail, c);

		c.gridy = 6;
		c.ipadx = 0;
		c.insets.bottom = c.insets.top = 5;
		panel.add(iEducation, c);

		c.gridy = 7;
		panel.add(iExperience, c);

		c.gridy = 8;
		panel.add(iAbout, c);

		c.gridy = 9;
		c.ipadx = 50;
		c.ipady = 10;
		c.fill = GridBagConstraints.VERTICAL;
		panel.add(button, c);

		frame.add(scrollPanel);
		frame.setVisible(true);
	}

	public boolean isFilled() {
		if (this.iAccount.getText().equals("") || getPassword().equals("") || this.iName.getText().equals("")
				|| this.iMajor.getText().equals("") || this.iLocation.getText().equals("")
				|| this.iEmail.getText().equals("") || this.iEducation.getText().equals("")
				|| this.iExperience.getText().equals("") || this.iAbout.getText().equals("")) {
			JOptionPane.showMessageDialog(frame, "請確實填寫每個欄位", "錯誤!", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public String getPassword() {
		return String.valueOf(iPassword.getPassword());
	}
}