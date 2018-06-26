package nd.trading.bot.models;

public class PositionConfig  implements IConfig {
	 public String symbol;
     public String buyMode;
     public String sellMode;
     public float  fixedProfitValue;
     public float  fixedBuyPriceValue;
     public float  priceDownPercent;
     public float  sellProfitPercent;
     public String thresholdMode;
     public float  value;
     public float  initialUnits;
     public float  extrapolateLevel;
     public float  extrapolateUnitFactor;
     public float  priceRangeStart;
     public float  priceRangeEnd;
     public float  minQuantity;
     public boolean tradeProcess;
}
