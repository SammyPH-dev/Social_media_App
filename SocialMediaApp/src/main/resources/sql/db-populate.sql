INSERT INTO users (username, password, profile_picture)
VALUES
    ('sammy', 'password123', 'sammy.jpg'),

    ('alice', 'alice123', 'alice.jpg'),

    ('bob', 'bob123', 'bob.jpg'),

    ('charlie', 'charlie123', 'charlie.jpg'),

    ('david', 'david123', 'david.jpg');

INSERT INTO friendships
(requester_id, receiver_id, status)
VALUES
    (1, 2, 'ACCEPTED'),

    (3, 1, 'ACCEPTED'),

    (1, 4, 'PENDING'),

    (5, 1, 'PENDING'),

    (2, 3, 'ACCEPTED');

INSERT INTO posts
(user_id, content, image_path)
VALUES
    (1,
     'Premier poste sur l'application,
     NULL),

    (2,
     'Incroyable',
     NULL),

    (3,
     'TP de fin de session',
     NULL),

    (4,
     'test1234',
     NULL),

    (5,
     'yeah',
     NULL);