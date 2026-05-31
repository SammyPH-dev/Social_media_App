package bdeb.socialmediaapp.service;

import bdeb.socialmediaapp.dao.IUserDAO;
import bdeb.socialmediaapp.model.User;
import bdeb.socialmediaapp.util.UserNotFound;

public class UserService {
    IUserDAO userDAO;

    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User findUserByUsername(String username){
        User user = null;
        try{
            user = userDAO.getUser(username);
            return user;
        }catch(UserNotFound e){
            System.out.println("Error caught in UserService");
            throw new UserNotFound("User not found");
        }
    }

    public void createUser(String username, String password) {
        userDAO.createUser(username, password);
    }

    public User validateUser(String username, String password) {
        User user = userDAO.validateUser(username, password);
        if(user == null){
            throw new UserNotFound("User not found");
        }
        return user;
    }
}
