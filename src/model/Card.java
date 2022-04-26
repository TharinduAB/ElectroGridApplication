package model;
import java.sql.*; 
public class Card {

	private Connection connect() 
	{ 
			Connection con = null; 
			
			try
	{ 
				Class.forName("com.mysql.jdbc.Driver"); 
 
				//Provide the correct details: DBServer/DBName, username, password 
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Grid", "root", ""); 
	} 
			catch (Exception e) 
			{e.printStackTrace();} 
 
			return con; 
 }

public String insertCardData(String name, String cardno, String month, String year,String ccvno) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into carddata (`cardID`,`cardHolderName`,`cardNo`,`Month`,`Year`,`ccvNo`)"
 + " values (?, ?, ?, ?, ? , ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, name); 
 preparedStmt.setString(3, cardno); 
 preparedStmt.setString(4, month);
 preparedStmt.setString(5, year);
 preparedStmt.setString(6, ccvno);
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


public String readCardData() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Card ID</th><th>Card Holder Name</th><th>Card No</th>" + "<th>Month</th>" +
 "<th>Year</th>" + 
 "<th>CCV No</th>" +
 "<th>Update</th><th>Remove</th></tr>"; 
 
 String query = "select * from carddata"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String cardID = Integer.toString(rs.getInt("cardID")); 
 String cardHolderName = rs.getString("cardHolderName"); 
 String cardNo = rs.getString("cardNo"); 
 String Month = rs.getString("Month"); 
 String Year = rs.getString("Year"); 
 String ccvNo = rs.getString("ccvNo");
 // Add into the html table
 output += "<tr>  <td>" + cardID + "</td>"; 
 output += "<td>" + cardHolderName + "</td>"; 
 output += "<td>" + cardNo + "</td>"; 
 output += "<td>" + Month + "</td>"; 
 output += "<td>" + Year + "</td>";
 output += "<td>" + ccvNo + "</td>"; 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='cardID' type='hidden' value='" + cardID 
 + "'>" + "</form></td></tr>"; 
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


public String updateCard(String ID, String name, String cardno, String month, String year,String ccvno)
{ 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 String query = "UPDATE carddata SET cardHolderName=?,cardNo=?,Month=?,Year=?,ccvNo=? WHERE cardID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 
	 preparedStmt.setString(1, name); 
	 preparedStmt.setString(2, cardno); 
	 preparedStmt.setString(3, month);
	 preparedStmt.setString(4, year);
	 preparedStmt.setString(5, ccvno);
	 
	 preparedStmt.setInt(6, Integer.parseInt(ID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the Card Details."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	public String deleteCard(String itemID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from carddata where cardID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(itemID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the card details."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	

	
	} 

 // execute the statement