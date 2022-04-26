package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Billpay {
		
	private Connection connect() 
	{ 
			Connection con = null; 
			
			try
	{ 
				Class.forName("com.mysql.jdbc.Driver"); 
 
				//Provide  correct details: DBServer/DBName, username, password 
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Grid", "root", ""); 
	} 
			catch (Exception e) 
			{e.printStackTrace();} 
 
			return con; 
	}
	
	
	//-------------------------------------------------------------------------
	
	public String readPaymentData() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Bill ID</th><th>Customer Name</th><th>Month</th>" + "<th>Card ID</th>" +
	 "<th>Amount</th>" ; 
	 
	 String query = "select * from payment"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String pymntID = Integer.toString(rs.getInt("pymntID")); 
	 String billID = rs.getString("billID"); 
	 String customerName = rs.getString("customerName"); 
	 String Month = rs.getString("Month"); 
	 String cardID = rs.getString("cardID"); 
	 //String Amount = rs.getString("Amount");
	 String Amount = Double.toString(rs.getDouble("Amount"));
	 
	 // Add into the html table
	 output += "<tr>  <td>" + billID + "</td>"; 
	 output += "<td>" + customerName + "</td>"; 
	 output += "<td>" + Month + "</td>"; 
	 output += "<td>" + cardID + "</td>"; 
	 output += "<td>" + Amount + "</td>";
	 // buttons + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the Card Details."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 


	
	public String insertPayMentdata(String billid, String customername, String month, String cardid, String amount) 
		
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for inserting."; } 
		 // create a prepared statement
		 String query = " insert into payment (`pymntID`,`billID`,`customerName`,`Month`,`cardID`,`Amount`)"
		 + " values (?, ?, ?, ?, ? , ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, billid); 
		 preparedStmt.setString(3, customername); 
		 preparedStmt.setString(4, month);
		 preparedStmt.setString(5, cardid);
		 //preparedStmt.setString(6, amount);
		 preparedStmt.setDouble(6, Double.parseDouble(amount)); 
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
}
