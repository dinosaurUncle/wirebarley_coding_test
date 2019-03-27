package wirebarley.exchange.test;

import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import wirebarley.exchange.service.ExchangeService;
import wirebarley.exchange.vo.ExchangeVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:config/beans.xml")
public class ExchangeTest {
	
	protected static Logger logger = Logger.getLogger(ExchangeTest.class.getName());
	@Autowired
	ApplicationContext context;
	
	@Test
	public void test() {		
		ExchangeService exchangeService 
		= (ExchangeService) context.getBean("exchangeService");		
		ExchangeVO exchangeObj = exchangeService.getExchangeObj("USD", "KRW");
		logger.info(exchangeObj.toString());						
	}
}
