package mz.ciuem.inamar.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import mz.ciuem.inamar.dao.GenericDao;
import mz.ciuem.inamar.entity.IdEntity;
import mz.ciuem.inamar.service.GenericService;

@Transactional
abstract class GenericServiceImpl<T extends IdEntity> implements
		GenericService<T> {

	
	@Autowired
	private GenericDao<T> specificDao;
	
	
	public T create(T t) {
		return specificDao.create(t);
	}

	
	public List<T> getAll() {
		return specificDao.getAll();
	}

	
	public T find(Long id) {
		return specificDao.find(id);
	}

	
	public T update(T t) {
		t.setUpdated(new Date());
		return specificDao.update(t);
	}

	
	public long count() {
		return specificDao.count();
	}

	
	public T first() {
		return specificDao.first();
	}
	
	
	public T last() {
		return specificDao.last();
	}
	
	
	public void delete(T id) {
		specificDao.delete(id);
	}
	
	public void saveOrUpdate(T t){
		
		specificDao.saveOrUpdate(t);
	}
}