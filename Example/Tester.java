
import java.sql.*;
import java.awt.event.*;

public class Tester {

	static int ID;
	static String name;
	static Statement statement;
	static ResultSet rs;

	public static void main(String[] args) {
		MainLogin login = new MainLogin();

		class CreateAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				login.frame.setVisible(false);
				SelectAccountType selectFrame = new SelectAccountType();
				class companySelected implements ActionListener {
					public void actionPerformed(ActionEvent e) {
						selectFrame.frame.setVisible(false);
						CreateCompany CCframe = new CreateCompany();
					}
				}
				selectFrame.company.addActionListener(new companySelected());
				class employeeSelected implements ActionListener {
					public void actionPerformed(ActionEvent e) {
						selectFrame.frame.setVisible(false);
						CreateEmployee CEframe = new CreateEmployee();
					}
				}
				selectFrame.employee.addActionListener(new employeeSelected());
			}
		}
		login.newAccount.addActionListener(new CreateAction());

		class LoginAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (login.userLogin(login.inputAccount.getText(), login.getPassword())) {
					login.frame.setVisible(false);
					EmployeeWorkList EWLframe = new EmployeeWorkList();
				} else if (login.comapnyLogin(login.inputAccount.getText(), login.getPassword())) {
					login.frame.setVisible(false);
					CompanyWorkList CWLframe = new CompanyWorkList();
				} else {
					login.loginFailed();
				}
			}
		}
		login.login.addActionListener(new LoginAction());
	}

	public static void connectSQL() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://140.119.19.73:3315/mongroup3", "mongroup3",
					"tyr4822");
			statement = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}