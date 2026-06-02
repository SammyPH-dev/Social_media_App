package bdeb.socialmediaapp.dao;

import bdeb.socialmediaapp.data_access.DatabaseFactory;
import bdeb.socialmediaapp.model.User;
import bdeb.socialmediaapp.util.UserNotFound;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User_DAO_MSSQL implements IUserDAO {
    private Connection connection;

    public User_DAO_MSSQL() {
        this.connection = DatabaseFactory.getInstance().getConnection();
    }

    @Override
    public void createUser(String username, String password) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL_BOX.CREATE_USER);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getUsers(String username) throws UserNotFound {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL_BOX.FIND_USER_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userID = resultSet.getInt("id");
                String profilePicture = resultSet.getString("profile_picture");
                users.add(new User(profilePicture, userID, username));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User validateUser(String username, String password) throws UserNotFound {
        User user = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL_BOX.FIND_USER_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userID = resultSet.getInt("id");
                String profilePicture = resultSet.getString("profile_picture");
                user = new User(userID, username, password, profilePicture);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    //Change la photo de profile du user
    public void updateProfilePictureByID(int userID,String fileName){
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL_BOX.UPDATE_USER_PROFILE_PICTURE_BY_ID);
            preparedStatement.setString(1,fileName);
            preparedStatement.setInt(2,userID);
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.out.println("error while updating profile picture");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
