package mz.ciuem.inamar.dao.imlp;

import java.util.Collection;
import java.util.List;

import mz.ciuem.inamar.dao.SectorrDao;
import mz.ciuem.inamar.entity.DelegacaoDepartamento;
import mz.ciuem.inamar.entity.Sectorr;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SectorrDaoImpl extends GenericDaoImpl<Sectorr> implements SectorrDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Sectorr> findByNomeIsAdmar(String nome, boolean isAdmar) {
		Query query = getCurrentSession().createQuery("select s from Sectorr s where s.nome like :nome and s.isAdmar=:isAdmar");
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("isAdmar", isAdmar);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<? extends Sectorr> findByDelegacaoDep(DelegacaoDepartamento delegacaoDepartamento, boolean isAdmar) {
		Query query = getCurrentSession().createQuery("select s from Sectorr s where s.isAdmar=:isAdmar and s not in (select sect from Sectorr sect join  sect.delegacaoDepartamentoSectores dds where dds in (select dds2 from DelegacaoDepartamentoSector dds2 join dds2.delegacaoDepartamento dd where dd=:delegacaoDepartamento)) order by s.nome asc");
		//Query query = getCurrentSession().createQuery("select s from Sectorr s where s.isAdmar=:isAdmar and s not in (select dlp from DelegacaoDepartamento dlp join dlp.delegDepSectores  delDepts where delDepts.delegacaoDepartamento =:delegacaoDepartamento) order by s.nome asc");
		query.setParameter("delegacaoDepartamento", delegacaoDepartamento);
		query.setParameter("isAdmar", isAdmar);
		return query.list();
	}

}
