import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBarDemo extends JFrame {
    private JPanel pagePanel;

    public MenuBarDemo() {
        setTitle("MenuBar Demo");
        setSize(1500, 800);

        // 創建MenuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // 創建按鈕1
        JButton button1 = new JButton("Schoolwork");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 切換到頁面1
                switchToPage(1);
            }
        });
        menuBar.add(button1);

        // 創建按鈕2
        JButton button2 = new JButton("Internship");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 切換到頁面2
                switchToPage(2);
            }
        });
        menuBar.add(button2);

        // 創建按鈕3
        JButton button3 = new JButton("Study Aboard");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 切換到頁面3
                switchToPage(3);
            }
        });
        menuBar.add(button3);

        // 創建按鈕4
        JButton button4 = new JButton("Gossip");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 切換到頁面4
                switchToPage(4);
            }
        });
        menuBar.add(button4);
        
        // 右側按鈕
        JToolBar toolBar = new JToolBar();
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(toolBar);

        // 在JToolBar中新增按鈕
        JButton button5 = new JButton("Post");
        toolBar.add(button5);
        // 設置按鈕的點擊事件
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 處理按鈕點擊事件
            	switchToPage(5);
            	
            	// 實例化GUI class
                PostFrame postFrame = new PostFrame();
                
                // 顯示目標GUI
                postFrame.setVisible(true);
                
                // 關閉當前GUI
                dispose();
                
                System.out.println("Post");
            }
        });
        
        JButton button6 = new JButton("Profile");
        toolBar.add(button6);
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 處理按鈕點擊事件
            	switchToPage(6);
            	
            	// 實例化GUI class
                ProfileFrame profileFrame = new ProfileFrame(null);
                
                // 顯示目標GUI
                profileFrame.setVisible(true);
                
                // 關閉當前GUI
                dispose();
                System.out.println("Personal Profile: ");
            }
        });
        
        JButton button7 = new JButton("LOG OUT");
        toolBar.add(button7);
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 處理按鈕點擊事件
            	switchToPage(7);
            	
            	// 實例化GUI class
                LoginFrame loginFrame = new LoginFrame();
                
                // 顯示目標GUI
                loginFrame.setVisible(true);
                
                // 關閉當前GUI
                dispose();
                System.out.println("Logged Out...");
            }
        });
       
        // 創建頁面容器
        pagePanel = new JPanel();
        pagePanel.setLayout(new CardLayout());
        add(pagePanel);

        // 添加頁面
        JLabel label1 = new JLabel("Schoolwork Board");
        pagePanel.add(label1, "1");

        // 添加頁面2
        JLabel label2 = new JLabel("Internship Board");
        pagePanel.add(label2, "2");

        // 添加頁面3
        JLabel label3 = new JLabel("Study Aboard Board");
        pagePanel.add(label3, "3");

        // 添加頁面4
        JLabel label4 = new JLabel("Gossip Board");
        pagePanel.add(label4, "4");
        
        // 添加頁面5
        JLabel label5 = new JLabel("Post");
        pagePanel.add(label5, "5");
          
        // 添加頁面6
        JLabel label6 = new JLabel("Personal Profile: ");
        pagePanel.add(label6, "6");
        
        // 添加頁面7
        JLabel label7 = new JLabel("Logged Out...");
        pagePanel.add(label7, "7");
    }

    private void switchToPage(int page) {
        CardLayout cardLayout = (CardLayout) pagePanel.getLayout();
        cardLayout.show(pagePanel, Integer.toString(page));
    }

    public static void main(String[] args) {
        MenuBarDemo demo = new MenuBarDemo();
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        demo.setVisible(true);
    }
}
