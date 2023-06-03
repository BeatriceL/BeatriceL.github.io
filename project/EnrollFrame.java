import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class EnrollFrame {
	private JFrame frame;
	private JPanel panel;
	private JLabel nameLabel;
	private JTextField nameText;
	private JLabel passwordLabel;
	private JPasswordField passwordText;
	private JLabel mailLabel;
	private JTextField mailText;
	private JButton sendBtn;
	private JLabel verifyLabel;
	private JTextField verifyText;
	private JButton createAccountBtn;
	private JButton clearBtn;
	private JDBC jdbc;
	private User user;

	public EnrollFrame() {
		createFrame();
		createPanel();
		createName();
		createPassword();
		createMail();
		createVerify();
		createAccount();
		createClear();

		panel.add(nameLabel);
		panel.add(nameText);
		panel.add(passwordLabel);
		panel.add(passwordText);
		panel.add(mailLabel);
		panel.add(mailText);
		panel.add(sendBtn);
		panel.add(verifyLabel);
		panel.add(verifyText);
		panel.add(createAccountBtn);
		panel.add(clearBtn);

		frame.add(panel);
	}

	public void createFrame() {
		frame = new JFrame();
		frame.setTitle("Enroll");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(600, 400);
		frame.setBackground(new Color(135, 206, 250));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Enroll");
		frame.setLocationRelativeTo(null);

	}

	public void createPanel() {
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(135, 206, 250));
	}

	public void createName() {
		nameLabel = new JLabel("Username");
		nameLabel.setBounds(100, 50, 115, 40);
		nameLabel.setForeground(Color.black);
		nameLabel.setFont(new Font("Noteworthy", Font.BOLD, 22));

		nameText = new JTextField(20);
		nameText.setBounds(230, 50, 230, 40);
		nameText.setBackground(Color.white);
		nameText.setForeground((new Color(205, 205, 206)));
		nameText.setCaretColor((new Color(205, 205, 206)));
		nameText.setFont(new Font("STXingKai", Font.BOLD, 20));
		nameText.setBorder(BorderFactory.createLineBorder(new Color(40, 42, 50)));
	}

	public void createPassword() {
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(100, 100, 115, 40);
		passwordLabel.setForeground(Color.black);
		passwordLabel.setFont(new Font("Noteworthy", Font.BOLD, 22));

		passwordText = new JPasswordField();
		passwordText.setBounds(230, 100, 230, 40);
		passwordText.setBackground(Color.white);
		passwordText.setForeground((new Color(205, 205, 206)));
		passwordText.setCaretColor((new Color(205, 205, 206)));
		passwordText.setFont(new Font("STXingKai", Font.BOLD, 20));
		passwordText.setBorder(BorderFactory.createLineBorder(new Color(40, 42, 50)));
	}

	public void createMail() {
		mailLabel = new JLabel("Mail");
		mailLabel.setBounds(100, 150, 115, 40);
		mailLabel.setForeground(Color.black);
		mailLabel.setFont(new Font("Noteworthy", Font.BOLD, 22));

		mailText = new JTextField(50);
		mailText.setBounds(230, 150, 230, 40);
		mailText.setBackground(Color.white);
		mailText.setForeground((new Color(205, 205, 206)));
		mailText.setCaretColor((new Color(205, 205, 206)));
		mailText.setFont(new Font("STXingKai", Font.BOLD, 20));
		mailText.setBorder(BorderFactory.createLineBorder(new Color(40, 42, 50)));

		sendBtn = new JButton();
		sendBtn.setText("Send Verfication Code");
		sendBtn.setBounds(230, 215, 200, 30);
		sendBtn.setFont(new Font("Noteworthy", Font.BOLD, 15));
		sendBtn.setBackground(Color.white);
		sendBtn.setForeground(new Color(0, 0, 205));
		sendBtn.setBorder(BorderFactory.createLineBorder(new Color(205, 205, 206)));
		sendBtn.setFocusable(false);

		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Send a verfication code to mail
			}
		});
	}

	public void createVerify() {
		verifyLabel = new JLabel();
		verifyLabel.setText("Verication Code");
		verifyLabel.setBounds(100, 255, 150, 40);
		verifyLabel.setForeground(Color.black);
		verifyLabel.setFont(new Font("Noteworthy", Font.BOLD, 22));

		verifyText = new JTextField(10);
		verifyText.setBounds(270, 260, 100, 30);
		verifyText.setBackground(Color.white);
		verifyText.setForeground((new Color(205, 205, 206)));
		verifyText.setCaretColor((new Color(205, 205, 206)));
		verifyText.setFont(new Font("STXingKai", Font.BOLD, 20));
		verifyText.setBorder(BorderFactory.createLineBorder(new Color(40, 42, 50)));
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void createAccount() {
		createAccountBtn = new JButton("Create Account");
		createAccountBtn.setText("Create Account");
		createAccountBtn.setBounds(165, 320, 117, 30);
		createAccountBtn.setFont(new Font("Noteworthy", Font.BOLD, 15));
		createAccountBtn.setBackground(Color.white);
		createAccountBtn.setForeground(new Color(0, 0, 205));
		createAccountBtn.setBorder(BorderFactory.createLineBorder(new Color(205, 205, 206)));
		createAccountBtn.setFocusable(false);

		createAccountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Connect to DB and create an account
				try {

					String inputAccount = nameText.getText();
					String inputPassword = String.valueOf(passwordText.getPassword());// getPassword是passwordText的語法
					String inputMail = mailText.getText();
					JDBC jdbc = new JDBC();

					//1.帳號密碼信箱為空則顯示警訊
					if (inputAccount.isEmpty() || inputPassword.isEmpty() || inputMail.isEmpty()) {// 如果未輸入東西
						// 未輸入東西應該要跳出警訊
						System.out.println("不可為空");
						return;
					}

					//2.Account重複||Email重複
					if(jdbc.checkExistingAccount(inputAccount)||jdbc.checkExistingEmail(inputMail)){//如果其中一個重複
						//帳號信箱重複應該要跳出警訊
						System.out.println("帳號或信箱重複");
						return;
					}

					//帳號密碼信箱都有填東西，也沒有重複
					jdbc.enroll(inputAccount, inputPassword, inputMail);//在資料庫建立檔案
					User user = new User(inputMail, inputPassword,inputMail);//在User class建立資料
					LoginFrame loginFrame = new LoginFrame();
					frame.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////



	public void createClear() {
		clearBtn = new JButton("Clear");
		clearBtn.setText("Clear");
		clearBtn.setBounds(375, 320, 117, 30);
		clearBtn.setFont(new Font("Noteworthy", Font.BOLD, 15));
		clearBtn.setBackground(Color.white);
		clearBtn.setForeground(new Color(0, 0, 205));
		clearBtn.setBorder(BorderFactory.createLineBorder(new Color(205, 205, 206)));
		clearBtn.setFocusable(true);
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameText.setText(null);
				mailText.setText(null);
				passwordText.setText(null);
				verifyText.setText(null);
			}
		});
	}
}