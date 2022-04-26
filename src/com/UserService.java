package com;

import model.User;


//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
 
//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Users")
public class UserService 
{ 
	User usrObj = new User();
@GET
@Path("/") 
@Produces(MediaType.TEXT_PLAIN) 
public String readUserData() 
 { 
 return usrObj.readUserData(); 
 } 

@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertUserData(@FormParam("user_name") String user_name, 
						@FormParam("password") String password, 
						@FormParam("address") String address, 
						@FormParam("contac_no") Integer contac_no,
						@FormParam("email")String email) 
{ 
		String output = usrObj.insertUserData(user_name, password, address, contac_no, email); 
		return output; 
}

@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updateUser(String userData) 
{ 
//Convert the input string to a JSON object 
 JsonObject itemObject = new JsonParser().parse(userData).getAsJsonObject(); 
//Read the values from the JSON object
 Integer userID = itemObject.get("userID").getAsInt(); 
 String user_name = itemObject.get("user_name").getAsString(); 
 String password = itemObject.get("password").getAsString(); 
 String address = itemObject.get("address").getAsString(); 
 Integer contac_no = itemObject.get("contac_no").getAsInt();
 String email = itemObject.get("email").getAsString();
 String output = usrObj.updateUser(user_name, password, address, contac_no, email, userID); 
return output; 
}

@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteUserData(String itemData) 
{ 
//Convert the input string to an XML document
 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
 
//Read the value from the element <itemID>
 String userID = doc.select("userID").text(); 
 String output = usrObj.deleteUserData(userID); 
return output; 
}



















}