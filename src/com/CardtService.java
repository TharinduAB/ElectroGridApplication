package com;
// REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*;

import model.Card;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Card")
 
public class CardtService 
{ 
		Card cardObj = new Card(); 
		@GET
		@Path("/") 
		@Produces(MediaType.TEXT_HTML) 
		public String readItems() 
		{ 
			return cardObj.readCardData(); 
		} 

		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
			public String insertCardData(@FormParam("cardHolderName") String cardHolderName, 
						@FormParam("cardNo") String cardNo, 
						@FormParam("Month") String Month, 
						@FormParam("Year") String Year, 
						@FormParam("ccvNo") String ccvNo)
		{ 
			String output = cardObj.insertCardData(cardHolderName, cardNo, Month, Year,ccvNo); 
			return output; 
		}

		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
			public String updateCard(String CardData) 
		{ 
					//Convert to input string to a JSON object 
					JsonObject cardObject = new JsonParser().parse(CardData).getAsJsonObject(); 
					//Read the values from the JSON object
						String cardID = cardObject.get("cardID").getAsString(); 
						String cardHolderName = cardObject.get("cardHolderName").getAsString(); 
						String cardNo = cardObject.get("cardNo").getAsString(); 
						String Month = cardObject.get("Month").getAsString(); 
						String Year = cardObject.get("Year").getAsString();
						String ccvNo = cardObject.get("ccvNo").getAsString(); 
						String output = cardObj.updateCard(cardID, cardHolderName, cardNo, Month, Year,ccvNo); 
			return output; 
		}

		@DELETE
		@Path("/") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
			public String deleteCard(String itemData) 
		{ 
						//Convert the input string to an XML document
						Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
 
						//Read the value from 
						String cardID = doc.select("cardID").text(); 
						String output = cardObj.deleteCard(cardID); 
			return output; 
		}





}
