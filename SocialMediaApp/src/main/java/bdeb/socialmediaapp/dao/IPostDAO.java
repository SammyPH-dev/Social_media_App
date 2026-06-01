package bdeb.socialmediaapp.dao;

import bdeb.socialmediaapp.model.Post;

import java.util.List;

public interface IPostDAO {
    public void createPost(int userID, String content, String image);
    public List<Post> fetchPosts(int userID);
}
