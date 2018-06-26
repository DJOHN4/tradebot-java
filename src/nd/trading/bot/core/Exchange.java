package nd.trading.bot.core;

import java.util.List;

import nd.trading.bot.models.*;
import nd.trading.utilities.AuthInfo;

public abstract class Exchange {

	private String name;
	private String marketName;
	private String[] tickerList;
	private String[] currencies;
	protected AuthInfo authVal;

	public Exchange(ExchangeConfig config) {
		this.setName(config.name);
		this.setMarketName(config.market);

		authVal = new AuthInfo(config.baseUrl == null ? "" : config.baseUrl, config.key == null ? "" : config.key,
				config.secret == null ? "" : config.secret, config.passphrase == null ? "" : config.passphrase,
				config.token == null ? "" : config.token);
		if (config.tickers != null)
			tickerList = config.tickers.split(",");
		if (config.currencies != null)
			currencies = config.currencies.split(",");

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
}
