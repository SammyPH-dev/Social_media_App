package bdeb.socialmediaapp.dao;

import bdeb.socialmediaapp.data_access.DatabaseFactory;
import bdeb.socialmediaapp.model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Message_DAO_MSSQL implements IMessageDAO {
    private Connection connection;

    public Message_DAO_MSSQL() {
        this.connection = DatabaseFactory.getInstance().getConnection();
    }

    public List<Message> fetchConversationByFriendId(int userId, int friendID) {
        List<Message> conversation = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_BOX.FIND_CONVERSATION);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, friendID);
            preparedStatement.setInt(3, friendID);
            preparedStatement.setInt(4, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               int id = resultSet.getInt("id");
               int receiverId = resultSet.getInt("receiver_id");
               int senderId = resultSet.getInt("sender_id");
               String content = resultSet.getString("content");
               String sentAt = resultSet.getTimestamp("sent_at").toLocalDateTime().toString();
               String senderUsername = resultSet.getString("sender_username");
               Message message = new Message(id, senderId, receiverId, content, sentAt);
               message.setSenderUsername(senderUsername);
               message.setSentAt(sentAt);
               conversation.add(message);
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return conversation;
    }

    public void createMessage(int userId, int friendID, String content){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_BOX.CREATE_MESSAGE);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, friendID);
            preparedStatement.setString(3, content);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
