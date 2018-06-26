package nd.trading.bot.models;

import java.util.ArrayList;
import java.util.List;

public class ConfigRoot implements IConfig {

	public List<ExchangeConfig> Exchanges;
	
	public ConfigRoot(){
		Exchanges=new ArrayList<ExchangeConfig>();
	}
	
}
