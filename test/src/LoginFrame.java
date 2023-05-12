import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;

public class LoginFrame extends JFrame {
 
 private static final int FRAME_WIDTH = 400; 
 private static final int FRAME_HEIGHT = 300;
 private static final int FIELD_WIDTH = 200;
 

 private JTextField tfUserName, tfPassword;
 private JButton btnEnroll, btnLogin;
 private JPanel panel = (JPanel) this.getContentPane();

 
 public LoginFrame() {

  this.setTitle("Login");
  this.setSize(this.FRAME_WIDTH, this.FRAME_HEIGHT);
  createTextField();
  createButton();
  createLayout();
 }
 
 public void createTextField() {
  tfUserName = new JTextField(10);
  tfPassword = new JTextField(10);
 }

 private void createLayout() {
  JPanel uPanel = new JPanel();
  uPanel.add(new JLabel("Username:"));
  uPanel.add(tfUserName);
  uPanel.setPreferredSize(new Dimension(500, 40));

  JPanel pPanel = new JPanel();
  pPanel.add(new JLabel("Password:"));
  pPanel.add(tfPassword);
  pPanel.setPreferredSize(new Dimension(500, 40));

  JPanel bPanel = new JPanel();
  bPanel.add(btnEnroll);
  bPanel.add(btnLogin);
  bPanel.setPreferredSize(new Dimension(500, 40));

  JPanel allPannel = new JPanel();
  allPannel.add(uPanel);
  allPannel.add(pPanel);
  allPannel.add(bPanel);

  setLayout(new BorderLayout(20, 40));
  getContentPane().add(new JPanel(), BorderLayout.NORTH);
  getContentPane().add(allPannel, BorderLayout.CENTER);
  getContentPane().add(new JPanel(), BorderLayout.SOUTH);
 }

//your code
 private void createButton() {
  
  btnEnroll = new JButton("Enroll");
  btnLogin = new JButton("Login");
 
  btnEnroll.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
     
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
}
//  btnLogin.addActionListener(new ActionListener() {
//   @Override
//   public void actionPerformed(ActionEvent e) {
//    // TODO Auto-generated method stub
//    try {
//     
//
//
//   
//   }catch{
//    
//   }
//  });
 
 
}