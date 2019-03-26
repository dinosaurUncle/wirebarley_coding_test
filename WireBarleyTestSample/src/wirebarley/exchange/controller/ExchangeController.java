package wirebarley.exchange.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import wirebarley.exchange.service.ExchangeService;
import wirebarley.exchange.vo.ExchangeVO;

@Controller
public class ExchangeController {
	
	@Autowired
	private ExchangeService exchangeService;
	
	@RequestMapping(value="/exchange/{transferType}/{receiptType}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getExchangeList(@PathVariable String transferType, @PathVariable String receiptType){
		ExchangeVO exchangeObj = exchangeService.getExchangeObj(transferType, receiptType);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("exchangeObj", exchangeObj);
		return result;
	}
    

}
