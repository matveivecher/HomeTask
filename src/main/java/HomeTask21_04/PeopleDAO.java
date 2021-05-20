package HomeTask21_04;

import java.io.Serializable;
import java.sql.*;

public class PeopleDAO implements DAO<Man> {
    String userName = "Matvei";
    String password = "1234";
    String connectionUrl = "jdbc:mysql://localhost:3306/03_05";
    PreparedStatement preparedStatement;
    Connection connection = null;

    @Override
    public Man save(Man man) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(connectionUrl, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection == null) connection.close();
        }
        String sql = "INSERT INTO 03_05.people(id,name,surname,age) " + "VALUES(?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, man.getId());
        preparedStatement.setString(2, man.getName());
        preparedStatement.setString(3, man.getSurname());
        preparedStatement.setInt(4, man.getAge());
        preparedStatement.executeUpdate();
        return man;
    }

    @Override
    public Man get(int id) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(connectionUrl, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection == null) connection.close();
        }
        String sql = "select *from 03_05.people where id = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        Man man = null;
        while (resultSet.next()) {
            man = new Man(id, resultSet.getString("name"), resultSet.getString("surname"), resultSet.getInt(4));
        }
        return man;
    }

    @Override
    public void update(Man man, int id) throws SQLException {
        String sql = "UPDATE 03_05.people SET id=(?),name=(?),surname=(?),age=(?) where id = " + id;
        connection = DriverManager.getConnection(connectionUrl, userName, password);
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, man.getId());
        preparedStatement.setString(2, man.getName());
        preparedStatement.setString(3, man.getSurname());
        preparedStatement.setInt(4, man.getAge());
        preparedStatement.executeUpdate();
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "DELETE FROM 03_05.people where id = "+id;
        connection=DriverManager.getConnection(connectionUrl,userName,password);
        Statement statement=connection.createStatement();
        statement.executeUpdate(sql);
        System.out.println("Удалено");
        return 1;
    }
}
