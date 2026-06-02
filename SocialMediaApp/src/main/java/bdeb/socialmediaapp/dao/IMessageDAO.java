package bdeb.socialmediaapp.dao;

import bdeb.socialmediaapp.model.Message;

import java.util.List;

public interface IMessageDAO {
    public List<Message> fetchConversationByFriendId(int userId, int friendID);
    public void createMessage(int userId, int friendID, String content);
}
