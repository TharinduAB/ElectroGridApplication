package model;

import java.sql.*;

public class User {
	
	private Connection connect() 
	{ 
			Connection con = null; 
			
			try
	{ 
				Class.forName("com.mysql.jdbc.Driver"); 
 
				//Provide the correct details: DBServer/DBName, username, password 
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electro_grid", "root", "root"); 
	} 
			catch (Exception e) 
			{e.printStackTrace();} 
 
			return con; 
 }
	


public String insertUserData(String user_name, String password, String address, Integer contac_no,String email) 
{ 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into users (`userID`,`user_name`,`password`,`address`,`contac_no`,`email`)"
	 + " values (?, ?, ?, ?, ? , ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, user_name); 
	 preparedStmt.setString(3, password); 
	 preparedStmt.setString(4, address);
	 preparedStmt.setInt(5, contac_no);
	 preparedStmt.setString(6, email);
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the carddata."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
}


public String readUserData()
{ 
	String output = ""; 
	try
	{ 
		Connection con = connect(); 
		if (con == null) 
	{ 
			return "Error while connecting to the database for reading."; 
	} 
	// Prepare the html table to be displayed
	output = "<table border='1'><tr><th>User ID</th>" 
			+"<th>User Name</th><th>Password</th>"
			+ "<th>Address</th>" 
			+ "<th>Contact No</th><th>Email</th></tr>";
	
	String query = "select * from users"; 
 	Statement stmt = con.createStatement(); 
 	ResultSet rs = stmt.executeQuery(query);
 	
 	// iterate through the rows in the result set
 	while (rs.next()) 
 	{ 
 		String userID = Integer.toString(rs.getInt("userID")); 
 		String user_name = rs.getString("user_name"); 
 		String password = rs.getString("password"); 
 		String address = rs.getString("address"); 
 		String contac_no =Integer.toString(rs.getInt("contac_no"));
 		String email = rs.getString("email");
 		
 		// Add a row into the html table
 		output += "<tr><td>" + userID + "</td>"; 
 		output += "<td>" + user_name + "</td>"; 
 		output += "<td>" + password + "</td>";
 		output += "<td>" + address + "</td>";
 		output += "<td>" + contac_no + "</td>";
 		output += "<td>" + email + "</td>";
 		
 	// buttons
 		 output += "<td><input name='btnUpdate' " 
 		 + " type='button' value='Update'></td>"
 		 + "<td><form method='post' action='userform.jsp'>"
 		 + "<input name='btnRemove' " 
 		 + " type='submit' value='Remove'>"
 		 + "<input name='userID' type='hidden' " 
 		 + " value='" + userID + "'>" + "</form></td></tr>"; 
 	} 

 	con.close();
 	
 // Complete the html table
 	 output += "</table>"; 
 	 } 
 	catch (Exception e) 
 	 { 
 	 output = "Error while reading the items."; 
 	 System.err.println(e.getMessage()); 
 	 } 
 	return output; 
}

public String updateUser(String user_name, String password, String address, Integer contac_no,String email, Integer userID) {
	
	String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 String query = "UPDATE users SET user_name=?,password=?,address=?,contac_no=?,email=? WHERE userID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 
	 
	 preparedStmt.setString(1, user_name); 
	 preparedStmt.setString(2, password); 
	 preparedStmt.setString(3, address);
	 preparedStmt.setInt(4, contac_no);
	 preparedStmt.setString(5, email);
	 preparedStmt.setInt(6, userID);
	 preparedStmt.execute(); 
	 con.close();
	
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the User Details."; 
	 System.err.println(e.getMessage());
	 System.err.println(e);
	 } 
	 return output; 
	 } 

public String deleteUserData(String userID) 
{ 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from users where userID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(userID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output;
}
































}
