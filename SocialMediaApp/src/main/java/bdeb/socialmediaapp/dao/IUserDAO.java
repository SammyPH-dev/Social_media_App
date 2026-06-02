package bdeb.socialmediaapp.dao;

import bdeb.socialmediaapp.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    public void createUser(String username, String password);
    public List<User> getUsers(String username);
    public User validateUser(String username, String password);
    public void updateProfilePictureByID(int userID, String fileName);
}
