package nd.trading.bot.objectfactory;

import nd.trading.bot.core.Forexpolate;
import nd.trading.bot.models.IConfig;
import nd.trading.bot.models.StrategyConfig;

public class StrategyFactory implements IFactory {

	public <T> T getObject(IConfig config) {
		T obj=null;
		switch (((StrategyConfig) config).name) {
		case "Forexpolate":
			obj= (T) new Forexpolate((StrategyConfig) config);
			return obj;
		default:
			return null;
		}
		
	}

}
