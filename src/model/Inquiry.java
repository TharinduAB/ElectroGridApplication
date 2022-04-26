package model;

import java.sql.*;

public class Inquiry
{ 
	//A common method to connect to the DB
	private Connection connect()
	 {
		 Connection con = null;
		 
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");
		
		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 
		 return con;
	 }
	
	//Insert
	public String insertInquiries(String name, String email, String contactNumber, String address, String inquiryType, String message)
	 {
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 
			 // create a prepared statement
			 String query = " insert into inquiries (`inquiryID`,`name`,`email`,`contactNumber`,`address`,`inquiryType`,`message`)" + " values (?, ?, ?, ?, ?, ?, ?)";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, name);
			 preparedStmt.setString(3, email);
			 preparedStmt.setString(4, contactNumber);
			 preparedStmt.setString(5, address);
			 preparedStmt.setString(6, inquiryType);
			 preparedStmt.setString(7, message);
			 
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while inserting the inquiry connection request.";
			 System.err.println(e.getMessage());
		 }
		 return output;
	}
	
	public String readInquiries()
	{
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Inquiry ID</th><th> Name</th><th>Email</th>" +
					 "<th>Contact Number</th>" +
					 "<th>Address</th>" +
					 "<th>Inquiry Type</th>" +
					 "<th>Message</th></tr>";
			
			 String query = "select * from inquiries";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String inquiryID = Integer.toString(rs.getInt("inquiryID"));
				 String name = rs.getString("name");
				 String email = rs.getString("email");
				 String contactNumber = rs.getString("contactNumber");
				 String address = rs.getString("address");
				 String inquiryType = rs.getString("inquiryType");
				 String message = rs.getString("message");
				 
				 
				 // Add into the html table
				 output += "<tr><td>" +  inquiryID + "</td>";
				 output += "<td>" + name + "</td>";
				 output += "<td>" + email + "</td>";
				 output += "<td>" + contactNumber + "</td>";
				 output += "<td>" + address + "</td>";
				 output += "<td>" + inquiryType + "</td>";
				 output += "<td>" + message + "</td>";
				 
				 
				 // buttons
				 //output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='inquiryrequests.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" + "<input name='inquiryId' type='hidden' value='" + inquiryID + "'>" + "</form></td></tr>";
			 }
			 con.close();
			 // Complete the html table
			 output += "</table>";
		 }
		 catch (Exception e)
		 {
			 output = "Error while reading the inquiries.";
			 System.err.println(e.getMessage());
		 }
		 return output;
	}
	
	//update
	public String updateInquiries(String ID, String nameIn, String emailIn, String contactNumberIn, String addressIn, String inquiryTypeIn, String messageIn)
	{
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 
			 // create a prepared statement
			 String query = "UPDATE inquiries SET name=?,email=?,contactNumber=?,address=?,inquiryType=?,message=? WHERE inquiryID=?";
			 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, nameIn);
			 preparedStmt.setString(2, emailIn);
			 preparedStmt.setString(3, contactNumberIn);
			 preparedStmt.setString(4, addressIn);
			 preparedStmt.setString(5, inquiryTypeIn);
			 preparedStmt.setString(6, messageIn);
			 preparedStmt.setInt(7, Integer.parseInt(ID));
			 
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while updating the inquiry connection request.";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
		 
	}
	
	// delete
	public String deleteInquiries(String inquiryID )
	{
		 String output = "";
		 
		 try
		 {
		 
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			 
			 // create a prepared statement
			 String query = "delete from inquiries where inquiryID=?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(inquiryID ));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while deleting the inquiry.";
			 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	}