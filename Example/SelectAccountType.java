
import javax.swing.*;
import java.awt.*;

public class SelectAccountType {

	GridBagConstraints c = new GridBagConstraints();

	JFrame frame = new JFrame("選擇帳號類型");
	JButton company = new JButton("公司行號");
	JButton employee = new JButton("個人帳號");
	JTextPane panel = new JTextPane();

	public SelectAccountType() {
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());

		c.gridx = c.gridy = 0;
		c.gridwidth = c.gridheight = 1;
		c.ipadx = c.ipady = 70;
		frame.add(company, c);

		c.gridx = 2;
		frame.add(employee, c);

		frame.setVisible(true);
	}
}