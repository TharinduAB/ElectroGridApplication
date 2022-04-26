package com;

//For JSON
import com.google.gson.*;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import model.Billpay;
//For XML
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.parser.*;

@Path("/Billpay")
public class BillPayService {

  Billpay paymentObj = new Billpay();

  @GET
  @Path("/")
  @Produces(MediaType.TEXT_HTML)
  public String readPayment() {
    return paymentObj.readPaymentData();
  }

  @POST
  @Path("/")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.TEXT_PLAIN)
  public String insertPayMent(
    @FormParam("billID") String billID,
    @FormParam("customerName") String customerName,
    @FormParam("Month") String Month,
    @FormParam("cardID") String cardID,
    @FormParam("Amount") String Amount
  ) {
    String output = paymentObj.insertPayMentdata(
      billID,
      customerName,
      Month,
      cardID,
      Amount
    );
    return output;
  }
}
