package bdeb.socialmediaapp.dao;

public class SQL_BOX {

    //USERS TABLE REQUETES
    public static final String CREATE_USER= "INSERT INTO users (username, password) VALUES (?, ?)";
    public static final String FIND_USER_BY_USERNAME= "SELECT * FROM users WHERE username = ?";
    public static final String FIND_USER_BY_USERNAME_AND_PASSWORD= "SELECT * FROM users WHERE username = ? and password = ?";
    public static final String UPDATE_USER_PROFILE_PICTURE_BY_ID= "UPDATE users SET profile_picture = ? WHERE id = ?";

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

    //FRIENDSHIP TABLE REQUETES
    public static final String FIND_FRIENDS_BY_USER_ID = """
            SELECT * FROM users
            WHERE id IN(
                SELECT receiver_id AS friend_id FROM friendships
                WHERE requester_id = ?
                AND status='ACCEPTED'
                UNION
                SELECT requester_id AS friend_id FROM friendships
                WHERE receiver_id = ?
                AND status='ACCEPTED'
            )
            """;

    //Messages Table REQUETES
    public static final String FIND_CONVERSATION = """
            SELECT m.id, m.sender_id, m.receiver_id, m.content, m.sent_at, u.username AS sender_username FROM messages m
            INNER JOIN users u ON m.sender_id = u.id
            WHERE
                (m.sender_id = ? AND m.receiver_id = ?)
                OR
                (m.sender_id = ? AND m.receiver_id = ?)
            ORDER BY m.sent_at ASC
            """;

    public static final String CREATE_MESSAGE = """
           INSERT INTO messages (sender_id, receiver_id, content)
           VALUES (?, ?, ?)
            """;
}
