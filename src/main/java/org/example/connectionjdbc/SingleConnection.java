package org.example.connectionjdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
    // Url do banco

    private static String url = "jdbc:postgresql://localhost:5432/posjava";
    // Senha do banco de dados.
    private static String password="root";
    // Usuário do banco
    private static String user="root";
    private static Connection connection = null;

    static{
        connect();
    }
    public SingleConnection(){
        connect();
    }
    private static void connect(){
        try{
            // A conexão com o banco deve ser feita uma única vez
            if(connection == null){
                // faz o carregamento do drive do postgres
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
                // Não vai salvar as ações de insert, update automaticamente.
                connection.setAutoCommit(false);
                System.out.println("Conectou com sucesso!");
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }

    }

    public static Connection getConnection(){
        return connection;
    }


}
