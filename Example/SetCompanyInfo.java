 
import java.awt.*;
import javax.swing.*;

public class SetCompanyInfo {

	GridBagConstraints c = new GridBagConstraints();
	Font inputFont = new Font("sans-serif", Font.PLAIN, 16);

	JFrame frame = new JFrame("新增公司");
	JPanel panel = new JPanel();
	JScrollPane scrollPanel = new JScrollPane(panel);
	JButton button = new JButton();

	JLabel account = new JLabel("帳號：");
	JLabel password = new JLabel("密碼：");
	JLabel name = new JLabel("公司名稱：");
	JLabel industry = new JLabel("產業類型：");
	JLabel location = new JLabel("公司地址：");
	JLabel website = new JLabel("官方網站：");
	JLabel about = new JLabel("關於：");
	JLabel field = new JLabel("專業領域：");

	JTextField iAccount = new JTextField();
	JPasswordField iPassword = new JPasswordField();
	JTextField iName = new JTextField();
	JTextField iIndustry = new JTextField();
	JTextField iLocation = new JTextField();
	JTextField iWebsite = new JTextField();
	JTextArea iAbout = new JTextArea();
	JTextArea iField = new JTextArea();

	public SetCompanyInfo() {
		frame.setSize(700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(new GridBagLayout());

		iAbout.setSize(new Dimension(350, iAbout.getPreferredSize().height));
		iAbout.setLineWrap(true);
		iAbout.setWrapStyleWord(true);
		iAbout.setFont(inputFont);
		iAbout.setBorder(iAccount.getBorder());

		iField.setSize(new Dimension(350, iField.getPreferredSize().height));
		iField.setLineWrap(true);
		iField.setWrapStyleWord(true);
		iField.setFont(inputFont);
		iField.setBorder(iAccount.getBorder());

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
		panel.add(industry, c);

		c.gridy = 4;
		panel.add(location, c);

		c.gridy = 5;
		panel.add(website, c);

		c.gridy = 6;
		panel.add(about, c);

		c.gridy = 7;
		panel.add(field, c);

		c.gridx = 1;
		c.gridy = 0;
		c.ipady = c.ipadx = 0;
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
		iIndustry.setPreferredSize(new Dimension(356, 15));
		iIndustry.setFont(inputFont);
		panel.add(iIndustry, c);

		c.gridy = 4;
		iLocation.setPreferredSize(new Dimension(356, 15));
		iLocation.setFont(inputFont);
		panel.add(iLocation, c);

		c.gridy = 5;
		iWebsite.setPreferredSize(new Dimension(356, 15));
		iWebsite.setFont(inputFont);
		panel.add(iWebsite, c);

		c.gridy = 6;
		c.ipadx = c.ipady = 0;
		c.insets.bottom = c.insets.top = 5;
		panel.add(iAbout, c);

		c.gridy = 7;
		panel.add(iField, c);

		c.gridy = 9;
		c.ipadx = 50;
		c.ipady = 10;
		c.insets.top = 20;
		c.fill = GridBagConstraints.VERTICAL;
		panel.add(button, c);

		frame.add(scrollPanel);
		frame.setVisible(true);
	}

	public boolean isFilled() {
		if (this.iAccount.getText().equals("") || getPassword().equals("") || this.iName.getText().equals("")
				|| this.iIndustry.getText().equals("") || this.iLocation.getText().equals("")
				|| this.iWebsite.getText().equals("") || this.iAbout.getText().equals("")
				|| this.iField.getText().equals("")) {
			JOptionPane.showMessageDialog(frame, "請確實填寫每個欄位", "錯誤!", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public String getPassword() {
		return String.valueOf(iPassword.getPassword());
	}
}