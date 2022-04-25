package com;

import model.Station;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Stations")
public class StationService {
	
	Station stationObj = new Station();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readStations()
	 {
		return stationObj.readStations();
	 } 
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertStation(@FormParam("stationCode") String stationCode,
	 @FormParam("stationName") String stationName,
	 @FormParam("location") String location,
	 @FormParam("zone") String zone,
	 @FormParam("province") String province,
	 @FormParam("capacity") Float capacity,
	 @FormParam("status") String status)
	{
	 String output = stationObj.insertStation(stationCode, stationName, location, zone, province, capacity, status);
	return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateStation(String stationData)
	{
	//Convert the input string to a JSON object
	 JsonObject stationObject = new JsonParser().parse(stationData).getAsJsonObject();
	//Read the values from the JSON object
	 String stationID = stationObject.get("stationID").getAsString();
	 String stationCode = stationObject.get("stationCode").getAsString();
	 String stationName = stationObject.get("stationName").getAsString();
	 String location = stationObject.get("location").getAsString();
	 String zone = stationObject.get("zone").getAsString();
	 String province = stationObject.get("province").getAsString();
	 Float capacity = stationObject.get("capacity").getAsFloat();
	 String status = stationObject.get("status").getAsString();
	 String output = stationObj.updateStation(stationID, stationCode, stationName, location, zone, province, capacity, status);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteStation(String stationData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(stationData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String stationID = doc.select("stationID").text();
	 String output = stationObj.deleteStation(stationID);
	return output;
	}
	
}
