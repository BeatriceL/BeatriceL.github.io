import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;

public class LoginFrame {
	private JPanel panel;
	private JFrame frame;
	private JTextField accountText;
	private JPasswordField passwordText;
	private JLabel accountLabel;
	private JLabel passwordLabel;
	private JLabel title;
	private JButton loginBtn;
	private JButton enrollBtn;

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		createPanel();
		createFrame();
		createTitle();
		createAccount();
		createPassword();
		createLogin();
		createEnroll();

		panel.add(title);
		panel.add(accountLabel);
		panel.add(accountText);
		panel.add(passwordLabel);
		panel.add(passwordText);
		panel.add(loginBtn);
		panel.add(enrollBtn);
		frame.add(panel);

	}

	public void createFrame() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(600, 400);
		frame.setBackground(new Color(135, 206, 250));
		frame.setLocationRelativeTo(null);
	}

	public void createPanel() {
		panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setLayout(null);
	}

	public void createTitle() {
		title = new JLabel("NCCU Communication Platform");
		title.setBounds(150, 10, 400, 80);
		title.setForeground(new Color(0, 0, 0));
		title.setFont(new Font("Noteworthy", Font.BOLD, 22));
	}

	public void createAccount() {
		accountLabel = new JLabel("Account");
		accountLabel.setBounds(150, 60, 90, 55);
		accountLabel.setFont(new Font("Noteworthy", Font.BOLD, 18));

		accountText = new JTextField(50);
		accountText.setBounds(150, 105, 280, 35);
		accountText.setForeground(new Color(112, 128, 144));
		accountText.setFont(new Font("STXingKai", Font.BOLD, 20));
		accountText.setBackground(new Color(254, 255, 255));
		accountText.setCaretColor(new Color(112, 128, 144));
		accountText.setBorder(BorderFactory.createLineBorder(new Color(40, 42, 50)));
	}

	public void createPassword() {
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(150, 135, 90, 55);
		passwordLabel.setFont(new Font("Noteworthy", Font.BOLD, 18));

		passwordText = new JPasswordField(30);
		passwordText.setBounds(150, 175, 280, 35);
		passwordText.setForeground(new Color(112, 128, 144));
		passwordText.setFont(new Font("STXingKai", Font.BOLD, 20));
		passwordText.setBackground(new Color(254, 255, 255));
		passwordText.setCaretColor(new Color(112, 128, 144));
		passwordText.setVisible(true);
		passwordText.setBorder(BorderFactory.createLineBorder(new Color(40, 42, 50)));
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	public void createLogin() {
		loginBtn = new JButton("Login");
		loginBtn.setText("Login");
		loginBtn.setBounds(200, 240, 200, 30);
		loginBtn.setFont(new Font("Noteworthy", Font.BOLD, 15));
		loginBtn.setBackground(Color.white);
		loginBtn.setForeground(new Color(0, 0, 205));
		loginBtn.setBorder(BorderFactory.createLineBorder(new Color(205, 205, 206)));
		loginBtn.setFocusable(false);

		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Connect to DB
				JDBC jdbc = new JDBC();
				String inputAccount = accountText.getText();
				String inputPassword = String.valueOf(passwordText.getPassword());

				// 資料庫加新東西
				try {

					if (inputAccount.isEmpty() || inputPassword.isEmpty()) {// 如果未輸入東西
						// 未輸入東西應該要跳出警訊
						System.out.println("未輸入帳號密碼");
					} else if (jdbc.login(inputAccount, inputPassword)) {// 如果帳號密碼都正確 //有問題要做的事寫在JDBC
						String storedEmail = jdbc.getEmailFromAccount(inputAccount);
						User user = new User(inputAccount,inputPassword,storedEmail);// 讓現在的User有相對應的帳號和密碼
						// 實例化GUI class
		                Homepage homepage = new Homepage();
		                
		                // 顯示目標GUI
		                homepage.setVisible(true);
		                
		                // 關閉當前GUI
						frame.dispose();
						
						System.out.println("你的帳號資訊"+user.getName()+user.getPassword()+user.getEmail());
					} 

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////


	/////////////////////////////////////////////////////////////////////////////////////////////////////
	public void createEnroll() {
		enrollBtn = new JButton("Enroll");
		enrollBtn.setText("Enroll");
		enrollBtn.setBounds(200, 295, 200, 30);
		enrollBtn.setFont(new Font("Noteworthy", Font.BOLD, 15));
		enrollBtn.setBackground(Color.white);
		enrollBtn.setForeground(new Color(0, 0, 205));
		enrollBtn.setBorder(BorderFactory.createLineBorder(new Color(205, 205, 206)));
		enrollBtn.setFocusable(false);

		enrollBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				EnrollFrame enroll = new EnrollFrame();
			}
		});
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}