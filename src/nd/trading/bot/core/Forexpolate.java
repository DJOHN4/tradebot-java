package nd.trading.bot.core;

import java.util.Date;
import java.util.List;

import nd.trading.bot.models.Constants;
import nd.trading.bot.models.PositionConfig;
import nd.trading.bot.models.StrategyConfig;
import nd.trading.platform.models.ForexPosition;
import nd.trading.platform.models.ForexStock;
import nd.trading.platform.models.Order;
import nd.trading.platform.models.Quote;
import nd.trading.platform.oanda.*;

public class Forexpolate extends Strategy implements Runnable, IStrategy {
	private String lastTransId = "";
	private ForexStock fxStock;
	private PositionConfig posConfig=null;
	
	public Forexpolate(StrategyConfig config) {
		super(config);
	}

	public String getLastTransId() {
		return lastTransId;
	}

	public void setLastTransId(String lastTransId) {
		this.lastTransId = lastTransId;
	}

	public ForexStock getFxStock() {
		return fxStock;
	}

	public void setFxStock(ForexStock fxStock) {
		this.fxStock = fxStock;
	}

	@Override
	public String getStrategyName() {
		// TODO Auto-generated method stub
		return getName();
	}

	@Override
	public void setExchangeDetails(IExchange xChange) {
		setExchangeObject(xChange);
		
	}

	@Override
	public void setStrategyId() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getStrategyId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IExchange getExchange() {
		return getExchangeObject();
	}

	public void run() {
		getAccountDetails();
		do {
			UpdateAccountDetails();
			if (IsEnoughPositionToTrade()) {
				for (int iPos = ((OandaAccount) primaryAccountInfo).PositionList.size() - 1; iPos >= 0; --iPos) {
					this.stockObject = ((OandaAccount) primaryAccountInfo).PositionList.get(iPos);
					posConfig=this.getPositionConfig(this.stockObject.Symbol);

					if (isReadyToSell()) {
						if (placeSellOrder()) // Do we need to call account
												// update here?
						{
							System.out.println(this.stockObject.Symbol
									+ " Sold at "
									+ this.stockObject.SellPrice);
						}
					} else {
						if (isReadyToAddMore())
							addMoreStocks(); // Account update has an impact on
												// position list because of
												// closed positions
						else if (this.getPositionConfig(this.stockObject.Symbol).tradeProcess)
							processTrade(); // Account update has an impact on
											// position list because of closed
											// positions
					}
				}
			} else
				addEnoughPositions();

		/*	Thread.Sleep(Convert
					.ToInt32(ConfigurationManager.AppSettings["ThreadSleep"]
							.ToString()));*/
		} while (this.exitLoop == 0);
	}

	private void getAccountDetails() {
		primaryAccountInfo = exchangeObject.getAccountDetails(getPrimaryAccountId()); // Service
																				// call
																				// 1
																				// -
																				// Once
																				// while
																				// starting
																				// app
		lastTransId = ((OandaAccount) primaryAccountInfo).LastTransactionId;

		for (int iPos = ((OandaAccount) primaryAccountInfo).PositionList.size() - 1; iPos >= 0; --iPos) {
			System.out.println("Bought "
					+ ((OandaAccount) primaryAccountInfo).PositionList.get(iPos).TotalCount
					+ " "
					+ ((OandaAccount) primaryAccountInfo).PositionList.get(iPos).Symbol);
		}
	}

	private void UpdateAccountDetails() {
		exchangeObject.getAccountChanges(primaryAccountInfo); // Service call 2
																// - Every
																// Do-While loop
																// delay thread
																// sleep
	}

