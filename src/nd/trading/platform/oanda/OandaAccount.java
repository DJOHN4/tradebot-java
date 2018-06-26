package nd.trading.platform.oanda;

import java.util.ArrayList;
import java.util.List;
import nd.trading.platform.models.*;


public class OandaAccount extends Account {
	 public List<ForexPosition> PositionList;
     public List<Order> OrderList;
     public String LastTransactionId;

     public OandaAccount()
     {
         PositionList = new ArrayList<ForexPosition>();
         OrderList = new ArrayList<Order>();
     }
}
