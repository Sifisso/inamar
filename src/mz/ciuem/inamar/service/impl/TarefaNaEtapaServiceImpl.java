package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.TarefaNaEtapaDao;
import mz.ciuem.inamar.entity.EtapaFluxo;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TarefaNaEtapa;
import mz.ciuem.inamar.service.TarefaNaEtapaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tarefaNaEtapaService")
public class TarefaNaEtapaServiceImpl extends GenericServiceImpl<TarefaNaEtapa> implements TarefaNaEtapaService{

	@Autowired
	private TarefaNaEtapaDao _teDao;
	
	@Override
	public List<TarefaNaEtapa> findByPedido(Pedido pedido) {
		return _teDao.findByPedido(pedido);
	}

	@Override
	public List<TarefaNaEtapa> findByEtapaFluxo(EtapaFluxo etapaFluxo) {
		return _teDao.findByEtapaFluxo(etapaFluxo);
	}

}
