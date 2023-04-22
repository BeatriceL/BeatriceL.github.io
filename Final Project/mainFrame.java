
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class mainFrame extends JFrame{

	private JLabel websiteLabel = new JLabel("政大交流版");
	private JButton create = new JButton("註冊");
	private JButton login = new JButton("登入");
	private JButton intern = new JButton("實習");
	private JButton abroad = new JButton("出國");
	private JButton homework = new JButton("課業");
	private JButton chat = new JButton("閒聊");
	Font menuBarFont = new Font("新細明體", Font.PLAIN, 8);
	public mainFrame() {
		createLayout();
	}
	private void createLayout(){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		websiteLabel.setBounds(70, 20, 100, 30);
		websiteLabel.setFont(menuBarFont);
		create.setBounds(150, 20, 10, 5);
		create.setFont(menuBarFont);
		login.setBounds(160, 20, 10, 5);
		login.setFont(menuBarFont);
		intern.setBounds(40, 50, 50, 30);
		intern.setFont(menuBarFont);
		abroad.setBounds(100, 50, 50, 30);
		abroad.setFont(menuBarFont);
		homework.setBounds(40, 100, 50, 30);
		homework.setFont(menuBarFont);
		chat.setBounds(100, 100, 50, 30);
		chat.setFont(menuBarFont);
		panel.add(websiteLabel);
		panel.add(create);
		panel.add(login);
		panel.add(intern);
		panel.add(abroad);
		panel.add(homework);
		panel.add(chat);
		add(panel);
	}
}
