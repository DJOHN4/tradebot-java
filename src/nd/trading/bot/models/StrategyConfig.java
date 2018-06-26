package nd.trading.bot.models;

import java.util.List;

import nd.trading.platform.models.*;

public class StrategyConfig  implements IConfig {

	public String name;
    public String subName;
    public String accountId;
    public int minPositionLevelPercent;
    public int maxPositionCount;
    public int extrapolateValue;
    public String orderType;
    public boolean activeStatus;
    public List<AccountConfig> accounts;
    public List<UserConfig> users;
    
}
