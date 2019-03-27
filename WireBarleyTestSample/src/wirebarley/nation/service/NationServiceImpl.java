package wirebarley.nation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wirebarley.nation.dao.NationDAO;
import wirebarley.nation.vo.NationVo;

@Service("nationService")
public class NationServiceImpl implements NationService {

	@Autowired
	NationDAO nationDAO;
	
	@Override
	public List<NationVo> getNationList() {
		return nationDAO.getNationList();
	}

}