	private void addEnoughPositions()
     {
         Order orderObject = null;
         try
         {
             List<Quote> quoteList = exchangeObject.getQuoteList(null);   //Service call 3  - Initially once and whenever new position added to account based on Max pos count
             foreach (Quote q in quoteList)
             {
                 if (((OandaAccount)primaryAccountInfo).PositionList.Find(obj => obj.Symbol == q.Symbol) == null) // if (!IsAvavilableInPositionList(q.Symbol))
                 {
                     if (this.posConfig.initialUnits >= this.posConfig.minQuantity)
                     {
                         if (this.orderType == Constants.OrderType.MARKET)
                             orderObject = new Order(q.Symbol, this.posConfig.initialUnits, q.LastTradePrice, Constants.OrderAction.BUY, this.orderType);
                         else if (this.orderType == Constants.OrderType.LIMIT)
                             orderObject = new Order(q.Symbol, this.posConfig.initialUnits, q.LastTradePrice, Constants.OrderAction.BUY, this.orderType);

                         if (exchangeObject.placeOrder(orderObject))   //Service call 4
                         {
                             if (orderObject.Status == Constants.OrderState.FILLED)
                             {
                               /*  DataAnalyzer dt = new DataAnalyzer();
                                 dt.Symbol = orderObject.Symbol;
                                 Thread newThread = new Thread(dt.SaveActiveTicker);
                                 newThread.Name = "AnalyzerAdd";
                                 newThread.Start(); */
                             }
                             else if (orderObject.Status == Constants.OrderState.PENDING)
                             {
                                 System.out.println(new Date() + " : Order status " + Constants.OrderState.PENDING);
                             }
                             else if (orderObject.Status == Constants.OrderState.CANCELLED)
                             {
                                 System.out.println(new Date() + " : Order status " + Constants.OrderState.CANCELLED);
                             }
                             if (((OandaAccount)primaryAccountInfo).PositionList.size() >= this.maxPositionCount)
                                 return;
                         }
                     }
                 }
             }
         }
         catch (Exception ex)
         {
             throw ex;
         }
     }

	private boolean isReadyToSell() {
		boolean rtnVal = false;
		if (posConfig.sellMode == Constants.TradeMode.FIXED) {
			if (((ForexPosition) this.stockObject).UnrealizedPL >= this.posConfig.fixedProfitValue)
				rtnVal = true;
		} else if (posConfig.sellMode == Constants.TradeMode.VARIABLE) {

		}
		return rtnVal;
	}

	private boolean placeSellOrder() {
		boolean rtnVal = false;
		Order orderObject = null;

		if (this.orderType == Constants.OrderType.MARKET)
			orderObject = new Order(this.stockObject.Symbol,
					this.stockObject.TotalCount, this.currentPrice,
					Constants.OrderAction.SELL, this.orderType);
		else if (this.orderType == Constants.OrderType.LIMIT)
			orderObject = new Order(this.stockObject.Symbol,
					this.stockObject.TotalCount, this.currentPrice,
					Constants.OrderAction.SELL, this.orderType);

		if (exchangeObject.placeOrder(orderObject)) // Service call 5
		{
			if (orderObject.Status == Constants.OrderState.FILLED) {
				// Do this in new thread..
				this.stockObject.TotalCount = orderObject.Count;
				this.stockObject.SellPrice = orderObject.Price;
				this.stockObject.TotalRevenue = (orderObject.Count * orderObject.Price)
						+ orderObject.Brokerage;
				this.stockObject.TotalSellFee = orderObject.Brokerage;
				this.stockObject.ProfitnLoss = orderObject.PnL
						+ orderObject.Brokerage;
				this.stockObject.ModifiedOn = orderObject.DoneAt;
				this.stockObject.Status = Constants.PositionState.CLOSED;
				/*if (UpdatePositionsForDataAnalysis()) {
					DataAnalyzer dt = new DataAnalyzer();
					dt.Symbol = orderObject.Symbol;
					dt.CloseActiveTicker();
				} else
					System.out.println("Error while writing to DB");*/
				rtnVal = true;
			} else if (orderObject.Status == Constants.OrderState.PENDING) {
				System.out.println(new Date() + " : Order status "
						+ Constants.OrderState.PENDING);
			} else if (orderObject.Status == Constants.OrderState.CANCELLED) {
				System.out.println(new Date()+ " : Order status "
						+ Constants.OrderState.CANCELLED);
			}

		}
		return rtnVal;
	}

