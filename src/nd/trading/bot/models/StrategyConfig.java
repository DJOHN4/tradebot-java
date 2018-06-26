package nd.trading.bot.models;

import java.util.List;
import java.util.Optional;

public class StrategyConfig  implements IConfig {

	public String name;
    public String subName;
    public int minPositionLevelPercent;
    public int maxPositionCount;
    public String orderType;
    public boolean activeStatus;
    public List<AccountConfig> accounts;
    public List<UserConfig> users;    
}
