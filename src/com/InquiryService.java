package com;

import model.Inquiry;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/inquiries")
public class InquiryService
{
	 Inquiry inquiryObj = new Inquiry();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInquiries()
	 {
			return inquiryObj.readInquiries();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertInquiries(
	@FormParam("name") String name,
	 @FormParam("email") String email,
	 @FormParam("contactNumber") String contactNumber,
	 @FormParam("address") String address,
	 @FormParam("inquiryType") String inquiryType,
	 @FormParam("message") String message)
	 
	{
	 String output = inquiryObj.insertInquiries(name, email, contactNumber, address, inquiryType, message);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInquiries(String inquiryData)
	{
	//Convert the input string to a JSON object
	 JsonObject inquiryObject = new JsonParser().parse(inquiryData).getAsJsonObject();
	//Read the values from the JSON object
	 String inquiryID = inquiryObject.get("inquiryID").getAsString();
	 String name = inquiryObject.get("name").getAsString();
	 String email = inquiryObject.get("email").getAsString();
	 String contactNumber = inquiryObject.get("contactNumber").getAsString();
	 String address = inquiryObject.get("address").getAsString();
	 String inquiryType = inquiryObject.get("inquiryType").getAsString();
	 String message = inquiryObject.get("message").getAsString();
	 String output = inquiryObj.updateInquiries(inquiryID, name, email, contactNumber, address, inquiryType, message);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInquiries(String inquiryData)
	{
	//Convert the input string to an XML documents
	 Document doc = Jsoup.parse(inquiryData, "", Parser.xmlParser());

	//Read the value from the element <requestId>
	 String inquiryID = doc.select("inquiryID").text();
	 String output = inquiryObj.deleteInquiries(inquiryID);
	return output;
	}
	
}