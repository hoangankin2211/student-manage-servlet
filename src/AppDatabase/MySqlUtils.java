package AppDatabase;
import java.sql.*;


public class MySqlUtils {

    private static final MySqlUtils instance = new MySqlUtils();

    public static MySqlUtils getInstance(){
        return instance;
    }

     public static Connection con;
     public static final String hostName = "localhost:3306";
     public static final String databaseName = "student_manage";
     public static final String username = "root";
     public static final String password = "123456789";
    private MySqlUtils()  {
        String urlDatabase = "jdbc:mysql://" + hostName + "/" + databaseName;
        try {
            con =  DriverManager.getConnection(urlDatabase,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet query(String statement) throws SQLException {
        PreparedStatement stm = con.prepareStatement(statement);
        stm.execute();
        return stm.getResultSet();
    }

}

