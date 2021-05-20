package HomeTask21_04;

import java.sql.*;

public class DAOAddress implements DAO<Address>{
    Connection connection=null;
    String userName="Matvei";
    String password="1234";
    String connectionURL="jdbc:mysql://localhost:3306/03_05";
    PreparedStatement preparedStatement=null;
    Statement statement=null;
    @Override
    public Address save(Address address) throws SQLException, ClassNotFoundException {
         String sql="INSERT INTO 03_05.address(id,street,house)"+" VALUES(?,?,?)";
         connection= DriverManager.getConnection(connectionURL,userName,password);
         preparedStatement=connection.prepareStatement(sql);
         preparedStatement.setInt(1,address.getId());
         preparedStatement.setString(2,address.getStreet());
         preparedStatement.setInt(3,address.getHouse());
         preparedStatement.executeUpdate();
        System.out.println(address+" сохранено!");
        return null;
    }

    @Override
    public Address  get(int id) throws SQLException, ClassNotFoundException {
        String sql="SELECT *FROM 03_05.address where id = "+id;
        connection=DriverManager.getConnection(connectionURL,userName,password);
        statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        Address address=null;
        while (resultSet.next()){
            address=new Address(resultSet.getInt("id"),resultSet.getString("street"),resultSet.getInt("house"));
        }

        return address;
    }

    @Override
    public void update(Address address, int id) throws SQLException {
       String sql="UPDATE 03_05.address SET id=(?),street=(?),house=(?) where id = "+id   ;
       connection=DriverManager.getConnection(connectionURL,userName,password);
       preparedStatement=connection.prepareStatement(sql);
       preparedStatement.setInt(1,address.getId());
       preparedStatement.setString(2,address.getStreet());
       preparedStatement.setInt(3,address.getHouse());
       preparedStatement.executeUpdate();
        System.out.println("Обновлено");
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql="DELETE from 03_05.address where id = "+id;
        connection=DriverManager.getConnection(connectionURL,userName,password);
        statement= connection.createStatement();
        statement.executeUpdate(sql);
        System.out.println("Удалено");
        return 0;
    }
}
