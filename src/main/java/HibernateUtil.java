import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    static EntityManagerFactory em=null;
    static {
        em= Persistence.createEntityManagerFactory("main.java");
    }
    public static EntityManager getEm(){
        return em.createEntityManager();
    }
    public static void close(){
        em.close();
    }
}
