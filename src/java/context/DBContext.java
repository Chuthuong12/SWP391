package context;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static java.lang.Character.UnicodeBlock.forName;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {

//    Connection con = null;

     public static Connection makeConnection() throws Exception {
        Connection cn = null;
        String IP = "localhost";
        String instanceName = "KDICHIGO";
        String port = "1433";
        String uid = "sa";
        String pwd = "123456";
        String db = "SWP391_Project_Test";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://" + IP + "\\" + instanceName + ":" + port
                + ";databasename=" + db + ";user=" + uid + ";password=" + pwd;
        Class.forName(driver);
        cn = DriverManager.getConnection(url);
        return cn;
    }

    public static void main(String[] args) throws Exception {
        Connection con = DBContext.makeConnection();
        if (con != null) {
            System.out.println("ok");
        } else {
            System.out.println("fail");
        }
    }

}
