package HomeTask21_04;

import java.io.Serializable;
import java.sql.SQLException;

public interface DAO<T> {
    T save(T t) throws SQLException, ClassNotFoundException;
    T get(int id) throws SQLException, ClassNotFoundException;
    void update(T t,int id) throws SQLException;
    int delete(int id) throws SQLException;
}
