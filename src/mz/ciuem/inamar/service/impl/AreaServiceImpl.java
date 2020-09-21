package mz.ciuem.inamar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.AreaDao;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.service.AreaService;

@Service("areaService")
public class AreaServiceImpl extends GenericServiceImpl<Area> implements AreaService{

	@Autowired
	private AreaDao _arDao;
	
	@Override
	public List<Area> findByNomeIsActivoIsAdmar(String nome, boolean isActivo,
			boolean isAdmar) {
		return _arDao.findByNomeIsActivoIsAdmar(nome, isActivo, isAdmar);
	}

}
