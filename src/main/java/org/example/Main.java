package org.example;


import org.example.connectionjdbc.SingleConnection;
import org.example.dao.UserPosDao;
import org.example.entities.UserPosJava;
import org.junit.Test;

import java.util.List;
public class Main{
    @Test
    public void initBanco(){
        SingleConnection.getConnection();
        UserPosDao userPosDao = new UserPosDao();

        userPosDao.findAll();

    }

    public void selectAll(){
        UserPosDao userPosDao = new UserPosDao();
        List<UserPosJava> list = userPosDao.findAll();
        for(UserPosJava user:list){
            System.out.println(user);
        }
    }

    @Test
    public void findById(){
        try{
            UserPosDao userPosDao = new UserPosDao();
            UserPosJava user = userPosDao.findById(2L);
            System.out.println(user);
        }catch(Exception e) {
         e.printStackTrace();
        }
    }

}