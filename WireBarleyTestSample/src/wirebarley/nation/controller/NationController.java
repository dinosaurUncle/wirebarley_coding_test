package wirebarley.nation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import wirebarley.nation.service.NationService;
import wirebarley.nation.vo.NationVo;

@Controller
public class NationController {
	
	@Autowired
	private NationService nationService;
	
	@RequestMapping(value="/nations", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getNationList(){
		List<NationVo> nationList = nationService.getNationList();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("nationList", nationList);
		return result;
	}
}
