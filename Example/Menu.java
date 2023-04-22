
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;;

public class Menu {

	Font menuBarFont = new Font("Helvetica", Font.PLAIN, 14);
	ArrayList<Integer> idList;
	JList<String> elementList;
	DefaultListModel<String> l;

	JButton button = new JButton();
	JButton searchButton = new JButton("搜尋");
	JTextField search = new JTextField();
	JTextPane panel = new JTextPane();
	JScrollPane scrollPanel = new JScrollPane(panel);
	JScrollPane listPanel;

	JFrame frame = new JFrame("政大人力銀行");
	JMenuBar menuBar = new JMenuBar();
	GridBagConstraints c = new GridBagConstraints();

	public Menu() {
		idList = new ArrayList<>();
		l = new DefaultListModel<>();
		elementList = new JList<>(l);
		elementList.setValueIsAdjusting(false);
		listPanel = new JScrollPane(elementList);

		menuBar.setBorder(new EmptyBorder(0, 0, 0, 0));
		listPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		scrollPanel.setBorder(new EmptyBorder(5, 20, 5, 5));
		elementList.setCellRenderer(getRederer());
		elementList.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(215, 215, 215)));
		scrollPanel.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(215, 215, 215)));
		search.setFont(new Font("sans-serif", Font.PLAIN, 16));

		elementList.setSelectionBackground(new Color(244, 242, 239));
		elementList.setSelectionForeground(new Color(108, 111, 118));
		elementList.setBackground(new Color(250, 250, 250));
		listPanel.setBackground(new Color(250, 250, 250));
		panel.setBackground(new Color(250, 250, 250));
		scrollPanel.setBackground(new Color(250, 250, 250));

		panel.setEditable(false);
		panel.setContentType("text/html");
		frame.setMaximumSize(new Dimension(1200, 800));
		frame.setMinimumSize(new Dimension(1200, 800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		frame.getContentPane().setBackground(new Color(250, 250, 250));

		c.ipadx = 100;
		c.ipady = 13;
		c.gridx = c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.WEST;
		frame.add(menuBar, c);

		c.gridy = c.gridwidth = c.gridheight = 1;
		c.gridx = 1;
		c.ipadx = 25;
		c.anchor = GridBagConstraints.CENTER;
		frame.add(searchButton, c);

		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 325;
		c.fill = GridBagConstraints.HORIZONTAL;
		frame.add(search, c);

		c.gridx = 0;
		c.gridy = c.gridwidth = 2;
		c.ipady = 650;
		c.fill = GridBagConstraints.BOTH;
		frame.add(listPanel, c);

		c.gridx = 2;
		c.gridy = c.gridwidth = 1;
		c.gridheight = 2;
		c.ipadx = 740;
		frame.add(scrollPanel, c);

		c.gridx = 2;
		c.gridy = 0;
		c.ipadx = 40;
		c.ipady = 8;
		c.insets.right = 15;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHEAST;
		frame.add(button, c);
		button.setVisible(false);
	}

	private ListCellRenderer<? super String> getRederer() {
		return new DefaultListCellRenderer() {
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index,
						isSelected, cellHasFocus);
				listCellRendererComponent
						.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(215, 215, 215)));
				return listCellRendererComponent;
			}
		};
	}
}