package org.example;


import org.example.connectionjdbc.SingleConnection;
import org.example.dao.UserPosDao;
import org.example.entities.UserPosJava;
import org.junit.Test;

import java.util.List;

import java.util.concurrent.atomic.AtomicLong;

public class Main{

    @Test
    public void initBanco(){
        SingleConnection.getConnection();
    }
    @Test
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
        user.setName("Teste");
        user.setEmail("teste");
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
            userPosJava.setId(1L);
            userPosJava.setName("Outro Nome");
            userPosJava.setEmail("outroNome@gmail.com");
            dao.update(userPosJava);
        }catch(RuntimeException e){

            e.printStackTrace();
        }
    }

    @Test
    public void delete(){
        try{
            UserPosDao dao = new UserPosDao();
            dao.delete(2L);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}