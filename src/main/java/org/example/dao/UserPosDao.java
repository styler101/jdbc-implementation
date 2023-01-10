package org.example.dao;

import org.example.connectionjdbc.SingleConnection;
import org.example.entities.UserPosJava;

import java.rmi.server.ExportException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class UserPosDao {
    // Estamos mantendo na instância do objeto a conexão com o banco de dados.
    private Connection connection;

    public UserPosDao(){
        this.connection = SingleConnection.getConnection();
    }
    // O método ira persistir a nossa entidade no banco
    public void insert(UserPosJava user){
        try{
            String sql = "insert into userposjava(name, email) values(?,?)";
            // Objeto responsável por preparar a instânciação dos memos
            PreparedStatement query = connection.prepareStatement(sql);
            // Coluna 1 - Id
            query.setString(1,user.getName());
            query.setString(2, user.getEmail());
            // executa a query
            query.execute();
            // Salva no banco
            connection.commit();;
        }catch(Exception e) {
            try {
                // reverte a alteração feita.
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    public List<UserPosJava> findAll(){
        List<UserPosJava> list = new ArrayList<UserPosJava>();
        try{
            String sql = "select * from userposjava";
            PreparedStatement statement = connection.prepareStatement(sql);
            // Pega os valores da busca sql.
            ResultSet resultSet =  statement.executeQuery();
            // Enquanto tiver um projeto elemento na tabela.
            while(resultSet.next()){
                // Devemos pegar os valores das colunas com o result
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                UserPosJava userPosJava = new UserPosJava(id, name, email);
               list.add(userPosJava);

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return list;

    }

    public UserPosJava findById(Long id) {
        UserPosJava user = null;
        try {
            String sql = "select * from userposjava where id= "+ id;
            PreparedStatement query = connection.prepareStatement(sql);
            // Executa a query no banco de dados
            ResultSet result = query.executeQuery();
            while(result.next()) {
                Long userId = result.getLong("id");
                String name = result.getString("name");
                String email = result.getString("email");
                user = new UserPosJava(userId, name, email);
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void update(UserPosJava user){
        try{
            // Os simbólos de ? dizem que esse parâmetro será dinamica
            String sql = "update userposjava set name =?, email = ? where id = "+ user.getId();
            PreparedStatement query = connection.prepareStatement(sql);
            // O Atualizar é similar ao cadastrar
            query.setString(1,user.getName());
            query.setString(2, user.getEmail());
            query.execute();
            connection.commit();
        }catch(Exception e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    public void delete(Long id){
        try{
            String sql = "delete from userposjava where id=" + id;
            PreparedStatement statement =  connection.prepareStatement(sql);
            statement.execute();
            connection.commit();

        }catch(Exception e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }


    }
}
