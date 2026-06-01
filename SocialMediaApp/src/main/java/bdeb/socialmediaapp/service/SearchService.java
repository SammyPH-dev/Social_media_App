package bdeb.socialmediaapp.service;

import bdeb.socialmediaapp.dao.IUserDAO;
import bdeb.socialmediaapp.dao.User_DAO_MSSQL;
import bdeb.socialmediaapp.model.User;

import java.util.List;

public class SearchService {
    private IUserDAO userDAO;

    public SearchService(IUserDAO userDAO) {
        this.userDAO =  userDAO;
    }

    public List<User> searchUser(String username) {
        List<User> users = userDAO.getUsers(username);
        return users;
    }
}
