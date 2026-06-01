package bdeb.socialmediaapp.service;

import bdeb.socialmediaapp.dao.IPostDAO;
import bdeb.socialmediaapp.model.Post;

import java.util.List;

public class PostService {
    private IPostDAO postDAO;

    public PostService(IPostDAO postDAO) {
        this.postDAO = postDAO;
    }


    //Creation d'un post sans image
    public void createPost(int userID, String content) {
        postDAO.createPost(userID, content, null);
    }

    public List<Post> getPosts(int userID) {
        List<Post> posts = postDAO.fetchPosts(userID);
        return posts;
    }
}
