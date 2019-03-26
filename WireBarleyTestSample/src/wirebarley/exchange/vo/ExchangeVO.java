package wirebarley.exchange.vo;

public class ExchangeVO {

	private String exchangeName;
	private double exchangeValue;
	
	public ExchangeVO() {}
	
	public ExchangeVO(String exchangeName, double exchangeValue) {
		this.exchangeName = exchangeName;
		this.exchangeValue = exchangeValue;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public double getExchangeValue() {
		return exchangeValue;
	}

	public void setExchangeValue(double exchangeValue) {
		this.exchangeValue = exchangeValue;
	}

	@Override
	public String toString() {
		return "ExchangeVO [exchangeName=" + exchangeName + ", exchangeValue=" + exchangeValue + "]";
	}
	
	
	
	
	
}
