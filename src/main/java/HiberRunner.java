import pojos.Person;

import javax.persistence.EntityManager;


public class HiberRunner {

    public static void main(String[] args) {
        Person person=new Person(null,"Matvei","Vecher",18);
        EntityManager em=HibernateUtil.getEm();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        HibernateUtil.close();


    }
}
