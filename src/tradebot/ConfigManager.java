package tradebot;

public static class ConfigManager {
	
	public static JObject jsonObject = null;
    public static string basePath = System.IO.Directory.GetCurrentDirectory().ToString();
    public static string configPath = ConfigurationManager.AppSettings[Constants.Directories.CONFIGPATH].ToString();
    public static string configFile = basePath + configPath + Constants.Directories.CONFIGFILE;
    public static int threadSleep = Convert.ToInt32(ConfigurationManager.AppSettings[Constants.ThreadProperties.THREADSLEEP].ToString());
    

    public static List<IStrategy> GetConfigurations()
    {
        List<IStrategy> strategyList = new List<IStrategy>();
        IFactory xChangeObjectFactory = null;
        IFactory strategyObjectFactory = null;
        IStrategy stratObj = null;
        IExchange xChangeObj = null;

        using (StreamReader file = File.OpenText(configFile))
        using (JsonTextReader reader = new JsonTextReader(file))
        {
            jsonObject = (JObject)JToken.ReadFrom(reader);
        }

        JArray xngList = (JArray)jsonObject[Constants.Exchanges.SECTIONNAME];
            foreach (JObject xngItem in xngList)
            {
                xChangeObjectFactory = new ExchangeFactory();
                xChangeObj = xChangeObjectFactory.GetObject<IExchange>(xngItem);
                JArray strgyList = (JArray)xngItem[Constants.Strategies.SECTIONNAME];
                foreach (JObject strgyItem in strgyList)
                {
                    strategyObjectFactory = new StrategyFactory();
                    stratObj = strategyObjectFactory.GetObject<IStrategy>(strgyItem);
                    stratObj.SetExchangeDetails(xChangeObj);
                    stratObj.SetStrategyId();
                    strategyList.Add(stratObj);
                }
            }
        return strategyList;
    }
}

}
