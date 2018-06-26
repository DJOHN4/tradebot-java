package nd.trading.bot.core;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import nd.trading.bot.models.AccountConfig;
import nd.trading.bot.models.PositionConfig;
import nd.trading.bot.models.StrategyConfig;
import nd.trading.bot.models.UserConfig;
import nd.trading.platform.models.*;
import nd.trading.platform.oanda.OandaAccount;

public abstract class Strategy {

	private String name;
	private String subName;
	private boolean activeStatus;
	private int minPositionLevelPercent;
	protected int maxPositionCount;
	protected String orderType;
	protected List<AccountConfig> accountConfigList;
	protected List<UserConfig> userConfigList;

	protected int exitLoop;
	protected float currentPrice;
	protected Account primaryAccountInfo;
	protected IExchange exchangeObject;
	protected Position stockObject;

	public Strategy(StrategyConfig config) {
		try {
			this.setName(config.name);
			this.subName = config.subName;
			this.activeStatus = config.activeStatus;
			this.minPositionLevelPercent = config.minPositionLevelPercent;
			this.maxPositionCount = config.maxPositionCount;
			this.orderType = config.orderType;
			this.accountConfigList = config.accounts;
			this.userConfigList = config.users;
			// ObjectMapper mapper = new ObjectMapper();
			// this.myStrategyJson = mapper.writeValueAsString(this.strategyInfo);
			this.exitLoop = 0;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IExchange getExchangeObject() {
		return this.exchangeObject;
	}

	public void setExchangeObject(IExchange obj) {
		this.exchangeObject = obj;
	}

	protected PositionConfig getPositionConfig(String symbol) {
		PositionConfig pConfig = null;

		for (int i = 0; i < userConfigList.size(); i++) {
			Optional<PositionConfig> optional = userConfigList.get(i).buyList.stream()
					.filter(x -> symbol.equals(x.symbol)).findFirst();

			if (optional.isPresent()) {
				return optional.get();
			}
		}
		return pConfig;
	}

	protected UserConfig getUserConfig(String symbol) {
		UserConfig uConfig = null;

		for (int i = 0; i < userConfigList.size(); i++) {
			Optional<PositionConfig> optional = userConfigList.get(i).buyList.stream()
					.filter(x -> symbol.equals(x.symbol)).findFirst();

			if (optional.isPresent()) {
				return userConfigList.get(i);
			}
		}

		return uConfig;
	}

	protected String getPrimaryAccountId() {
		String primaryAccountId = "";

		Optional<AccountConfig> optional = accountConfigList.stream().filter(x -> "Longbuy".equals(x.type)).findFirst();

		if (optional.isPresent()) {
			return optional.get().id;
		}
		return primaryAccountId;

	}

	protected boolean IsEnoughPositionToTrade() {
		boolean rtnVal = false;
		int expectedPositionLevel = (this.maxPositionCount * this.minPositionLevelPercent) / 100;
		if (((OandaAccount) primaryAccountInfo).PositionList.size() >= expectedPositionLevel)
			rtnVal = true;

		return rtnVal;
	}

}
