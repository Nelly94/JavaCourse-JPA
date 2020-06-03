package dao;

import java.util.List;

public interface GenericDAO<T, K> {

    public List<T> findAll();

    public T findById(K id);

    public void update(T entity);

    public void delete(T entity);

    public void deleteByKey(K id);

    public void create(T entity);

}
