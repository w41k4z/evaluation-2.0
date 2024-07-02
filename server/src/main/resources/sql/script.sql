-- AUTH
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    state INTEGER NOT NULL
);

CREATE TABLE users (
    id VARCHAR(9) PRIMARY KEY DEFAULT generate_user_id(),
    name VARCHAR(50) NOT NULL UNIQUE,
    username VARCHAR(50) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    state INTEGER NOT NULL
);

CREATE TABLE user_roles (
    id SERIAL PRIMARY KEY,
    users_id VARCHAR(9) REFERENCES users(id) NOT NULL,
    roles_id BIGINT REFERENCES roles(id) NOT NULL,
    CONSTRAINT uq_user_role UNIQUE(users_id, roles_id)
);


-- TABLES

CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    state INTEGER NOT NULL
);

CREATE TABLE points (
    rank INTEGER PRIMARY KEY,
    score INTEGER NOT NULL,
    CHECK(rank > 0 AND score >= 0)
);

CREATE TABLE runners (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    team_id VARCHAR(9) REFERENCES users(id) NOT NULL,
    number VARCHAR(50) NOT NULL,
    gender INTEGER NOT NULL,
    birth_date DATE NOT NULL,
    state INTEGER NOT NULL,
    CONSTRAINT uq_runner UNIQUE (team_id, number)
);

CREATE TABLE runner_categories (
    id SERIAL PRIMARY KEY,
    runner_id BIGINT REFERENCES runners(id) NOT NULL,
    category_id BIGINT REFERENCES categories(id) NOT NULL,
    CONSTRAINT uq_runner_category UNIQUE(runner_id, category_id)
);

CREATE TABLE stages (
    id SERIAL PRIMARY KEY,
    start_date DATE NOT NULL,
    start_time TIME NOT NULL,
    rank INTEGER UNIQUE NOT NULL,
    name VARCHAR(50) NOT NULL,
    path_length DOUBLE PRECISION NOT NULL,
    runners_per_team INTEGER NOT NULL,
    state INTEGER NOT NULL,
    CHECK(rank > 0 AND path_length > 0 AND runners_per_team > 0)
);

CREATE TABLE stage_runners (
    id SERIAL PRIMARY KEY,
    stage_id BIGINT REFERENCES stages(id) NOT NULL,
    runner_id BIGINT REFERENCES runners(id) NOT NULL,
    CONSTRAINT uq_stage_runner UNIQUE (stage_id, runner_id)
);

CREATE TABLE runners_times (
    id SERIAL PRIMARY KEY,
    stage_runners_id BIGINT REFERENCES stage_runners(id) UNIQUE NOT NULL, -- UNIQUE because it is the combination of the runner_id and the stage_id
    arrival_time TIMESTAMP NOT NULL
);

CREATE TABLE team_penalty (
    id SERIAL PRIMARY KEY,
    stage_id BIGINT REFERENCES stages(id) NOT NULL,
    team_id VARCHAR(9) REFERENCES users(id) NOT NULL,
    penalty TIME NOT NULL
);

CREATE TABLE imported_stages (
    rank INTEGER UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    path_length DOUBLE PRECISION NOT NULL,
    runners_per_team INTEGER NOT NULL,
    start_date DATE NOT NULL,
    start_time TIME NOT NULL
);

CREATE TABLE imported_results (
    stage_rank INTEGER NOT NULL,
    runner_number VARCHAR(50) NOT NULL,
    runner_name VARCHAR(100) NOT NULL,
    runner_gender INTEGER NOT NULL,
    runner_birth_date DATE NOT NULL,
    team VARCHAR(50) NOT NULL,
    arrival_time TIMESTAMP NOT NULL
);
