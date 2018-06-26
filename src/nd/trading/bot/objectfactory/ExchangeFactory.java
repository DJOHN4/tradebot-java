package nd.trading.bot.objectfactory;

import nd.trading.bot.models.ExchangeConfig;
import nd.trading.bot.models.IConfig;
import nd.trading.platform.oanda.OandaExchange;

public class ExchangeFactory implements IFactory {
	@Override
	public <T> T getObject(IConfig config) {
		switch (((ExchangeConfig)config).Name)
	    {
	        case "Oanda": return (T) new OandaExchange((ExchangeConfig)config);
	        default: return null;
	    }
	}
}