package bdeb.socialmediaapp.dao;

import bdeb.socialmediaapp.model.User;

import java.sql.SQLException;

public interface IUserDAO {
    public void createUser(String username, String password);
    public User getUser(String username);
    public User validateUser(String username, String password);
}
