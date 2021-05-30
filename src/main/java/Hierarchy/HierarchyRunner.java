package Hierarchy;

import pojos.HibernateUtil;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.Instant;

public class HierarchyRunner {
    private static HibernateUtil hibernateUtil=null;
    private static EntityManager entityManager=null;
    public static void main(String[] args) {
        TablePerClass();
    }

    private static void TablePerClass() {
        hibernateUtil=new HibernateUtil();
        entityManager=hibernateUtil.getEm();
        Task task=new Task(null,"Task1","Description 1");
        WorkTask workTask=WorkTask.builder().id(null)
                .name("WorTask1")
                .description("WorkTaskDescription")
                .cost(100).build();
        HomeTask homeTask=HomeTask.builder().id(null)
                .name("HomeTask")
                .description("HomeTaskDescription")
                .startDate(new Date(2021,5,31))
                .endDate(new Date(2021,6,10))
                .address(new Address("Skryganova","Minsk"))
                .build();
        entityManager.getTransaction().begin();
        entityManager.persist(task);
        entityManager.persist(workTask);
        entityManager.persist(homeTask);
        entityManager.getTransaction().commit();
        hibernateUtil.close();
    }
}
