package wirebarley.nation.vo;

public class NationVo {
	private Long id;
	private String name;
	private String exchangeValue;
	private String exchangeType;
	
	public NationVo(Long id, String name, String exchangeValue, String exchangeType) {
		this.id = id;
		this.name = name;
		this.exchangeValue = exchangeValue;
		this.exchangeType = exchangeType;
	}

	public NationVo() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExchangeValue() {
		return exchangeValue;
	}

	public void setExchangeValue(String exchangeValue) {
		this.exchangeValue = exchangeValue;
	}

	public String getExchangeType() {
		return exchangeType;
	}

	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}

	@Override
	public String toString() {
		return "NationVo [id=" + id + ", name=" + name + ", exchangeValue=" + exchangeValue + ", exchangeType="
				+ exchangeType + "]";
	}
	
	
	
	
	
	
	

}
