package wirebarley.nation.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import wirebarley.nation.vo.NationVo;

@Component
public class NationDAO {
	
	public List<NationVo> getNationList(){
		List<NationVo> nationList = new ArrayList<NationVo>();				
		nationList.add(new NationVo(new Integer(1).longValue(), "미국", "USD", "trans"));
		nationList.add(new NationVo(new Integer(2).longValue(), "호주", "AUD", "trans"));
		nationList.add(new NationVo(new Integer(3).longValue(), "한국", "KRW", "receip"));
		nationList.add(new NationVo(new Integer(4).longValue(), "일본", "JPY", "receip"));
		nationList.add(new NationVo(new Integer(5).longValue(), "필리핀", "PHP", "receip"));
		return nationList;
	}
}
