package Configure;

import java.sql.*;
import org.sqlite.JDBC;

/**
 * Created by johnzheng on 2016/9/29.
 */
public class Dao {

    //String url=Common.getValue("url");
    //String driver=Common.getValue("driver");

    //String sqliteUrl=Common.getValue("SQLiteUrl");
    //String sqliteDriver=Common.getValue("SQLiteDriver");

    String sqliteUrl="jdbc:sqlite://c:/Users/johnzheng/testDB.db";
    String sqliteDriver="org.sqlite.JDBC";

    Connection conn=null;

    /**
     * 查询操作
     * @param sql
     * @return
     */
    public ResultSet SelectResult(String sql){
        ResultSet rs=null;
        try {
            //mysql连接
            /*Class.forName(driver);
            conn= DriverManager.getConnection(url);*/

            //SQLite连接
            Class.forName(sqliteDriver);
            conn= DriverManager.getConnection(sqliteUrl);

            Statement stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            //conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 更新操作
     * @param sql
     * @return
     */
    public int UpdateResult(String sql){
        int result=0;
        try {
            //mysql连接
            /*Class.forName(driver);
            conn=DriverManager.getConnection(url);*/

            //SQLite连接
            Class.forName(sqliteDriver);
            conn=DriverManager.getConnection(sqliteUrl);

            Statement stmt=conn.createStatement();
            result=stmt.executeUpdate(sql);
            //conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
