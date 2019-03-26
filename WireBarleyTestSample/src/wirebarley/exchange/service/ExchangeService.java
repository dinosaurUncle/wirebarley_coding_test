package wirebarley.exchange.service;

import java.util.List;

import wirebarley.exchange.vo.ExchangeVO;

public interface ExchangeService {

	public ExchangeVO getExchangeObj(String transferType, String receiptType);
}
