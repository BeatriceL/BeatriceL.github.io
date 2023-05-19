
import java.awt.*;
import javax.swing.*;

public class About {

	GridBagConstraints c = new GridBagConstraints();

	JFrame frame = new JFrame();
	JLabel picture = new JLabel(new ImageIcon("bin/NCCU.png"));
	JLabel title = new JLabel("政大人力銀行");
	JLabel version = new JLabel("版本 2022.06");
	JLabel copyright = new JLabel("Copyright © 2022 NCCU MIS");
	JLabel rights = new JLabel("保留一切權利。");

	public About() {
		frame.setMaximumSize(new Dimension(300, 220));
		frame.setMinimumSize(new Dimension(300, 220));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new GridBagLayout());

		title.setFont(new Font("sans-serif", Font.BOLD, 15));
		version.setFont(new Font("sans-serif", Font.PLAIN, 10));
		copyright.setFont(new Font("sans-serif", Font.PLAIN, 10));
		rights.setFont(new Font("sans-serif", Font.PLAIN, 10));

		c.gridwidth = c.gridheight = 1;
		c.gridx = c.gridy = 0;
		c.insets.bottom = 15;
		frame.add(picture, c);

		c.gridy = 1;
		c.insets.bottom = 5;
		frame.add(title, c);

		c.gridy = 2;
		frame.add(version, c);

		c.gridy = 3;
		c.insets.bottom = 0;
		frame.add(copyright, c);

		c.gridy = 4;
		frame.add(rights, c);

		frame.setVisible(true);
	}
}