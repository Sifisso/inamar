package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.TipoEmbarcacaoDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TipoEmbarcacao;
import mz.ciuem.inamar.service.TipoEmbarcacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tipoEmbarcacaoService")
public class TipoEmbarcacaoServiceImpl extends GenericServiceImpl<TipoEmbarcacao> implements TipoEmbarcacaoService{

	@Autowired
	private TipoEmbarcacaoDao _tDao;
	
	@Override
	public List<TipoEmbarcacao> findNotInPedido(Pedido pedido) {
		return _tDao.findNotInPedido(pedido);
	}

}
