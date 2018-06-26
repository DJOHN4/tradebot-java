package nd.trading.platform.models;

import java.util.Date;
import java.util.List;

import nd.trading.bot.models.StrategyInfo;

public class Position extends Entity {
	 public String Symbol ;
     public float TotalCount ;
     public float AveragePrice ;
     public short Index ;
     public String Status ;

     public float TotalBuyFee ;
     public float TotalCost ;
     public float SellPrice ;
     public float TotalSellFee ;
     public float TotalRevenue ;
     public float ProfitnLoss ;
     public float ProfitPercentage ;

     public Date ModifiedOn ;
     public List<StockData> TradeList ;
     public StrategyInfo StrategyData ;
     public String OrderId ;
     public String Identifier ;
}
