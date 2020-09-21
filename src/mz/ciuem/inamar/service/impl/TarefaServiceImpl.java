package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.TarefaDao;
import mz.ciuem.inamar.entity.EtapaFluxo;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Tarefa;
import mz.ciuem.inamar.service.TarefaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tarefaService")
public class TarefaServiceImpl extends GenericServiceImpl<Tarefa> implements TarefaService{

	@Autowired
	private TarefaDao _tarDao;
	
	@Override
	public List<Tarefa> findByNomeIsActivo(String nome, boolean isActivo) {
		return _tarDao.findByNomeIsActivo(nome, isActivo);
	}

	@Override
	public List<Tarefa> findNotInPedido(Pedido pedido) {
		return _tarDao.findNotInPedido(pedido);
	}

	@Override
	public List<Tarefa> findNotInEtapaFluxo(EtapaFluxo etapaFluxo) {
		return _tarDao.findNotInEtapaFluxo(etapaFluxo);
	}
	

}
