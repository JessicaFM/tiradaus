CREATE EXTENSION IF NOT EXISTS pgcrypto;


DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;

CREATE TABLE roles (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) UNIQUE NOT NULL,
  description TEXT
);

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  user_name VARCHAR(100) NOT NULL,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password TEXT NOT NULL,
  is_active BOOLEAN DEFAULT TRUE,
  role_id INT NOT NULL DEFAULT 2,
  last_login TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE RESTRICT
);

CREATE INDEX IF NOT EXISTS idx_users_role_id ON users(role_id);

INSERT INTO roles (id, name, description) VALUES
(1, 'ADMIN', 'Administrator with full access'),
(2, 'USER', 'Regular user with limited access')
ON CONFLICT (id) DO NOTHING;

INSERT INTO users (id, user_name, first_name, last_name, email, password, is_active, role_id) 
VALUES (
  1,
  'admin', 
  'Admin Name', 
  'Admin Last Name', 
  'admin@example.com', 
  crypt('admin1234', gen_salt('bf', 10)), 
  TRUE, 
  1
)
ON CONFLICT (id) DO NOTHING;

INSERT INTO users (id, user_name, first_name, last_name, email, password, is_active, role_id)
VALUES (
  2,
  'test',
  'Test',
  'User',
  'test@example.com',
  crypt('test1234', gen_salt('bf', 10)),
  TRUE,
  2
)
ON CONFLICT (id) DO NOTHING;
