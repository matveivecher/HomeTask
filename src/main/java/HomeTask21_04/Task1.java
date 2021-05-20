package HomeTask21_04;

import com.google.protobuf.JavaType;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Task1 {
    static Connection  connection;
    static String userName="Matvei";
    static String passsword="1234";
    static String url="jdbc:mysql://localhost:3306/03_05";
    static PreparedStatement preparedStatement;
    static Statement statement;
    static ResultSet resultSet;
    public static void executePreparedStatement(String sql, Map<Integer, Man> param) throws SQLException {
        try {
            connection=DriverManager.getConnection(url,userName,passsword);
            connection.setAutoCommit(false);

            preparedStatement=connection.prepareStatement(sql);
            for (Man man:param.values()) {
                preparedStatement.setInt(1,man.getId());
                preparedStatement.setString(2,man.getName());
                preparedStatement.setString(3,man.getSurname());
                preparedStatement.setInt(4,man.getAge());
                preparedStatement.executeUpdate();
                connection.commit();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            connection.rollback();
            System.out.println("Выполнен откат");
        }
    }
    public static Man executeResultSet(String sql, Man man){
        Class clas=man.getClass();
        Field[] fields=clas.getDeclaredFields();
        Type[] types=clas.getTypeParameters();
        man=null;
        try {
            connection=DriverManager.getConnection(url,userName,passsword);
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){

                man=new Man(resultSet.getInt(String.valueOf(fields[0].getName())),resultSet.getString(String.valueOf(fields[1].getName())),resultSet.getString(String.valueOf(fields[2].getName())),resultSet.getInt(String.valueOf(fields[3].getName())));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return man;
    }
    public static void main(String[] args) {
//        Map<Integer,Man> map=new HashMap<>();
//        for (int i = 1; i <=10; i++) {
//            map.put(i,new Man(i,"name"+i,"surname"+i,(int)(Math.random()*16)+15));
//        }
//        String sql="INSERT INTO 03_05.people (id,name,surname,age)"+"VALUES(?,?,?,?)";
//        try {
//            executePreparedStatement(sql,map);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        Man man=new Man(1,"asdas","asdasd",231);
//        System.out.println(executeResultSet("SELECT *FROM 03_05.people where id=2",man));
        try {
            connection=DriverManager.getConnection(url,userName,passsword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            CallableStatement callableStatement=connection.prepareCall("{call insertName1(?,name)}");
            callableStatement.setInt(1,2);
            callableStatement.registerOutParameter(2,Types.VARCHAR);
            callableStatement.execute();
            ResultSet resultSet=callableStatement.getResultSet();
                            System.out.println(resultSet.getString("name"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    }

