package com;

import model.Billing;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/billdetails")

public class BillingService {
	
	Billing billdetailObj = new Billing();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBilldetails()
	 {
			return billdetailObj.readbilldetails();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertbilldetails(
	 @FormParam("name") String name,
	 @FormParam("accountNumber") String accountNumber,
	 @FormParam("serviceAddress") String serviceAddress,
	 @FormParam("dueDate") String dueDate,
	 @FormParam("unitsUsed") String unitsUsed,
	 @FormParam("amount") String amount)
	 
	{
	 String output = billdetailObj.insertbilldetails(name, accountNumber, serviceAddress, dueDate, unitsUsed, amount);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatebilldetails(String billdetailData)
	{
	//Convert the input string to a JSON object
	 JsonObject billdetailObject = new JsonParser().parse(billdetailData).getAsJsonObject();
	//Read the values from the JSON object
	 String billID = billdetailObject.get("billID").getAsString();
	 String name = billdetailObject.get("name").getAsString();
	 String accountNumber = billdetailObject.get("accountNumber").getAsString();
	 String serviceAddress = billdetailObject.get("serviceAddress").getAsString();
	 String dueDate = billdetailObject.get("dueDate").getAsString();
	 String unitsUsed = billdetailObject.get("unitsUsed").getAsString();
	 String amount = billdetailObject.get("amount").getAsString();
	 String output = billdetailObj.updatebilldetails(billID, name, accountNumber, serviceAddress, dueDate, unitsUsed, amount);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletebilldetails(String billData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());

	//Read the value from the element <requestId>
	 String billID = doc.select("billID").text();
	 String output = billdetailObj.deletebilldetails(billID);
	return output;
	}
	
} 



