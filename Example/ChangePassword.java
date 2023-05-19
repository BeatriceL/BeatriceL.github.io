
import javax.swing.*;
import java.awt.*;

public class ChangePassword {

	GridBagConstraints c = new GridBagConstraints();

	JFrame frame = new JFrame("更改密碼");
	JLabel oldLabel = new JLabel("舊密碼：");
	JLabel newLabel = new JLabel("新密碼：");
	JLabel againLabel = new JLabel("再次輸入新密碼：");
	JButton button = new JButton("確認更改");

	JPasswordField inputOld = new JPasswordField();
	JPasswordField inputNew = new JPasswordField();
	JPasswordField inputAgain = new JPasswordField();

	public ChangePassword() {
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());

		inputOld.setFont(new Font("sans-serif", Font.PLAIN, 16));
		inputNew.setFont(new Font("sans-serif", Font.PLAIN, 16));
		inputAgain.setFont(new Font("sans-serif", Font.PLAIN, 16));

		c.gridx = c.gridy = 0;
		c.gridwidth = c.gridheight = 1;
		c.ipady = 15;
		c.anchor = GridBagConstraints.EAST;
		frame.add(oldLabel, c);

		c.gridy = 1;
		frame.add(newLabel, c);

		c.gridy = 2;
		frame.add(againLabel, c);

		c.gridx = 1;
		c.gridy = 3;
		c.ipady = 10;
		c.anchor = GridBagConstraints.CENTER;
		frame.add(button, c);

		c.gridy = 0;
		c.ipadx = 200;
		c.fill = GridBagConstraints.VERTICAL;
		frame.add(inputOld, c);

		c.gridy = 1;
		frame.add(inputNew, c);

		c.gridy = 2;
		frame.add(inputAgain, c);

		frame.setVisible(true);
	}

}