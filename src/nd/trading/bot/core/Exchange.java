package nd.trading.bot.core;

import nd.trading.bot.models.*;

public abstract class Exchange {

	
	 private String name;
     private String marketName;
     private String[] tickerList;
    // private List<CurrencyInfo> Currencies;
     //protected AuthInfo AuthVal;
     
     
	public Exchange(ExchangeConfig config) {
		 this.setName(config.Name);
         this.setMarketName(config.Market);
	}

	public String getMarketName() {
		return this.marketName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}


	public String[] getTickerList() {
		return tickerList;
	}


	public void setTickerList(String[] tickerList) {
		this.tickerList = tickerList;
	}

}
