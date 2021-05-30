package pojos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {
    static SessionFactory sessionFactory = null;
    private static Session session;
    public SessionFactoryUtil() {
        sessionFactory=new Configuration().configure().buildSessionFactory();
        session=sessionFactory.openSession();
        session.beginTransaction();
            }
            public static Session getSession(){
        return session;
            }

//    public static void repeatCreate() {
//        if (getSession() == null)
//            new SessionFactoryUtil();
//
//    }
//
//    public static Session getSession() {
//                return sessionFactory.openSession();
//    }

    public static void closeSession() {
        sessionFactory.close();
    }
}
