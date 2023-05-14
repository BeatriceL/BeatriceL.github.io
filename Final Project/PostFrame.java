package testf;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;

public class PostFrame extends JFrame {

	private JPanel contentPane;
	private JLabel titlel, textl;
	private JTextField titlet;
	private JTextArea textt;
	private JButton post; 
	private String board, poster;
	
	String server = "jdbc:mysql://140.119.19.73:3315/";
	String database = "108305025";
	String url = server + database + "?useSSL=false";
	String username = "108305025";
	String password = "0fzyb";

	public PostFrame() {
		setTitle("Post");
		setBounds(100, 100, 450, 300);

		board = "Schoolwork Board";
		poster = "小明";
		
		createLabel();
		createTextField();
		createButton();
		createLayout();
	}
	
	private void createLabel() {
		titlel = new JLabel("Title:");
		titlel.setFont(new Font("新細明體", Font.BOLD, 20));
		titlel.setBounds(108, 40, 48, 30);
		
		textl = new JLabel("Text:");
		textl.setFont(new Font("新細明體", Font.BOLD, 20));
		textl.setBounds(108, 91, 48, 30);
	}
	
	private void createTextField() {
		titlet = new JTextField(10);
		titlet.setBounds(166, 47, 96, 21);
		
		textt = new JTextArea();
		textt.setBounds(166, 94, 96, 106);
	}
	
	private void createButton() {
		post = new JButton("post");
		post.setBackground(new Color(255, 255, 255));
		post.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(url, username, password)) {
					Statement stat = conn.createStatement();
					String query;
					boolean success;
					String titles = titlet.getText();
					String texts = textt.getText();
					query = String.format("INSERT INTO `%s` (Poster, Title, Text) VALUES ('%s', '%s', '%s')", board, poster, titles,texts);
					success = stat.execute(query);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		post.setBounds(176, 210, 72, 23);
	}
	
	private void createLayout() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(titlel);
		contentPane.add(textl);
		contentPane.add(titlet);
		contentPane.add(textt);
		contentPane.add(post);
	}
}