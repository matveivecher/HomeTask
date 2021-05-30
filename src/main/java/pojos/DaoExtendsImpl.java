package pojos;

import java.io.Serializable;
import java.util.List;

public class DaoExtendsImpl<Person,B extends Serializable> extends DaoImpl<Person, B> {
    public DaoExtendsImpl() {
        SessionFactoryUtil sessionFactoryUtil=new SessionFactoryUtil();
    }

    @Override
    public void persist(Person person) {
        super.persist(person);
    }

    @Override
    public void update(Person person) {
        super.update(person);
    }

    @Override
    public Person findById(B b) {
        return super.findById(b);
    }

    @Override
    public void delete(B b) {
        super.delete(b);
    }

    @Override
    public List<Person> findAll(List<Person> list) {
        return super.findAll(list);
    }

    @Override
    public void deleteAll() {
        super.deleteAll();
    }
}