import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class ProfileFrame extends JFrame{

    private JPanel panel;
    private JLabel title, nameLabel, mailLabel, revisLabel, checkAgainLabel, warning;
    private JTextField nameText, mailText, revisText, checkAgainText;
    private JButton conformBtn, homeBtn, postBtn;
    private JDBC jdbc;
    private User loggedUser;

    public ProfileFrame(User user) {       
        createFrame();
        createPanel();
        createLabel();
        createTextField();
        createButton();
        createLayout();
        this.loggedUser = user;
        setProfile();
        
    }

    public void createFrame() {
    	setTitle("Profile");
    	setVisible(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
        setBackground(new Color(135, 206, 250));
       	setLocationRelativeTo(null);
    }

    public void createPanel() {
        panel = new JPanel();
        panel.setLayout(null);
    }

    public void createLabel() {
    	 title = new JLabel("My Profile");
         title.setBounds(10, 5, 250, 60);
         title.setForeground(Color.BLACK);
         title.setFont(new Font("Noteworthy", Font.BOLD, 35));
         
         nameLabel = new JLabel("Username");
         nameLabel.setBounds(150, 100, 90, 25);
         nameLabel.setForeground(Color.BLACK);
         nameLabel.setFont(new Font("Noteworthy", Font.BOLD, 18));
         
         mailLabel = new JLabel("Mail");
         mailLabel.setBounds(195, 130, 90, 25);
         mailLabel.setForeground(Color.BLACK);
         mailLabel.setFont(new Font("Noteworthy", Font.BOLD, 18));
         
         revisLabel = new JLabel("Revise password");
         revisLabel.setBounds(97, 160, 180, 25);
         revisLabel.setForeground(Color.BLACK);
         revisLabel.setFont(new Font("Noteworthy", Font.BOLD, 18));
         
         checkAgainLabel = new JLabel("Check again");
         checkAgainLabel.setBounds(135, 190, 180, 30);
         checkAgainLabel.setForeground(Color.BLACK);
         checkAgainLabel.setFont(new Font("Noteworthy", Font.BOLD, 18));
         
         warning = new JLabel("Wrong password, Please check again !");
         warning.setBounds(180, 320, 300, 25);
         warning.setForeground(Color.red);
         warning.setFont(new Font("Noteworthy", Font.BOLD, 15));
         warning.setVisible(false);
         warning.setBorder(BorderFactory.createLineBorder(Color.red));
    }
    
    public void createTextField() {
    	nameText = new JTextField(20);
        nameText.setBounds(250, 100, 200, 25);
        nameText.setBackground(new Color(254, 254, 247));
        nameText.setForeground(Color.BLACK);
        nameText.setCaretColor(Color.BLACK);
        nameText.setFont(new Font("STXingKai", Font.BOLD, 20));
        nameText.setBorder(BorderFactory.createLineBorder(new Color(40, 42, 50)));
        nameText.setEditable(false);
        
        mailText = new JTextField(30);
        mailText.setBounds(250, 130, 200, 25);
        mailText.setBackground(new Color(254, 254, 247));
        mailText.setForeground(Color.BLACK);
        mailText.setCaretColor(Color.BLACK);
        mailText.setFont(new Font("STXingKai", Font.BOLD, 18));
        mailText.setBorder(BorderFactory.createLineBorder(new Color(40, 42, 50)));
        mailText.setEditable(false);
        
        revisText = new JTextField(20);
        revisText.setBounds(250, 160, 200, 25);
        revisText.setBackground(new Color(254, 254, 247));
        revisText.setForeground(Color.BLACK);
        revisText.setCaretColor(Color.BLACK);
        revisText.setFont(new Font("STXingKai", Font.BOLD, 18));
        revisText.setBorder(BorderFactory.createLineBorder(new Color(40, 42, 50)));
        
        checkAgainText = new JTextField(20);
        checkAgainText.setBounds(250, 190, 200, 25);
        checkAgainText.setBackground(new Color(254, 254, 247));
        checkAgainText.setForeground(Color.BLACK);
        checkAgainText.setForeground(Color.BLACK);
        checkAgainText.setFont(new Font("STXingKai", Font.BOLD, 18));
        checkAgainText.setBorder(BorderFactory.createLineBorder(new Color(40, 42, 50)));
    }

    public void createButton() {
        conformBtn = new JButton("Store");
        conformBtn.setBounds(110, 420, 80, 25);
        conformBtn.setBackground(Color.gray);
        conformBtn.setForeground((new Color(0, 0, 205)));
        conformBtn.setFont(new Font("Noteworthy", Font.BOLD, 18));
        conformBtn.setFocusable(false);
        conformBtn.setBorder(BorderFactory.createLineBorder(new Color(40, 42, 50)));
        conformBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String revise = revisText.getText();//慾更該地密碼
                String check = checkAgainText.getText();//確認密碼

                if (revise.isEmpty() || check.isEmpty()) {//確認兩格不可為空
                    JOptionPane.showMessageDialog(null, "Please fill out your checked password!", "Warning",
                            JOptionPane.ERROR_MESSAGE);
                } else if (revise.equals(check)) {//如果兩者相等

                    warning.setVisible(false);
                    revisText.setText("");
                    checkAgainText.setText("");
                    
                    // Connect to JDBC and revise the password
                    // user.editProfile(nameInput.getText(), revise);
                    loggedUser.editProfile(check);


                    JOptionPane.showMessageDialog(null, "Successfully revised!", "Notification",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    warning.setVisible(true);

                }

            }
        });

        homeBtn = new JButton("Home");
        homeBtn.setBounds(220, 420, 80, 25);
        homeBtn.setBackground(Color.gray);
        homeBtn.setForeground((new Color(0, 0, 205)));
        homeBtn.setFont(new Font("Noteworthy", Font.BOLD, 18));
        homeBtn.setFocusable(false);
        homeBtn.setBorder(BorderFactory.createLineBorder(new Color(40, 42, 50)));
        homeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 跳轉至Main Frame
            	// 實例化GUI class
                MainFrame mainframe = new MainFrame();
                
                // 顯示目標GUI
                mainframe.setVisible(true);
                
                // 關閉當前GUI
                dispose();
            }
        });

        postBtn = new JButton("Post");
        postBtn.setBounds(330, 420, 80, 25);
        postBtn.setBackground(Color.gray);
        postBtn.setForeground((new Color(0, 0, 205)));
        postBtn.setFont(new Font("Noteworthy", Font.BOLD, 18));
        postBtn.setFocusable(false);
        postBtn.setBorder(BorderFactory.createLineBorder(new Color(40, 42, 50)));
        postBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PostFrame postFrame = new PostFrame();
                dispose();
            }
        });
    }

    public void createLayout() {
    	panel.add(title);
        panel.add(nameLabel);
        panel.add(nameText);
        panel.add(mailLabel);
        panel.add(mailText);
        panel.add(revisLabel);
        panel.add(revisText);
        panel.add(checkAgainLabel);
        panel.add(checkAgainText);
        panel.add(warning);
        panel.add(conformBtn);
        panel.add(homeBtn);
        panel.add(postBtn);
        panel.add(warning);

        add(panel);
    }
    
    public void setProfile() {
        // nameText.setText(user.getName());
        // mailText.setText(user.getMail());
        nameText.setEditable(false);
        mailText.setEditable(false);

    }

}
