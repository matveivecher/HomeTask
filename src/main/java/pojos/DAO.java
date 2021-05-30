package pojos;


import java.io.Serializable;
import java.util.List;

public interface DAO<T,Id extends Serializable> {
    public void persist(T entity);

    public void update(T entity);

    public T findById(Id id);

    public void delete(Id id);

    public List<T> findAll(List<T> list);

    public void deleteAll();
}
