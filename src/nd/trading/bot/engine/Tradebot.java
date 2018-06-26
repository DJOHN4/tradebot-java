package nd.trading.bot.engine;

import java.util.List;

import nd.trading.bot.core.IStrategy;
import nd.trading.db.DbManager;
import nd.trading.platform.models.Position;

public class Tradebot {

	private List<IStrategy> strategyList = null;

	public void triggerBot() {
		Thread newThread = null;
		try {
			// Get exchange list
			//ReadConfigurations();
			
			
			Position pos=new Position();
			pos.Symbol="Dinu";
			this.strategyList = ConfigManager.getConfigurations();
//DbManager.insertEntity(pos);
			
			
			
			
			
			for (int i = 0; i < this.strategyList.size(); i++) {

				System.out.println("Market: " + this.strategyList.get(i).getExchange().getMarketName()
						+ " | Exchange: " + this.strategyList.get(i).getExchange().getExchangeName()
						+ " | Strategy: " + this.strategyList.get(i).getStrategyName() + " Trade triggered");

				newThread = new Thread((Runnable) this.strategyList.get(i));
				newThread.start();
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
