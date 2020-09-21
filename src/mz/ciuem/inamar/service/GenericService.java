package mz.ciuem.inamar.service;

import java.util.List;

public interface GenericService<T> {

    public T create(T t);  
    
    public List<T> getAll();

    public T find(Long id);
    
    public T update(T t); 
 
    public long count();
    
    public void delete(T id);
    
    public T first();
    
    public void saveOrUpdate(T t);
    
    public T last();
}