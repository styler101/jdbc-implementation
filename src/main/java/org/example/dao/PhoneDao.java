package org.example.dao;

import org.example.connectionjdbc.SingleConnection;
import org.example.entities.Phone;
import org.example.entities.UserPhone;


import java.sql.ResultSet;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhoneDao {
    private Connection connection;
    public PhoneDao(){
        connection = SingleConnection.getConnection();
    }

    public void insert(Phone phone) {
        try {
            String sqlQuery = "insert into userphones(id, number, type, user_id) values(?,?,?,?)";
            PreparedStatement query = connection.prepareStatement(sqlQuery);
            query.setLong(1,phone.getId());
            query.setString(2, phone.getNumber());
            query.setString(3, phone.getType());
            query.setLong(4, phone.getUser_id());
            query.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }
    

    public List<UserPhone> joinTables(Long phone_id, Long user_id){
        List<UserPhone> list = new ArrayList<UserPhone>();
        String sql = "select phones.id, users.name, users.email, phones.number, phones.type, phones.user_id  from userphones as phones  inner join userposjava as users on users.user_id  = ?" + phone_id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                String userName = result.getString("users.name");
                String phone = result.getString("phones.numbers");
                String type = result.getString("phones.types");
                String email = result.getString("users.email");
                UserPhone user = new UserPhone(userName, phone, type, email);
                list.add(user);

            }
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}


