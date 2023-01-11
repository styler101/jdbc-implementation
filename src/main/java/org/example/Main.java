package org.example;


import org.example.connectionjdbc.SingleConnection;
import org.example.dao.PhoneDao;
import org.example.dao.UserPosDao;
import org.example.entities.Phone;
import org.example.entities.UserPhone;
import org.example.entities.UserPosJava;
import org.junit.Test;

import java.util.List;

public class Main{


    public void initBanco(){
        SingleConnection.getConnection();
    }

    public void selectAll(){
        UserPosDao userPosDao = new UserPosDao();
        List<UserPosJava> list = userPosDao.findAll();
        for(UserPosJava user:list){
            System.out.println(user);
        }
    }


    public void insert(){
        UserPosDao dao = new UserPosDao();
        UserPosJava user =  new UserPosJava();
        user.setName("Lucas");
        user.setEmail("lucas@gmail.com");
        dao.insert(user);
    }



    public void findById(){
        try{
            UserPosDao userPosDao = new UserPosDao();
            UserPosJava user = userPosDao.findById(2L);
            System.out.println(user);
        }catch(Exception e) {
         e.printStackTrace();
        }
    }


    public void update(){
        try{
            UserPosDao dao = new UserPosDao();
            UserPosJava userPosJava = new UserPosJava();
            // para não começar com zero
            userPosJava.setName("Lucas Santos");
            userPosJava.setEmail("Lucas@gmail.com");
            dao.update(userPosJava);
        }catch(RuntimeException e){

            e.printStackTrace();
        }
    }


    public void delete(){
        try{
            UserPosDao dao = new UserPosDao();
            dao.delete(2L);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void insertUserPhone(){
        try{
            PhoneDao dao = new PhoneDao();
            Phone phone = new Phone();
            phone.setId(4L);
            phone.setNumber("(31)XXXX-XXXX");
            phone.setUser_id(4L);
            phone.setType("Fixo 2");
            dao.insert(phone);
        }catch(Exception e ){
            e.printStackTrace();
        }
    }

    @Test
    public void listByJoin(){
        try{
          PhoneDao dao = new PhoneDao();
          List<UserPhone> list = dao.joinTables(1L, 4L);
          for(UserPhone item:list){
              System.out.println(item);
          }

        }catch(Exception e){
            e.printStackTrace();
        }
    }


}