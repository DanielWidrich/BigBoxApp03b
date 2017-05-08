package bigbox.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static Connection connection;
	
	private DBUtil(){}
	
	public static synchronized Connection getConnection() 
	{
		try 
		{
			// set the db url, username, and password
			String url = "jdbc:mysql://localhost:3306/biggerbox";
			String username = "root";   // I don't know why we are using root
			String password = "sesame";

			// get and return connection
			connection = DriverManager.getConnection(
					url, username, password);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Error! Connection to database failed.");
		}
		return connection;
    }
    
    public static synchronized void closeConnection()  
    {
        if (connection != null) 
        {
            try 
            {
                connection.close();
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            } 
            finally 
            {
                connection = null;                
            }
        }
    }
    
    public static String fixDBString (String s)
    {
    	// if the string is null, return it
    	if (s == null)
    	{
    		return s;
    	}
    	
    	// add apostrophe before each existing apostrophe
    	StringBuilder sb = new StringBuilder(s);
    	for (int i = 0; i < sb.length(); i++)
    	{
    		char ch = sb.charAt(i);
    		if (ch == 39)
    		{
    			sb.insert(i++, "'");
    		}    		
    	}
    	return sb.toString();    	
    }
}
