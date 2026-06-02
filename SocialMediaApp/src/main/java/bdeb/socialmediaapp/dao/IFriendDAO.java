package bdeb.socialmediaapp.dao;

import bdeb.socialmediaapp.model.User;

import java.util.List;

public interface IFriendDAO {
    public List<User> fetchFriends(int userID);
}
