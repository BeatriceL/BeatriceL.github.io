import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import java.awt.*;

public class mainLogin {

	public static void main(String[] args) {
		GridBagConstraints c = new GridBagConstraints();

		JFrame frame = new JFrame("登入");
		JLabel account = new JLabel("帳號：");
		JLabel password = new JLabel("密碼：");
		JTextField inputAccount = new JTextField();
		JPasswordField inputPas = new JPasswordField();
		JButton login = new JButton("登入");
		JButton newAccount = new JButton("新增帳號");

		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());

	// 帳號 字串
		c.gridx = c.gridy = 0;
		c.gridwidth = c.gridheight = 1;
		c.ipady = 10;
		frame.add(account, c);
		
	// 密碼 字串
		c.gridy = 1;
		frame.add(password, c);
	// 帳號 輸入格
		c.gridx = 1;
		c.gridy = 0;
		c.ipady = 10;
		c.ipadx = 150;
		frame.add(inputAccount, c);
	//密碼 輸入格
		c.gridy = 1;
		frame.add(inputPas, c);
	//登入按鍵
		c.gridy = 3;
		c.ipady = 7;
		c.ipadx = 40;
		c.insets.top = 10;
		c.fill = GridBagConstraints.BOTH;
		frame.add(login, c);
	//新增帳號按鍵
		c.gridy = 4;
		c.insets.top = 0;
		frame.add(newAccount, c);

		frame.setVisible(true);
	}
}
