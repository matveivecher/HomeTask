package HomeTask17_04_21;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Person implements Serializable {
    private int id;
    private String name;
    private String surname;
    private int age;
    private double salary;
    private String passport;
    private String address;
    private String dateOfBirtday;
    private Date dateTimeCreate;
    private LocalDate timeToLaunch;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return (int) salary;
    }

    public String getPassport() {
        return passport;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfBirthday() {
        return dateOfBirtday;
    }

    public Date getDateTimeCreate() {
        return dateTimeCreate;
    }

    public LocalDate getTimeToLaunch() {
        return timeToLaunch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                ", salary=" + salary +
                ", passport='" + passport + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirtday=" + dateOfBirtday +
                ", dateTimeCreate=" + dateTimeCreate +
                ", timeToLaunch=" + timeToLaunch +
                '}';
    }

    public Person(int id, String name, String surname, int age, double salary, String passport, String address, String dateOfBirtday, Date dateTimeCreate, LocalDate timeToLaunch) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.salary = salary;
        this.passport = passport;
        this.address = address;
        this.dateOfBirtday = dateOfBirtday;
        this.dateTimeCreate = dateTimeCreate;
        this.timeToLaunch = timeToLaunch;
    }
}
