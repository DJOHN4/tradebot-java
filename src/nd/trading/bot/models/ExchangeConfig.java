package nd.trading.bot.models;

import java.util.ArrayList;
import java.util.List;

public class ExchangeConfig implements IConfig {

	public String Name ;
    public String Market ;
    public String BaseUrl ;
    public String Key ;
    public String Secret ;
    public String Passphrase ;
    public String Token ;
    public String TickerList ;
    public String Currencies ;
    public boolean ActiveStatus ;
    public List<StrategyConfig> Strategies ;
    
    public ExchangeConfig(){
    	Strategies=new ArrayList<StrategyConfig>();
	}
    
}
