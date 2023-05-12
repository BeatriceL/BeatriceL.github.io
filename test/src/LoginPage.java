import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPage {
 
 private JFrame frame;
 private JPanel contentPane;
 private JTextField txtAccount;
 private JPasswordField passwordField;
 private JLabel loginLabel,accountLabel,passwordLabel;
 private JButton loginBtn,createAccountBtn;


 /**
  * Create the frame.
  */
 
 public LoginPage() {
  
  frame = new JFrame();
  frame.setVisible(true);
  frame.setTitle("Login");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.getContentPane().setLayout(null);
  frame.setResizable(false);
  frame.setSize(687, 458);
  frame.setLocationRelativeTo(null);
  
  
  contentPane = new JPanel();
  contentPane.setBackground(new Color(83, 132, 235));
  contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
  contentPane.setLayout(null);

  loginLabel = new JLabel("NCCU Communication Platform");
  loginLabel.setBounds(157, 63, 409, 39);
  loginLabel.setBackground(new Color(187, 85, 224));
  loginLabel.setForeground(new Color(254, 255, 255));
  loginLabel.setFont(new Font("Noteworthy", Font.BOLD, 24));
  loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
  contentPane.add(loginLabel);

  accountLabel = new JLabel("Account");
  accountLabel.setBounds(157, 114, 64, 32);
  accountLabel.setHorizontalAlignment(SwingConstants.CENTER);
  accountLabel.setFont(new Font("Noteworthy", Font.BOLD, 15));
  contentPane.add(accountLabel);

  txtAccount = new JTextField();
  txtAccount.setBounds(253, 116, 217, 32);
  txtAccount.setFont(new Font("Noteworthy", Font.BOLD, 13));
  contentPane.add(txtAccount);
  txtAccount.setColumns(10);

  passwordLabel = new JLabel("Password");
  passwordLabel.setBounds(157, 188, 64, 33);
  passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
  passwordLabel.setFont(new Font("Noteworthy", Font.BOLD, 15));
  contentPane.add(passwordLabel);

  passwordField = new JPasswordField();
  passwordField.setBounds(253, 190, 217, 32);
  passwordField.setToolTipText("");
  passwordField.setFont(new Font("Noteworthy", Font.BOLD, 13));
  passwordField.setEchoChar('*');
  contentPane.add(passwordField);

  loginBtn = new JButton("Login");
  loginBtn.setBounds(282, 244, 166, 44);
  loginBtn.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
     //資料庫加新東西
     try (Connection conn = DriverManager.getConnection(url, username, password)) {
        int inputName = Integer.parseInt(tfUserName.getText());
        int inputPassword = Integer.parseInt(tfPassword.getText());
  
  
        Statement stat = conn.createStatement();
        String query;
        boolean success;
  
        query = "INSERT INTO ProjectTest (ID, password) VALUES (123, 456)";
        success = stat.execute(query);
        if (success) {
         ResultSet result = stat.getResultSet();
         showResultSet(result);
         result.close();
        }
  
       } catch (SQLException e2) {
        e2.printStackTrace();
       }
     }
     
     public static void showResultSet(ResultSet result) throws SQLException {
      ResultSetMetaData metaData = result.getMetaData();
      int columnCount = metaData.getColumnCount();
      for (int i = 1; i <= columnCount; i++) {
       System.out.printf("%15s", metaData.getColumnLabel(i));
      }
      System.out.println();
      while (result.next()) {
       for (int i = 1; i <= columnCount; i++) {
        System.out.printf("%15s", result.getString(i));
       }
       System.out.println();
      }
     }
    });
  
  loginBtn.setFont(new Font("Noteworthy", Font.BOLD, 15));
  loginBtn.setForeground(Color.GRAY);
  contentPane.add(loginBtn);

  createAccountBtn = new JButton("Create Account");
  createAccountBtn.setBounds(282, 300, 166, 44);
  createAccountBtn.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    
   }
  });
  createAccountBtn.setBackground(Color.PINK);
  createAccountBtn.setForeground(Color.GRAY);
  createAccountBtn.setFont(new Font("Noteworthy", Font.BOLD, 15));
  contentPane.add(createAccountBtn);
 }
 


}
