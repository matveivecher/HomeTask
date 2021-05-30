package pojos;

public class Runner {
    public static void main(String[] args) {
        //DaoImpl dao=new DaoImpl();
        DaoExtendsImpl daoExtends=new DaoExtendsImpl();
        Person person=new Person(null,"name2","surname2",20);
        //dao.persist(person);
        //daoExtends.persist(person);
        Person person1= (Person) daoExtends.findById(5);
        System.out.println(person1);

    }
}
