INSERT INTO roles values (1, 'ADMIN', 0), (2, 'TEAM', 0);

INSERT INTO users values ('USR000001', 'Admin', 'admin@gmail.com', '$2a$10$BgGhMLoUAy0C3zNZPDtaW.jNGjKRJ3Ni136uuuSWwdwX1kYoQEwQW', 0);

INSERT INTO user_roles values (DEFAULT, 'USR000001', 1);

-- TEST --
INSERT INTO users values 
('USR000002', 'Team 1', 'team1@gmail.com', '$2a$10$kLxOmnN6qlomkjxod.793eNgtcP2GzOHrSGJ37n/LN2WRc/U9pYDG', 0),
('USR000003', 'Team 2', 'team2@gmail.com', '$2a$10$n6QNtxbkTXlEfLKjL1e3VuwdMAMWM8ySAT3j6B/MBW86ao558p2CK', 0);

INSERT INTO user_roles values (DEFAULT, 'USR000002', 2), (DEFAULT, 'USR000003', 2);

INSERT INTO categories values (DEFAULT, 'Homme', 0), (DEFAULT, 'Femme', 0), (DEFAULT, 'Junior', 0), (DEFAULT, 'Senior', 0);

INSERT INTO points values (1, 10), (2, 6), (3, 4), (4, 2), (5, 1);

INSERT INTO runners values
(DEFAULT, 'Runner 1', 'USR000002', 1, 1, '1990-01-01', 0),
(DEFAULT, 'Runner 2', 'USR000002', 2, 0, '1990-01-02', 0),
(DEFAULT, 'Runner 3', 'USR000003', 1, 1, '1990-01-03', 0);

INSERT INTO stages values
(DEFAULT, 1, 'Betsizaraina', 10.0, 2, 0),
(DEFAULT, 2, 'Ampasimbe', 20.0, 2, 0);