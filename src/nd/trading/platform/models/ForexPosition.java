package nd.trading.platform.models;

import java.util.ArrayList;
import java.util.List;

public class ForexPosition extends Position {
	 public float UnrealizedPL;
     public List<String> TradeIdList;

     public ForexPosition()
     {
         TradeIdList = new ArrayList<String>();
     }
}
