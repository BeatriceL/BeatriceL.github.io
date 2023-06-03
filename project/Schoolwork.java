import java.sql.*;

public class Schoolwork {
    public static void main(String[] args) {
        String server = "jdbc:mysql://140.119.19.73:3315/";
        String database = "108305025";
    	String url = server + database + "?useSSL=false";
    	String username = "108305025";
    	String password = "0fzyb";

        // 建立database連接
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM table_name";

            // search
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    // 获取每一行数据的列值
                    int id = resultSet.getInt("id"); // 替换为你的列名
                    String name = resultSet.getString("name"); // 替换为你的列名
                    // 其他列...

                    // 打印数据
                    System.out.println("ID: " + id + ", Name: " + name);
                    // 打印其他列...
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
