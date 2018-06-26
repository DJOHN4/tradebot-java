package nd.trading.bot.core;

import nd.trading.bot.models.StrategyConfig;
import nd.trading.platform.models.*;
import nd.trading.platform.oanda.OandaAccount;


public abstract class Strategy  {

	private String name;
    protected float currentPrice;
    protected int exitLoop;
    protected IExchange exchangeObject;
    protected Account primaryAccountInfo;
    protected StrategyConfig strategyInfo;
    protected String myStrategyJson;
    protected Position stockObject;
    protected String tradeAccountId;
    
	public Strategy(StrategyConfig config){
		 this.strategyInfo = config;
         //this.myStrategyJson = JsonConvert.SerializeObject(this.StrategyInfo);
         this.exitLoop = 0;
         this.setName(config.name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public IExchange getExchangeObject() {
		return this.exchangeObject;
	}

	public void setExchangeObject(IExchange obj) {
		this.exchangeObject = obj;
	}
	
	 protected boolean IsEnoughPositionToTrade()
     {
		 boolean rtnVal = false;
         int expectedPositionLevel = (this.strategyInfo.maxPositionCount * this.strategyInfo.minPositionLevelPercent) / 100;
         if (((OandaAccount)primaryAccountInfo).PositionList.size() >= expectedPositionLevel)
             rtnVal = true;
         
         return rtnVal;
     }

}
