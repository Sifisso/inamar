package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.IdEntity;

public interface GenericDao<T extends IdEntity> {
	
	public long count();

    public T create(T t);

    public void delete(T id);

    public T find(Object id);
    
    public List<T> getAll();

    public T update(T t); 
    
    public void saveOrUpdate(T t); 

    public T first();
    
    public T last();
}