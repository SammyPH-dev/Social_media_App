package bdeb.socialmediaapp.dao;

import bdeb.socialmediaapp.data_access.DatabaseFactory;
import bdeb.socialmediaapp.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post_DAO_MSSQL implements IPostDAO {
    private Connection connection;

    public Post_DAO_MSSQL(){
        this.connection = DatabaseFactory.getInstance().getConnection();
    }

    @Override
    public void createPost(int userID, String content, String image) {
        //Ajoute a la table Posts
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL_BOX.CREATE_POST);
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, content);
            preparedStatement.setString(3, image);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> fetchPosts(int userID) {
        List<Post> posts = new ArrayList<>();
        //Fetch toute les posts du user et de ses amis
        try{
            //Prepare le statement
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL_BOX.FIND_POST_BY_FRIENDS_AND_USER);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, userID);
            preparedStatement.setInt(3, userID);
            //Execute le statment
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                //Construit un Post et l'ajoute a la list
                //p.id, p.user_id, p.content, p.image_path, p.created_at, u.username, u.profile_picture
                int postID = resultSet.getInt("id");
                userID = resultSet.getInt("user_id");
                String content = resultSet.getString("content");
                String image = resultSet.getString("image_path");
                Date createdAt = resultSet.getDate("created_at");
                String username = resultSet.getString("username");
                String profile_picture = resultSet.getString("profile_picture");
                posts.add(new Post(postID, userID, username, content, profile_picture, image, createdAt));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return posts;
    }
}
