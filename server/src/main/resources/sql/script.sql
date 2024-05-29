-- AUTH
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    state INTEGER NOT NULL
);

CREATE TABLE users (
    id VARCHAR(9) PRIMARY KEY DEFAULT generate_user_id(),
    username VARCHAR(50) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    state INTEGER NOT NULL
);

CREATE TABLE user_roles (
    id SERIAL PRIMARY KEY,
    users_id VARCHAR(9) REFERENCES users(id) NOT NULL,
    roles_id INTEGER REFERENCES roles(id) NOT NULL
);


-- TABLES