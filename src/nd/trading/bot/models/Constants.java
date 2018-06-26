package nd.trading.bot.models;

public class Constants {
	
	 public static class ThreadProperties
     {
         public static final String THREADSLEEP = "ThreadSleep";
     }
     public static class Directories
     {
         public static final String BASEPATH = "BasePath";
         public static final String CONFIGPATH = "ConfigPath";
         public static final String CONFIGFILE = "ConfigFile";
         public static final String INIFILE = "config.ini";
     }
     public static class DB
     {
         public static final String SERVERNAME = "ServerName";
         public static final String DBNAME = "DBName";
     }
	
	public static class OrderState
    {
        public  static final String PENDING = "PENDING";
        public  static final String FILLED = "FILLED";
        public  static final String CANCELLED = "CANCELLED";
    }
    public class OrderAction
    {
        public static final String BUY = "buy";
        public static final String SELL = "sell";
    }
    public class OrderType
    {
        public static final String MARKET = "Market";
        public static final String LIMIT = "Limit";
    }
    public class TradeMode
    {
        public static final String FIXED = "FIXED";
        public static final String VARIABLE = "VARIABLE";
    }
    public class PositionState
    {
        public static final String CLOSED = "CLOSED";
    }
  
}
