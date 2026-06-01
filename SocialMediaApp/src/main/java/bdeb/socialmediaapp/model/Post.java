package bdeb.socialmediaapp.model;

import java.util.Date;

public class Post {
    private int postID;
    private int userID;
    private String username;
    private String content;
    private String profilePicture;
    private String fileName;
    private Date createdAt;

    public Post(){}

    public Post(int userID, String content, String fileName) {
        this.userID = userID;
        this.content = content;
        this.createdAt = new Date();
        if(fileName != null) {
            this.fileName = fileName;
        }
    }

    public Post(int postID, int userID, String username, String content, String profilePicture, String fileName, Date createdAt) {
        this.postID = postID;
        this.userID = userID;
        this.content = content;
        this.createdAt = createdAt;
        this.profilePicture = profilePicture;
        this.fileName = fileName;
        this.username = username;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Post{" +
                "PostID=" + postID +
                ", userID=" + userID +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", image='" + fileName + '\'' +
                ", date=" + createdAt +
                '}';
    }
}
