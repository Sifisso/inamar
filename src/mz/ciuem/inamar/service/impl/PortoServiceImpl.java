package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.PortoDao;
import mz.ciuem.inamar.entity.Porto;
import mz.ciuem.inamar.service.PortoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("portoService")
public class PortoServiceImpl extends GenericServiceImpl<Porto> implements PortoService{

	@Autowired
	private PortoDao _portoDao;

	@Override
	public List <Porto> findBydescricaoActivo (String descricao, boolean isActivo) {
		
		return _portoDao.findBydescricaoActivo(descricao, isActivo);
	}
	
}