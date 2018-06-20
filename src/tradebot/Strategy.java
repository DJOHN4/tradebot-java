package tradebot;

public abstract class Strategy implements IStrategy, Runnable  {

	@Override
	public String GetStrategyName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SetExchangeDetails(IExchange xChange) {
		// TODO Auto-generated method stub

	}

	@Override
	public void SetStrategyId() {
		// TODO Auto-generated method stub

	}

	@Override
	public String GetStrategyId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IExchange GetExchangeObject() {
		// TODO Auto-generated method stub
		return null;
	}
	
	 public void run() {
	        System.out.println("Hello from a thread!");
	    }

}
