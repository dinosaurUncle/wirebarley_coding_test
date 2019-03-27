package wirebarley.nation.test;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import wirebarley.nation.service.NationService;
import wirebarley.nation.vo.NationVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:config/beans.xml")
public class NationTest {
	
	protected static Logger logger = Logger.getLogger(NationTest.class.getName());
	@Autowired
	ApplicationContext context;
	
	@Test
	public void test() {
		logger.info("---------------NationTest----------------");
		NationService nationService 
		= (NationService) context.getBean("nationService");		
		List<NationVo> nationList = nationService.getNationList();
		for (NationVo nation: nationList) logger.info(nation.toString()); 
		
		logger.info("---------------/NationTest----------------");
	}
}
