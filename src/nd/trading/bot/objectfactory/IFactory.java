package nd.trading.bot.objectfactory;

import nd.trading.bot.models.IConfig;

public interface IFactory {
	 public <T> T getObject(IConfig config);
}
