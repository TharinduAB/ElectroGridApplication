package com;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*;

import model.Billpay;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Billpay")

public class BillPayService {
 
	Billpay itemObj = new Billpay(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
		return itemObj.readPaymentData(); 
	 } 
	
	//---------------------------------------------------------------------------------------

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertPayMent(@FormParam("billID") String billID, 
	 @FormParam("customerName") String customerName, 
	 @FormParam("Month") String Month, 
	 @FormParam("cardID") String cardID, 
	@FormParam("Amount") String Amount)
	{ 
	 String output = itemObj.insertPayMentdata(billID, customerName, Month, cardID,Amount); 
	return output; 
	}
}


