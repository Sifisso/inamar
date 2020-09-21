package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.DelegacaoDepartamentoSectorDao;
import mz.ciuem.inamar.entity.DelegacaoDepartamento;
import mz.ciuem.inamar.entity.DelegacaoDepartamentoSector;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DelegacaoDepartamentoSectorDaoImpl extends GenericDaoImpl<DelegacaoDepartamentoSector> implements DelegacaoDepartamentoSectorDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<DelegacaoDepartamentoSector> findByDelegacaoDepartamento(
			DelegacaoDepartamento delegacaoDepartamento) {
		Query query = getCurrentSession().createQuery("select delDepSect from DelegacaoDepartamentoSector delDepSect JOIN FETCH "
				+ "delDepSect.delegacaoDepartamento delegDep JOIN FETCH delDepSect.sectorr sect JOIN FETCH delegDep.departamento dep where delegDep =:delegacaoDepartamento");
		query.setParameter("delegacaoDepartamento", delegacaoDepartamento);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DelegacaoDepartamentoSector> findByNomeActivo(String nome, boolean activo) {
		Query query = getCurrentSession().createQuery("select delDepSect from DelegacaoDepartamentoSector delDepSect JOIN FETCH delDepSect.delegacaoDepartamento delDep JOIN FETCH delDepSect.sectorr sect where sect.nome like :nome and delDepSect.isActivo =:activo");
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("activo", activo);
		return query.list();
	}

}
