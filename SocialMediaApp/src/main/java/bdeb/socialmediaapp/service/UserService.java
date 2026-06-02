package bdeb.socialmediaapp.service;

import bdeb.socialmediaapp.dao.IUserDAO;
import bdeb.socialmediaapp.model.User;
import bdeb.socialmediaapp.util.UserNotFound;

import java.util.List;

public class UserService {
    IUserDAO userDAO;

    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    //Recherche par username via userDAO
    public List<User> findUserByUsername(String username){
        List<User> users = null;
        try{
            users = userDAO.getUsers(username);
            return users;
        }catch(UserNotFound e){
            System.out.println("Error caught in UserService"); //test
            throw new UserNotFound("User not found");
        }
    }

    //Creation d'un user via userDAO
    public void createUser(String username, String password) {
        userDAO.createUser(username, password);
    }

    //Validation d'un user par username ET password via userDAO (principalement pour le login)
    public User validateUser(String username, String password) {
        User user = userDAO.validateUser(username, password);
        if(user == null){
            throw new UserNotFound("User not found");
        }
        return user;
    }

    //Mise a jour de la photo de profil de l'utilisateur
    public void setProfileImage(int userID, String fileName){
        userDAO.updateProfilePictureByID(userID, fileName);
    }
}
