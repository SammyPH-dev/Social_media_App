package bdeb.socialmediaapp.dao;

import bdeb.socialmediaapp.data_access.DatabaseFactory;
import bdeb.socialmediaapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public User getUser(String username) {
        User user = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL_BOX.FIND_USER_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                user = new User(username, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User validateUser(String username, String password) {
        User user = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL_BOX.FIND_USER_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(username, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
