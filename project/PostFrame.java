import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class PostFrame extends JFrame {

	private JPanel panel;
    private JLabel titleLabel, contentLabel, boardLabel;
    private JTextField titleText;
    private JTextArea contentArea;
    private JButton postBtn, homeBtn, clearBtn;
    private JComboBox boardCombo;
	private String board, poster;
	private JDBC jdbc;
	private User user;

	public PostFrame() {
		user = new User();
		poster = user.getName();
		jdbc = new JDBC();
		
		createFrame();
		createPanel();
		createLabel();
		createTextField();
		createCombo();
		createButton();
		createLayout();
	}
	
	public void createFrame() {
		setTitle("Post");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1500, 800);
		setBackground(new Color(135, 206, 250));
		setLocationRelativeTo(null);
	}
	
	public void createPanel() {
		panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setLayout(null);
	}
	
	public void createLabel() {
		titleLabel = new JLabel("Title");
        titleLabel.setBounds(400, 100, 115, 40);
        titleLabel.setFont(new Font("Noteworthy", Font.BOLD, 22));
		
        contentLabel = new JLabel("Content");
        contentLabel.setBounds(400, 150, 115, 40);
        contentLabel.setFont(new Font("Noteworthy", Font.BOLD, 22));
        
        boardLabel = new JLabel("Board:");
        boardLabel.setBounds(500, 525, 115, 40);
        boardLabel.setFont(new Font("Noteworthy", Font.BOLD, 15));
	}
	
	public void createTextField() {
		titleText = new JTextField(50);
        titleText.setBounds(500, 100, 350, 40);
        titleText.setForeground(new Color(112, 128, 144));
        titleText.setFont(new Font("STXingKai", Font.BOLD, 20));
        titleText.setBackground(new Color(254, 255, 255));
        titleText.setCaretColor(new Color(112, 128, 144));
        titleText.setBorder(BorderFactory.createLineBorder(new Color(40, 42, 50)));
		
        contentArea = new JTextArea();
        contentArea.setBounds(500, 150, 350, 370);
        contentArea.setForeground(new Color(112, 128, 144));
        contentArea.setFont(new Font("STXingKai", Font.BOLD, 20));
        contentArea.setBackground(new Color(254, 255, 255));
        contentArea.setCaretColor(new Color(112, 128, 144));
        contentArea.setBorder(BorderFactory.createLineBorder(new Color(40, 42, 50)));
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
	}
	
	public void createCombo() {
		boardCombo = new JComboBox();
		boardCombo.setBounds(555, 530, 115, 30);
		boardCombo.setFont(new Font("Noteworthy", Font.BOLD, 15));
		boardCombo.setBackground(Color.white);
		
		boardCombo.addItem("Schoolwork");
		boardCombo.addItem("Internship");
		boardCombo.addItem("Study Aboard");
		boardCombo.addItem("Gossip");
		
		boardCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedOption = (String) boardCombo.getSelectedItem();
				
				//根據選擇發文並跳轉至不同頁面
				if (selectedOption.equals("Schoolwork")) {
	                System.out.println("已發文至Schoolwork Board");
	            
	            } else if (selectedOption.equals("Internship")) {
	                System.out.println("已發文至Internship Board");
	                
	            } else if (selectedOption.equals("Study Abroad")) { 
	                System.out.println("已發文至Study Aboard Board");
	                
	            } else if (selectedOption.equals("Gossip")) {
	                System.out.println("已發文至Gossip Board");
	            }
	        }
	    });
	}
	
	public void createButton() {
		postBtn = new JButton("post");
		postBtn.setBounds(680, 530, 50, 30);
        postBtn.setFont(new Font("Noteworthy", Font.BOLD, 15));
        postBtn.setBackground(Color.white);
        postBtn.setForeground(new Color(0, 0, 205));
        postBtn.setBorder(BorderFactory.createLineBorder(new Color(205, 205, 206)));
        postBtn.setFocusable(false);
        postBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = jdbc.getConnection()) {
					Statement stat = conn.createStatement();
					board = (String)boardCombo.getSelectedItem();
					String query;
					boolean success;
					if (titleText.getText().isEmpty() || contentArea.getText().isEmpty() ) {
						JOptionPane.showMessageDialog(null, "標題或內文不能為空", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						query = String.format("INSERT INTO `%s` (Poster, Title, Text) VALUES ('%s', '%s', '%s')", board, poster, titleText.getText(), contentArea.getText());
						success = stat.execute(query);
						if(success) {
							JOptionPane.showMessageDialog(null, "post complete", "", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
        
        homeBtn = new JButton("Home");
        homeBtn.setBounds(740, 530, 50, 30);
        homeBtn.setFont(new Font("Noteworthy", Font.BOLD, 15));
        homeBtn.setBackground(Color.white);
        homeBtn.setForeground(new Color(0, 0, 205));
        homeBtn.setBorder(BorderFactory.createLineBorder(new Color(205, 205, 206)));
        homeBtn.setFocusable(false);
        homeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Back to HomeFrame
      
            	// 實例化GUI class
                MenuBarDemo menubardemo = new MenuBarDemo();
                
                // 顯示目標GUI
                menubardemo.setVisible(true);
                
                // 關閉當前GUI
                dispose();

            }
        });
        
        clearBtn = new JButton("Clear");
        clearBtn.setBounds(800, 530, 50, 30);
        clearBtn.setFont(new Font("Noteworthy", Font.BOLD, 15));
        clearBtn.setBackground(Color.white);
        clearBtn.setForeground(new Color(0, 0, 205));
        clearBtn.setBorder(BorderFactory.createLineBorder(new Color(205, 205, 206)));
        clearBtn.setFocusable(false);
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                titleText.setText(null);
                contentArea.setText(null);
            }
        });
	}
	
	public void createLayout() {
		panel.add(titleLabel);
        panel.add(titleText);
        panel.add(contentLabel);
        panel.add(contentArea);
        panel.add(boardLabel);
        panel.add(boardCombo);
        panel.add(postBtn);
        panel.add(homeBtn);
        panel.add(clearBtn);

        add(panel);
	}
	
}
