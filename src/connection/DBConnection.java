package connection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection connection;

    public Connection getConnection() {
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream("src\\Common\\db.properties");
            prop.load(is);
            String databaseUrl = prop.getProperty("db.url");
            String userName = prop.getProperty("db.username");
            String password = prop.getProperty("db.password");
            String driverName = prop.getProperty("db.driver");
            if (connection == null) {
                Class.forName(driverName);
                connection = DriverManager.getConnection(databaseUrl, userName, password);
            } else if (connection.isClosed()) {
                connection = DriverManager.getConnection(databaseUrl, userName, password);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Properties file not found");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (SQLException e) {
            System.out.println("SQL exception is occured in connection");
        } catch (IOException e) {
            System.out.println("IO Exception is occured");
        }
        return connection;
    }

    public void closeConnection(){
        if(connection != null){
            try {
                if(!connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
