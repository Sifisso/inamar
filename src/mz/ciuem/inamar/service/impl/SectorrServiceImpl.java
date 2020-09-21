package mz.ciuem.inamar.service.impl;

import java.util.Collection;
import java.util.List;

import mz.ciuem.inamar.dao.SectorrDao;
import mz.ciuem.inamar.entity.DelegacaoDepartamento;
import mz.ciuem.inamar.entity.Sectorr;
import mz.ciuem.inamar.service.SectorrService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sectorrService")
public class SectorrServiceImpl extends GenericServiceImpl<Sectorr> implements SectorrService{

	@Autowired
	private SectorrDao _sectorrDao;
	
	@Override
	public List<Sectorr> findByNomeIsAdmar(String nome, boolean isAdmar) {
		return _sectorrDao.findByNomeIsAdmar(nome, isAdmar);
	}

	@Override
	public Collection<? extends Sectorr> findByDelegacaoDep(
			DelegacaoDepartamento delegacaoDepartamento, boolean isAdmar) {
		// TODO Auto-generated method stub
		return _sectorrDao.findByDelegacaoDep(delegacaoDepartamento, isAdmar);
	}

	

}
