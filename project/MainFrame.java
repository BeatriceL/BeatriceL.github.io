import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JPanel pagePanel;
    private JButton text1, text2, text3, text4, text5, text6, text7;
    private JLabel rightLabel;


    public MainFrame() {
        setTitle("Homepage");
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

        // 添加頁面1
        text1 = new JButton("TEXT1");
        text1.setPreferredSize(new Dimension(435, 60));
        text1.setBorderPainted(false);
        text1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 rightLabel.setText("1");
            }
        });
        text2 = new JButton("TEXT2");
        text2.setPreferredSize(new Dimension(435, 60));
        text2.setBorderPainted(false);
        text2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	rightLabel.setText("2");
            }
        });
        text3 = new JButton("TEXT3");
        text3.setPreferredSize(new Dimension(435, 60));
        text3.setBorderPainted(false);
        text3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	rightLabel.setText("3");
            }
        });
        text4 = new JButton("TEXT4");
        text4.setPreferredSize(new Dimension(435, 60));
        text4.setBorderPainted(false);
        text4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	rightLabel.setText("4");
            }
        });
        text5 = new JButton("TEXT5");
        text5.setPreferredSize(new Dimension(435, 60));
        text5.setBorderPainted(false);
        text5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	rightLabel.setText("5");
            }
        });
        text6 = new JButton("TEXT6");
        text6.setPreferredSize(new Dimension(435, 60));
        text6.setBorderPainted(false);
        text6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	rightLabel.setText("6");
            }
        });
        text7 = new JButton("TEXT7");
        text7.setPreferredSize(new Dimension(435, 60));
        text7.setBorderPainted(false);
        text7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	rightLabel.setText("7");
            }
        });

        JPanel page1 = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new GridLayout(7, 1));
        JPanel rightPanel = new JPanel(); // 空白右側區域

        leftPanel.add(text1);
        leftPanel.add(text2);
        leftPanel.add(text3);
        leftPanel.add(text4);
        leftPanel.add(text5);
        leftPanel.add(text6);
        leftPanel.add(text7);

        rightPanel.setBackground(Color.WHITE); // 右側留白

        page1.add(leftPanel, BorderLayout.WEST);
        page1.add(rightPanel, BorderLayout.CENTER);
        
        pagePanel.add(page1, "1");
        
        rightLabel = new JLabel();
        rightPanel.add(rightLabel);

        // 添加頁面2


        // 添加頁面3


        // 添加頁面4


        // 添加頁面5


        // 添加頁面6


        // 添加頁面7

    }

    private void switchToPage(int page) {
        CardLayout cardLayout = (CardLayout) pagePanel.getLayout();
        cardLayout.show(pagePanel, Integer.toString(page));

        if (page == 1 || page == 2 || page == 3 || page == 4) {
            text1.setVisible(true);
            text2.setVisible(true);
            text3.setVisible(true);
            text4.setVisible(true);
            text5.setVisible(true);
            text6.setVisible(true);
            text7.setVisible(true);
        } else if (page == 5) {
            text1.setVisible(false);
            text2.setVisible(false);
            text3.setVisible(false);
            text4.setVisible(false);
            text5.setVisible(false);
            text6.setVisible(false);
            text7.setVisible(false);
        } else if (page == 6) {
            text1.setVisible(false);
            text2.setVisible(false);
            text3.setVisible(false);
            text4.setVisible(false);
            text5.setVisible(false);
            text6.setVisible(false);
            text7.setVisible(false);
        } else if (page == 7) {
            text1.setVisible(false);
            text2.setVisible(false);
            text3.setVisible(false);
            text4.setVisible(false);
            text5.setVisible(false);
            text6.setVisible(false);
            text7.setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame mainframe = new MainFrame();
                mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainframe.setVisible(true);
            }
        });
    }
}
