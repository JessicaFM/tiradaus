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

INSERT INTO roles (id, name, description) VALUES
(1, 'admin', 'Administrator with full access'),
(2, 'user', 'Regular user with limited access');

INSERT INTO users (user_name, first_name, last_name, email, password, is_active, role_id) VALUES
('admin', 'Admin Name', 'Admin Last Name', 'admin@example.com', '$2b$10$9Y8mgvz0/SoltKts.JI0s.6W8QbvrMN/kZiYVbj0frTG3eT/H3Gre', TRUE, 1);
