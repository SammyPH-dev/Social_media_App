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
     'Welcome to SocialApp! This is my first post.',
     NULL),

    (2,
     'Beautiful weather today in Montreal.',
     'montreal.jpg'),

    (3,
     'Just finished my Java assignment.',
     NULL),

    (4,
     'Anyone interested in joining a hockey pool?',
     NULL),

    (5,
     'Vacation picture from Quebec City.',
     'quebec.jpg');