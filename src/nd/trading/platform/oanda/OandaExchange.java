package nd.trading.platform.oanda;

import java.util.List;

import nd.trading.bot.core.Exchange;
import nd.trading.bot.core.IExchange;
import nd.trading.bot.models.*;
import nd.trading.platform.models.Account;
import nd.trading.platform.models.ForexStock;
import nd.trading.platform.models.Order;
import nd.trading.platform.models.Quote;

public class OandaExchange extends Exchange implements IExchange {

	//private IApiClient apiClient = null;
    private String accountId = "";

    public OandaExchange(ExchangeConfig config)
    {
    	super(config);
   /*     Dictionary<String, String> headers = new Dictionary<String, String>();
        
        {
            { "Authorization", "Bearer "  + this.AuthVal.Token}/*,
            { "Accept-Encoding", "gzip, deflate" },*/
       // };

        //apiClient = new TokenClient(this.AuthVal, headers);*/
     }

	@Override
	public String getExchangeName() {
		return getName();
	}

	@Override
	public Account getAccountDetails(String accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getAccountChanges(Account acnt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Quote> getQuoteList(String[] tickerList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean placeOrder(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getCurrentPrice(String symbol, String mode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean closeTrade(ForexStock fxS) {
		// TODO Auto-generated method stub
		return false;
	}
    
}
