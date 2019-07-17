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
import java.util.ArrayList;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * <b>[Enter purpose here]</b>
 * <hr>
 * Date created: Jul 15, 2019
 * <hr>
 * @author Joshua Trimm
 */
public class DatabaseConnection
{
	//private String jsonFileLocation = "C:\\Users\\Joshua\\eclipse-workspace\\Phone email scraper\\src\\connection.json";


    private static Connection con;
    
    
    public static void main(String[] args)
    {
    	getConnection();
    	
    	String urlTest = "leinbachservices.com";
    	String emails = "jim@leinbachservices.com, marketing@leinbachservices.com";
    	
    	try {
    		selectAllFromScrap();
    		
    		
    		ArrayList<String> urls = selectAllUrls();
    		
    		insertUrlAndEmails(urlTest, emails);
    		
    		for(String each : urls)
    		{
    			System.out.println(each);
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    


    }
    	
    	
    public static Connection getConnection() {
    	
        MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(Config.username);
		dataSource.setPassword(Config.password);
		dataSource.setServerName(Config.urlString);
		
		try {
			con = dataSource.getConnection();
		} catch (SQLException ex) {
		    // log an exception. fro example:
		    System.out.println("Failed to create the database connection." + ex); 
		}
        return con;
    }
    
    public static ResultSet using() throws SQLException
    {
    	Connection testing = getConnection();
    	
    	Statement stmt = testing.createStatement();
    	
    	ResultSet rs = stmt.executeQuery("USE `backlinq_web`");
    	
    	return rs;
    }
    
    public static ArrayList<String> insertUrlAndEmails(String url, String email) throws SQLException
    {
    	ArrayList<String> urls = new ArrayList<String>();
    	
    	ResultSet rs = using();
    	

    	try {
        	
    		
    		String query = "INSERT INTO `backlinqs_scrap` (`url`, `email`) VALUES(\"" + url + "\", \"" + email + "\")";
        	
        	PreparedStatement stmt = con.prepareStatement(query);
        	
        	stmt.execute();
                	
        	rs.close();
        	stmt.close();
        	con.close();
    	} catch(Exception i)
    	{
    		i.printStackTrace();
    	}
    	
    	return urls;
    }
    
    public static ArrayList<String> selectAllUrls() throws SQLException
    {
    	ArrayList<String> urls = new ArrayList<String>();
    	
    	ResultSet rs = using();
    	

    	try {
        	
        	Statement stmt = con.createStatement();
        	
        	
        	
        	rs = stmt.executeQuery("SELECT * FROM `backlinqs_scrap`");
        	

        	while(rs.next())
        	{
        		String url = rs.getString("url");
        		urls.add(url);
           	}
 
                	
        	rs.close();
        	stmt.close();
        	con.close();
    	} catch(Exception i)
    	{
    		i.printStackTrace();
    	}
    	
    	return urls;
    }
    
    public static void selectAllFromScrap() throws SQLException
    {
    	
    	
    	ResultSet rs = using();
    	

    	try {
        	
        	Statement stmt = con.createStatement();
        	
        	
        	
        	rs = stmt.executeQuery("SELECT * FROM `backlinqs_scrap`");
        	

        	while(rs.next())
        	{
        		String url = rs.getString("url");
        		String email = rs.getString("email");

                System.out.println(	url + " " + email);
                


        	}
 
            System.out.println(	rs.first());


        	
        	rs.close();
        	stmt.close();
        	con.close();
    	} catch(Exception i)
    	{
    		i.printStackTrace();
    	}
    }
    
    public static void selectAllDEPNOTES()
    {
    	MysqlDataSource dataSource = new MysqlDataSource();
    	dataSource.setUser("");
    	dataSource.setPassword("");
    	dataSource.setServerName("");
    	
    	try {
        	Connection conn = dataSource.getConnection();
        	Statement stmt = conn.createStatement();
        	
        	
        	ResultSet rs = stmt.executeQuery("USE `backlinq_web`");
        	rs = stmt.executeQuery("SELECT * FROM `backlinqs_users`");
        	

        	while(rs.next())
        	{
        		String firstName = rs.getString("FirstName");
                System.out.println(	firstName);
        	}
 
            System.out.println(	rs.first());


        	
        	rs.close();
        	stmt.close();
        	conn.close();
    	} catch(Exception i)
    	{
    		i.printStackTrace();
    	}
    }
    
    
}
