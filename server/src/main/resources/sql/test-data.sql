INSERT INTO roles values
(1, 'ADMIN', 0),
(2, 'USER', 0);

INSERT INTO users values
('USR000001', 'admin@gmail.com', '$2a$10$BgGhMLoUAy0C3zNZPDtaW.jNGjKRJ3Ni136uuuSWwdwX1kYoQEwQW', 0);

INSERT INTO user_roles values
(DEFAULT, 'USR000001', 1);