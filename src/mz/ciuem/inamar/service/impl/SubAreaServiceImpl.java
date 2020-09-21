package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.SubAreaDao;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.SubArea;
import mz.ciuem.inamar.service.SubAreaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("subAreaService")
public class SubAreaServiceImpl extends GenericServiceImpl<SubArea> implements SubAreaService{

	@Autowired
	private SubAreaDao _subAreaDao;
	
	@Override
	public List<SubArea> findByNomeActivo(String nome, boolean isActivo) {
		return _subAreaDao.findByNomeActivo(nome, isActivo);
	}

	@Override
	public List<SubArea> findByArea(Area area) {
		return _subAreaDao.findByArea(area);
	}

}
