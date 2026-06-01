package bdeb.socialmediaapp.dao;

public class SQL_BOX {

    //USERS TABLE REQUETES
    public static final String CREATE_USER= "INSERT INTO users (username, password) VALUES (?, ?)";
    public static final String FIND_USER_BY_USERNAME= "SELECT * FROM users WHERE username = ?";
    public static final String FIND_USER_BY_USERNAME_AND_PASSWORD= "SELECT * FROM users WHERE username = ? and password = ?";

    //POSTS TABLE REQUETES
    public static final String CREATE_POST= "INSERT INTO posts (user_id, content, image_path) VALUES (?, ?, ?)";
    public static final String FIND_POST_BY_FRIENDS_AND_USER= """
            SELECT p.id, p.user_id, p.content, p.image_path, p.created_at, u.username, u.profile_picture FROM posts AS p
            INNER JOIN users AS u ON p.user_id = u.id
            WHERE p.user_id = ?
            OR
            p.user_id IN(
                SELECT receiver_id AS friend_id FROM friendships
                WHERE requester_id = ?
                AND status='ACCEPTED'
                UNION
                SELECT requester_id AS friend_id FROM friendships
                WHERE receiver_id = ?
                AND status='ACCEPTED'
            )
            ORDER BY p.created_at DESC; 
            """;
    public static final String FIND_POST_BY_ID= """
            SELECT p.id, p.user_id, p.content, p.image_path, p.created_at, u.username, u.profile_picture FROM posts AS p
            INNER JOIN users AS u ON p.user_id = u.id
            WHERE p.user_id = ?
            ORDER BY p.created_at DESC;
            """;
}
