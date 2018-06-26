package nd.trading.bot.models;

import java.util.List;
import java.util.Optional;

public class UserConfig implements IConfig {

	public String name;
	public String role;
	public String startStatus;
	public boolean activeStatus;
	public List<PositionConfig> buyList;
}
