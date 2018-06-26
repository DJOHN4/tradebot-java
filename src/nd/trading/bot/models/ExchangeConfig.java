package nd.trading.bot.models;

import java.util.ArrayList;
import java.util.List;

public class ExchangeConfig implements IConfig {

	public String name ;
    public String market ;
    public String baseUrl ;
    public String key ;
    public String secret ;
    public String passphrase ;
    public String token ;
    public String tickers ;
    public String currencies ;
    public boolean activeStatus ;
    public List<StrategyConfig> strategies ;
    
    public ExchangeConfig(){
    	strategies=new ArrayList<StrategyConfig>();
	}
    
}
