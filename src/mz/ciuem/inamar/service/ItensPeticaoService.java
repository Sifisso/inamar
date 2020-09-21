package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.ItensPeticao;
import mz.ciuem.inamar.entity.Peticao;

public interface ItensPeticaoService extends GenericService<ItensPeticao>{

	public List<ItensPeticao> findByPeticaoID(Peticao peticao);
}
