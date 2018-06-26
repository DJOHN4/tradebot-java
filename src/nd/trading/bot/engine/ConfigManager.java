package nd.trading.bot.engine;

import java.io.*;
import java.util.*;

import nd.trading.bot.core.IExchange;
import nd.trading.bot.core.IStrategy;
import nd.trading.bot.models.*;
import nd.trading.bot.objectfactory.ExchangeFactory;
import nd.trading.bot.objectfactory.StrategyFactory;

import com.fasterxml.jackson.databind.*;

public class ConfigManager {

	public static String basePath = System.getProperty("user.dir");
	public static String configPath = "\\config";
	public static String iniFile = (basePath + (configPath + "\\config.ini" /*
																			 * +
																			 * Constants
																			 * .
																			 * Directories
																			 * .
																			 * CONFIGFILE
																			 * )
																			 */));
	public static String configFile = (basePath + (configPath + "\\config.json" /*
																				 * +
																				 * Constants
																				 * .
																				 * Directories
																				 * .
																				 * CONFIGFILE
																				 * )
																				 */));
	public static int threadSleep = 1000;
	public static String mongoServer = "";
	public static String mongoDbName = "";

	public static List<IStrategy> getConfigurations() {
		List<IStrategy> strategyList = null;
		try {
			strategyList = new ArrayList<IStrategy>();
			ObjectMapper mapper = new ObjectMapper();
			Properties prop = new Properties();
			InputStream input = null;

			input = new FileInputStream(iniFile);

			// load a ini properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("ThreadSleep"));
			mongoServer = prop.getProperty("MongoServer");
			mongoDbName = prop.getProperty("DBName");

			// JSON from file to Object
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
			ConfigRoot rootConfig = mapper.readValue(new File(configFile),
					ConfigRoot.class);

			ExchangeConfig xChng = null;
			StrategyConfig strgyItem = null;
			StrategyFactory strategyObjectFactory = null;
			ExchangeFactory xChangeObjectFactory = null;
			IExchange xChangeObj = null;
			IStrategy stratObj = null;
			
			for (int i = 0; i < rootConfig.Exchanges.size(); i++) {
				xChng = rootConfig.Exchanges.get(i);
				if (xChng.ActiveStatus) {
					xChangeObjectFactory = new ExchangeFactory();
					xChangeObj = xChangeObjectFactory.getObject(xChng);
					for (int j = 0; j < xChng.Strategies.size(); j++) {
						strgyItem = xChng.Strategies.get(j);
						strategyObjectFactory = new StrategyFactory();
						if (strgyItem.activeStatus) {
							stratObj = strategyObjectFactory
									.getObject(strgyItem);
							stratObj.setExchangeDetails(xChangeObj);
							strategyList.add(stratObj);
						}
					}
				}
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return strategyList;
	}
}
