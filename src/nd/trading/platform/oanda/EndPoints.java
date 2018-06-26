package nd.trading.platform.oanda;

public class EndPoints {
	
     public static final String LIST_ACCOUNTS = "/v3/accounts";
     public static final String ACCOUNT_INFO = "/v3/accounts/{accountId}";
     public static final String ACCOUNT_INSTRUMENTS = "/v3/accounts/{accountId}/instruments";
     public static final String ACCOUNT_SUMMARY = "/v3/accounts/{accountId}/summary";
     public static final String ACCOUNT_CHANGES = "/v3/accounts/{accountId}/changes";
  
     public static final String OPEN_POSITIONS = "/v3/accounts/{accountId}/openPositions";
     public static final String OPEN_INSTRUMENT_POSITIONS = "/v3/accounts/{accountId}/positions/";

     public static final String LIST_TRADES = "/v3/accounts/{accountId}/trades";
     public static final String LIST_ALL_OPEN_TRADES = "/v3/accounts/{accountId}/openTrades";
     public static final String LIST_OPEN_TRADES = "/v3/accounts/{accountId}/trades";
     public static final String CLOSE_TRADE = "/v3/accounts/{accountId}/trades/{tradeId}/close";

     public static final String GET_ORDER = "/v3/accounts/{accountId}/orders/{orderId}";
     public static final String POST_ORDER = "/v3/accounts/{accountId}/orders";

     public static final String LIST_TRANSACTIONS = "v3/accounts/{accountId}/transactions";
     public static final String GET_CANDLES = "/v3/instruments/{instrument}/candles"; 
}
