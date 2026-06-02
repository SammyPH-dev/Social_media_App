package bdeb.socialmediaapp.service;

import bdeb.socialmediaapp.dao.Friend_DAO_MSSQL;
import bdeb.socialmediaapp.dao.IFriendDAO;
import bdeb.socialmediaapp.model.User;

import java.util.List;

public class FriendService {
    private IFriendDAO friendDAO;

    public FriendService(IFriendDAO friendDAO) {
        this.friendDAO = friendDAO;
    }

    public List<User> getFriends(int userID) {
        List<User> friends = friendDAO.fetchFriends(userID);
        return friends;
    }
}