	private boolean isReadyToAddMore() {
		boolean rtnVal = false;
		if (this.posConfig.sellMode == Constants.TradeMode.FIXED) {
			this.currentPrice = exchangeObject.getCurrentPrice(
					this.stockObject.Symbol, Constants.OrderAction.BUY); // Service
																			// call
																			// 6
																			// -
																			// every
																			// for
																			// loop
			float lastPrice = this.stockObject.TradeList.get(this.stockObject.TradeList.size() - 1).PriceBuy;
			float priceDiff = lastPrice - currentPrice;
			if (priceDiff > this.posConfig.fixedBuyPriceValue)
				rtnVal = true;
		} else if (this.posConfig.sellMode == Constants.TradeMode.VARIABLE) {

		}
		return rtnVal;
	}

	private void addMoreStocks() {
		int count = 0;
		Order orderObject = null;
		if (((ForexPosition) this.stockObject).TradeList.size() < this.posConfig.extrapolateLevel) {

			count = (int) (this.stockObject.TradeList.get(this.stockObject.TradeList.size() - 1).Count + 1);
			// ***************************************************************************************
			// Check enough margin available to buy this units
			float margin = ((OandaAccount) primaryAccountInfo).MarginAvailable;
			float expPrice = count * this.currentPrice;

			if (expPrice < margin) {
				if (this.orderType == Constants.OrderType.MARKET)
					orderObject = new Order(this.stockObject.Symbol, count,
							this.currentPrice, Constants.OrderAction.BUY,
							this.orderType);
				else if (this.orderType == Constants.OrderType.LIMIT)
					orderObject = new Order(this.stockObject.Symbol, count,
							this.currentPrice, Constants.OrderAction.BUY,
							this.orderType);

				if (exchangeObject.placeOrder(orderObject)) // Service call 7
				{
					if (orderObject.Status == "FILLED") {
						System.out.println("Added More "
								+ this.stockObject.Symbol + " at "
								+ this.stockObject.SellPrice);
					} else if (orderObject.Status == "PENDING") {
						System.out.println(new Date() + " : Order status "
								+ Constants.OrderState.PENDING);
					} else if (orderObject.Status == "CANCELLED") {
						System.out.println(new Date() + " : Order status "
								+ Constants.OrderState.CANCELLED);
					}
				} else
					System.out.println("Buy more stocks failed - Please check");
			}
		}
	}

	private void processTrade() {
		if (this.stockObject.TradeList.size() > 1) {
			for (int iPos = this.stockObject.TradeList.size() - 1; iPos > 2; iPos--) {
				this.fxStock = (ForexStock) this.stockObject.TradeList.get(iPos);
				if (this.fxStock.UnrealizedPL >= this.posConfig.fixedProfitValue) {
					if (exchangeObject.closeTrade(this.fxStock)) // Service call
																	// 8 - Every
																	// for loop
																	// and every
																	// trade
																	// loop
					{
						this.fxStock.Cost = (this.fxStock.Count * this.fxStock.PriceBuy)
								+ this.fxStock.FeeBuy;
						this.fxStock.Revenue = (this.fxStock.Count * this.fxStock.PriceSell)
								+ this.fxStock.FeeSell;
					/*	if (DBManager.InsertEntity < ForexStock > (this.FxStock)) {
							System.out.println("Trade ID: "
									+ this.fxStock.TradeId
									+ " Closed, Profit = "
									+ (FxStock.ProfitnLoss + fxStock.FeeSell));
						} else
							System.out.println("Error while writing to DB");*/
					}
				}
			}
		}
	}
}
