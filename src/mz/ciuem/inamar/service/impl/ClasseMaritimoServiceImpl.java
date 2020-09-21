package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.ClasseMaritimoDao;
import mz.ciuem.inamar.entity.ClasseEmbarcacao;
import mz.ciuem.inamar.entity.ClasseMaritimo;
import mz.ciuem.inamar.entity.GrupoMaritimo;
import mz.ciuem.inamar.service.ClasseMaritimoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("classeMaritimoService")
public class ClasseMaritimoServiceImpl extends GenericServiceImpl<ClasseMaritimo> implements ClasseMaritimoService{

	@Autowired
	private ClasseMaritimoDao _cd;
	
	@Override
	public List<ClasseMaritimo> finByNomeActivo(String nome, boolean isActivo) {
		// TODO Auto-generated method stub
		return _cd.finByNomeActivo(nome, isActivo);
	}

	@Override
	public List<ClasseMaritimo> findByGrupoMaritimo(
			GrupoMaritimo grupoMaritimo) {
		// TODO Auto-generated method stub
		return _cd.findByGrupoMaritimo(grupoMaritimo);
	}

}
