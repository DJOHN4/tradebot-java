package nd.trading.platform.models;

import java.util.Date;

public class Order {
	 public String OrderId ;
     public String OrderType ;
     public String Side ;
     public String Symbol ;
     public float Count ;
     public float Price ;
     public float Cost ;
     public float Brokerage ;
     public float PnL ;
     public String Status ;
     public boolean Settled ;
     public String TradeId ;
     public Date DoneAt ;
     
     public Order() { }

     public Order(String symbol, float count, float price, String side, String type)
     {
         OrderId = "";
         Symbol = symbol;
         Count = count;
         Price = price;
         Cost = count * price;
         //Brokerage = fee;
         //Status = "ORDER";
         if (side == "")
             Side = "buy";
         else if (side == "sell")
             Side = "sell";
         if (type == "")
             OrderType = "limit";
         else
             OrderType = type;

     }
}
