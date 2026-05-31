package bdeb.socialmediaapp.dao;

public class SQL_BOX {

    //USERS TABLE REQUETES
    public static final String CREATE_USER= "INSERT INTO users (username, password) VALUES (?, ?)";
    public static final String FIND_USER_BY_USERNAME= "SELECT * FROM users WHERE username = ?";
    public static final String FIND_USER_BY_USERNAME_AND_PASSWORD= "SELECT * FROM users WHERE username = ? and password = ?";
}
