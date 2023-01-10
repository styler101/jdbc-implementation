package org.example;


import org.example.connectionjdbc.SingleConnection;
import org.junit.Test;

public class Main{
    @Test
    public void initBanco(){
        SingleConnection.getConnection();
    }

}