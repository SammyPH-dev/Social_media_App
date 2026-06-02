package bdeb.socialmediaapp.service;

import bdeb.socialmediaapp.dao.IMessageDAO;
import bdeb.socialmediaapp.model.Message;

import java.util.List;

public class MessageService {
    private IMessageDAO messageDAO;

    public MessageService(IMessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }


    public List<Message> getConversationByFriendId(int userId, int friendID) {
        List<Message> conversation = messageDAO.fetchConversationByFriendId(userId, friendID);
        return conversation;
    }

    public void sendMessage(int userId, int friendID, String content) {
        messageDAO.createMessage(userId, friendID, content);
    }
}
