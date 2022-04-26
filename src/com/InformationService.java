package com;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*;

import model.Information;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Information")
public class InformationService {

	
	Information information = new Information();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return information.readInformation();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertInformation(@FormParam("informationID") int informationID, @FormParam("category") String category, @FormParam("name") String name, @FormParam("summary") String summary, @FormParam("status") String status) {
		String output = information.insertInformation(informationID, category, name, summary, status);
		
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInformation(String itemData) {
		//Convert input string to a JSON object
		JsonObject informationObject = new JsonParser().parse(itemData).getAsJsonObject();

		//Read the values from the JSON object
		String informationID = informationObject.get("informationID").getAsString();
		String category = informationObject.get("category").getAsString();
		String name = informationObject.get("name").getAsString();
		String summary = informationObject.get("summary").getAsString();
		String status = informationObject.get("status").getAsString();
		
		String output = information.updateInformation(informationID, category, name, summary, status);
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInformation(String itemData) {
		//Convert the input string to an xml document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
		
		//Read the value from the element <itemID>
		String informationID = doc.select("informationID").text();
		
		String output = information.deleteInformation(informationID);
		
		return output;
	}
	
	
	
}
