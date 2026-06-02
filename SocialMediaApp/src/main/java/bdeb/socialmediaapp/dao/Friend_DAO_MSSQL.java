package bdeb.socialmediaapp.dao;

import bdeb.socialmediaapp.data_access.DatabaseFactory;
import bdeb.socialmediaapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Friend_DAO_MSSQL implements IFriendDAO {
    private Connection connection;

    public Friend_DAO_MSSQL(){
        this.connection = DatabaseFactory.getInstance().getConnection();
    }

    public List<User> fetchFriends(int userID){
        List<User> friends = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_BOX.FIND_FRIENDS_BY_USER_ID);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String username = resultSet.getString("username");
                String profilePicture = resultSet.getString("profile_picture");
                int friendID = resultSet.getInt("id");
                User user = new User(profilePicture, friendID, username);
                friends.add(user);
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return friends;
    }
}
