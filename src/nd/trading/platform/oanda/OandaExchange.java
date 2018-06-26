package nd.trading.platform.oanda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nd.trading.bot.core.Exchange;
import nd.trading.bot.core.IExchange;
import nd.trading.bot.models.*;
import nd.trading.platform.models.Account;
import nd.trading.platform.models.ForexStock;
import nd.trading.platform.models.Order;
import nd.trading.platform.models.Quote;
import nd.trading.utilities.IApiClient;
import nd.trading.utilities.Methods;

public class OandaExchange extends Exchange implements IExchange {

	private IApiClient apiClient = null;

	public OandaExchange(ExchangeConfig config) {
		super(config);
		/*
		 * Dictionary<String, String> headers = new Dictionary<String, String>();
		 * 
		 * { { "Authorization", "Bearer " + this.AuthVal.Token}/*, { "Accept-Encoding",
		 * "gzip, deflate" },
		 */
		// };

		// apiClient = new TokenClient(this.AuthVal, headers);*/
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
	public List<Quote> getQuoteList(String[] tickerList, String accountId) {
		List<Quote> result = null;

		String method = EndPoints.ACCOUNT_INSTRUMENTS;
		method = method.replace("{accountId}", accountId);

		if (tickerList == null)
			tickerList = this.getTickerList();

		String param = "instruments=" + String.join(",", tickerList);

		String jsonResult = apiClient.CallApi(this.getExchangeName(), Methods.GET, method, false, param, "");
		if (jsonResult != "") {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(jsonResult, new TypeReference<Map<String, Object>>() {
			});
			List qArray = (List) map.get("instruments");
			result = new ArrayList<Quote>();
			for (Object item : qArray) {
				Quote q = OandaConvert.DeserializeObject < Quote > (item);
				q.LastTradePrice = GetCurrentBuyPrice(q.Symbol);
				result.Add(q);
			}
		}
		return result;
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
