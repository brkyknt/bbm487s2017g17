import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import com.mysql.*;
public class DatabaseHandler {

		   static final String DB_URL = "jdbc:mysql://127.0.0.1:3306";

	   //  Database credentials 
	   static final String USER = "root"; // may be different on your mysql server
	   static final String PASS = ""; // may be different on your mysql server
	   private Connection conn = null;
	   
	   private void getConnection(){
		   		
		   
		   try{
			      //STEP 2: Register JDBC driver
			   	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);

			      //System.out.println("Creating statement...");
			   
			      //STEP 6: Clean-up environment
			     
			     
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }
		   
		   
	   }
	   
	   public Librarian checkLogin(String email, String password){
		   
		   Librarian librarian=null;
		   Statement stmt = null;

			
		      if(conn==null){
		    	  getConnection();
		      }else{
		    	  
		    	  // connection already established
		      }
		      
		try {
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT _id,fullname, email, password FROM librarybookloan.Librarians WHERE email='" + email +"'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.first()) {
				librarian = new Librarian(rs.getInt("_id"), rs.getString("fullname"), rs.getString("email"),
						rs.getString("password"));
				System.out.println("on db handler: "+librarian);
			}else{
				librarian=null;
				return librarian;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   if(password.equals(librarian.getPassword())){
			   
			   return librarian;

		   }else{
			   
			   librarian=null;
			   return librarian;

		   }
		   
	   }
	   
	   
	   
}
