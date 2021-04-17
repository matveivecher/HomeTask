package HomeTask17_04_21;

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class Task1 {
    static String[] address = {"Slobodskay", "Skryganova", "Kosmonavtov", "Rafieva", "Esenina"};
    static String[] names = {"Oleg", "Dima", "Klim", "Ilya", "Vova", "Alex", "Nikita", "Katya", "Sasha", "Dasha"};
    static String[] surnames = {"Gutor", "Cheryshev", "Kalinovski", "Maevski", "Martynovich", "Milevski", "Klemantovich", "Rakut", "Tibets"};

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        List<Person> people = new ArrayList<>();
        addRandomPersonInList(people);
        people.stream()
                .filter((x) -> x.getAge() < 21)
                .forEach(System.out::println);
        System.out.println("-----------------------------------------------------------------------------------");
        people.stream()
                .sorted(Comparator.comparing(Person::getName).thenComparing(Person::getSurname));
        Set<Person> uniquePersons = new HashSet<>(people);
        File file = new File("persons.txt");
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            uniquePersons.stream()
                    .forEach((x) -> {
                        try {
                            oos.writeObject(x);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                System.out.println(ois.readObject());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> strings = new ArrayList<>();
        uniquePersons.stream()
                .forEach((x) -> strings.add(x.getSurname() + " " + x.getName()));
        String userName = "Matvei";
        String password = "1234";
        String connectionUrl = "jdbc:mysql://localhost:3306/task17_04_21";
        Connection connection = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            System.out.println("We are connected!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection == null) connection.close();
        }
        DatabaseMetaData metaData = connection.getMetaData();
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO persons (id,name,surname,age,salary,passport,address,dateOfBirthday,dateTimeCreate,timeToLaunch)  " +
                " VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        for (Person p : uniquePersons) {
            ps.setInt(1, p.getId());
            ps.setString(2, p.getName());
            ps.setString(3, p.getSurname());
            ps.setInt(4, p.getAge());
            ps.setInt(5, p.getSalary());
            ps.setString(6, p.getPassport());
            ps.setString(7, p.getAddress());
            ps.setString(8, p.getDateOfBirthday());
            ps.setDate(9, p.getDateTimeCreate());
            ps.setDate(10, Date.valueOf(p.getTimeToLaunch()));
            ps.executeUpdate();
        }
    }

    public static String randomDateOfBirth() {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randomBeetwen(1980, 1990);
        gc.set(gc.YEAR, year);
        int month = randomBeetwen(1, 12);
        gc.set(gc.MONTH, month);
        int day = randomBeetwen(1, 31);
        gc.set(gc.DAY_OF_MONTH, day);
        String birthday = gc.get(gc.DAY_OF_MONTH) + "." + gc.get(gc.MONTH) + "." + gc.get(gc.YEAR);
        return birthday;
    }

    public static int randomBeetwen(int start, int finish) {
        return start + (int) (Math.random() * (finish - start + 1));
    }

    public static String randomPassport() {
        String[] array = {"M", "A", "B", "P", "H", "E", "D", "K"};
        String random = array[(int) (Math.random() * array.length)] + array[(int) (Math.random() * array.length)];
        for (int i = 0; i < 7; i++) {
            int x = (int) (Math.random() * 10);
            random = random.concat(Integer.toString(x));
        }
        return random;
    }

    public static void addRandomPersonInList(List people) {
        String str = null;
        String[] array = null;
        int age = 0;
        for (int i = 1; i <= 100; i++) {
            str = randomDateOfBirth();
            array = str.split("\\.");
            if (Integer.parseInt(array[0]) <= 17 && Integer.parseInt(array[1]) <= 4) {
                age = 2021 - Integer.parseInt(array[2]);
            } else {
                age = 2020 - Integer.parseInt(array[2]);
            }
            LocalDate launchDay = LocalDate.of(2021, Month.APRIL, (int) (Math.random() * 14 + 13));
            people.add(new Person(i, names[(int) (Math.random() * names.length)], surnames[(int) (Math.random() * surnames.length)], age, (int) (Math.random() * 600 + 600), randomPassport(), (address[(int) (Math.random() * address.length)] + "," + String.valueOf((int) (Math.random() * 101))), str, new Date(System.currentTimeMillis()), launchDay));
        }
    }
}


