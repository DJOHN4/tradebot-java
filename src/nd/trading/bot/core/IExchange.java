package nd.trading.bot.core;

import java.util.List;
import nd.trading.platform.models.*;


public interface IExchange {
	
	 String getExchangeName();
     String getMarketName();
     Account getAccountDetails(String accountId);
     void getAccountChanges(Account acnt);
     List<Quote> getQuoteList(String[] tickerList);
     boolean placeOrder(Order order);
     float getCurrentPrice(String symbol, String mode);
     boolean closeTrade(ForexStock fxS);
     
}
