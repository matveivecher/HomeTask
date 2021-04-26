package HomeTask21_04;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    static final String[] names={"Oleg","Nikita","Klim","Max","Dasha","Katya","Sasha","Liza"};
    static final String[] surnames = {"Gutor", "Cheryshev", "Kalinovski", "Maevski", "Martynovich", "Milevski", "Klemantovich", "Rakut", "Tibets"};
    static final String[] streets={"Slobod","Marks","Grove","Broad","Manchetan"};
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
       List<Address> addressList=new ArrayList<>();
       List<Man> manList=new ArrayList<>();
       DAOAddress daoAddress=new DAOAddress();
       PeopleDAO daoMan=new PeopleDAO();
       for (int i = 1; i <6 ; i++) {
            addressList.add(new Address(i,streets[(int)(Math.random()* streets.length)],i));
        }
        for (int i = 1; i <6 ; i++) {
            manList.add(new Man(i,names[(int)(Math.random()* names.length)],surnames[(int)(Math.random()* surnames.length)],i));
        }
        for (Man m:manList) {
            daoMan.save(m);
        }
        for (Address a:addressList) {
            daoAddress.save(a);
        }
        Address address=null;
        for (int i = 4; i <6 ; i++) {
             address=daoAddress.get(i);
            address.setStreet(address.getStreet()+2);
            daoAddress.update(address,i);
        }
        Man man=null;
        for (int i = 4; i <6; i++) {
            man=daoMan.get(i);
            man.setAge(man.getAge()+2);
            daoMan.update(man,i);
        }
        daoAddress.delete(1);
        daoMan.delete(1);
    }
}
