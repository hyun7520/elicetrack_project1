INSERT INTO
	board (board_id, topic, board_description)
VALUES
	(1, "cats", "abouts cats"),
    (2, "back-end", "study backend"),
    (3, "rest", "sleep is best");

INSERT INTO post (post_id, board_id, title, content, created_at)
VALUES
(1, 1, 'just adopted cat', 'say hello!', NOW()),
(2, 1, 'first post of new year!', 'happy new year', NOW()),
(3, 2, 'busy day coding', 'need coffee', NOW()),
(4, 2, 'all done!', 'Hello, World!', NOW());

INSERT INTO
    comment (post_id, content, created_at)
VALUES
    (1, "test comment1", NOW()),
    (1, "test comment2", NOW()),
    (2, "test comment3", NOW())