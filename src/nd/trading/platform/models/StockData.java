package nd.trading.platform.models;

import java.util.Date;

public class StockData extends Entity {
	 public int TradeId ;
     public String Symbol ;
     public float Count ;
     public float PriceBuy ;
     public float FeeBuy ;
     public float Cost ;
     public float PriceSell ;
     public float FeeSell ;
     public float Revenue ;
     public float ProfitnLoss ;
     public Date PurchaseTime ;
     public Date ModifiedTime ;
}
