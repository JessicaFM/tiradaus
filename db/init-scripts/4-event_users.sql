CREATE TABLE event_users (
  event_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (event_id, user_id),
  FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT INTO event_users (event_id, user_id)
VALUES
  (1, 2),
  (1, 3);

INSERT INTO event_users (event_id, user_id)
VALUES
  (2, 2);

INSERT INTO event_users (event_id, user_id)
VALUES
  (3, 2),
  (3, 3);

INSERT INTO event_users (event_id, user_id)
VALUES
  (4, 1),
  (4, 2),
  (4, 3);

INSERT INTO event_users (event_id, user_id)
VALUES
  (5, 2);

INSERT INTO event_users (event_id, user_id)
VALUES
  (6, 1);

INSERT INTO event_users (event_id, user_id)
VALUES
  (7, 2),
  (7, 3);

INSERT INTO event_users (event_id, user_id)
VALUES
  (8, 2);
