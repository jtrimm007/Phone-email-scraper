/**
 * -------------------------------------------------
 * File name: DatabaseConnection.java
 * Project name: Phone email scraper
 * -------------------------------------------------
 * Creator's name: Joshua Trimm
 * Email: joshua@trimwebdesign.com
 * Website: https://joshuatrimm.com
 * Creation date: Jul 15, 2019
 * -------------------------------------------------
 */

import java.sql.*;

/**
 * <b>[Enter purpose here]</b>
 * <hr>
 * Date created: Jul 15, 2019
 * <hr>
 * @author Joshua Trimm
 */
public class DatabaseConnection
{
	private String jsonFileLocation = "C:\\Users\\Joshua\\eclipse-workspace\\Phone email scraper\\src\\connection.json";

    private static String url = "jdbc:mysql://132.148.86.218:2083/backlinq_web";    
    private static String driverName = "com.mysql.jdbc.Driver";   
    private static String username = "backlinq_jtrimm";   
    private static String password = "B+A>rhdxEpGwI";
    private static Connection con;
    private static String urlstring;
    
    public static void main(String[] args)
    {
    	getConnection();
    }

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(urlstring, username, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
        return con;
    }
}
