package bdeb.socialmediaapp.model;

public class User {
    private String username;
    private String password;
    private String profilePicture;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() { return profilePicture; }

    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + '}';
    }
}
