INSERT INTO
	board (board_id, topic, board_description)
VALUES
	(1, "cats", "abouts cats"),
    (2, "back-end", "study backend"),
    (3, "rest", "sleep is best");

INSERT INTO post (post_id, board_id, title, content, created_at)
VALUES
(1, 1, 'new cat', 'say hello!', NOW()),
(2, 1, 'first post', 'happy new year', NOW()),
(3, 2, 'busy day coding', 'need coffee', NOW()),
(4, 2, 'all done!', 'Hello, World!', NOW());