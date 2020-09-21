package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.PeticaoMaritimoTaxaPedidoDao;
import mz.ciuem.inamar.entity.PeticaoEmbarcacao;
import mz.ciuem.inamar.entity.PeticaoMaritimo;
import mz.ciuem.inamar.entity.PeticaoMaritimoTaxaPedido;
import mz.ciuem.inamar.service.PeticaoMaritimoTaxaPedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("peticaoMaritimoTaxaPedidoService")
public class PeticaoMaritimoTaxaPedidoServiceImpl extends GenericServiceImpl<PeticaoMaritimoTaxaPedido> implements PeticaoMaritimoTaxaPedidoService{

	@Autowired
	private PeticaoMaritimoTaxaPedidoDao _pDao;
	
	@Override
	public List<PeticaoMaritimoTaxaPedido> findByPeticaoMaritimo(
			PeticaoMaritimo peticaoMaritimo) {
		return _pDao.findByPeticaoMaritimo(peticaoMaritimo);
	}

	@Override
	public List<PeticaoMaritimoTaxaPedido> findByPeticaoEmbarcacao(
			PeticaoEmbarcacao peticaoEmbarcacao) {
		return _pDao.findByPeticaoEmbarcacao(peticaoEmbarcacao);
	}

}
