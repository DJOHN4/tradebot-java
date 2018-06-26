package nd.trading.bot.core;

public interface IStrategy {
	 String getStrategyName();
     void setExchangeDetails(IExchange xChange);
     void setStrategyId();
     String getStrategyId();
     IExchange getExchange();
}
