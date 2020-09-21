package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoEtapa;

public interface PeticaoEtapaService extends GenericService<PeticaoEtapa>{
	
	public List<PeticaoEtapa> findByPeticaoLikePerfil(Peticao peticao, String perfil);
	public List<PeticaoEtapa> findByPeticao(Peticao peticao);
	public PeticaoEtapa findByPeticaoLikePerfilEstado(Peticao peticao, String perfil, String estado);

}
