USE A11_TP
GO;

DROP TABLE users;
DROP TABLE posts;
DROP TABLE friendships;
DROP TABLE messages;

CREATE TABLE users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL,
    profile_picture NVARCHAR(255)
);

CREATE TABLE posts (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    content NVARCHAR(2000),
    image_path NVARCHAR(255),
    created_at DATETIME2 NOT NULL DEFAULT GETDATE(),

    CONSTRAINT FK_posts_users FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE friendships (
    id INT IDENTITY(1,1) PRIMARY KEY,
    requester_id INT NOT NULL,
    receiver_id INT NOT NULL,
    status NVARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at DATETIME2 NOT NULL DEFAULT GETDATE(),

    CONSTRAINT FK_friendships_requester
    FOREIGN KEY (requester_id)
    REFERENCES users(id),

    CONSTRAINT FK_friendships_receiver
    FOREIGN KEY (receiver_id)
    REFERENCES users(id),

    CONSTRAINT CHK_friendships_status
    CHECK (status IN ('PENDING', 'ACCEPTED', 'REJECTED')),

    CONSTRAINT CHK_friendships_not_self
    CHECK (requester_id <> receiver_id)
);

CREATE TABLE messages(
    id INT IDENTITY(1,1) PRIMARY KEY,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    content NVARCHAR(1000) NOT NULL,
    sent_at DATETIME2 NOT NULL DEFAULT GETDATE()
);