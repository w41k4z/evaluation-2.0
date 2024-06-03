CREATE OR REPLACE PROCEDURE reinitialize()
LANGUAGE plpgsql
AS $$
BEGIN
    EXECUTE 'DELETE FROM runners_times';
    EXECUTE 'DELETE FROM stage_runners';
    EXECUTE 'DELETE FROM stages';
    EXECUTE 'DELETE FROM runner_categories';
    EXECUTE 'DELETE FROM runners';
    EXECUTE 'DELETE FROM points';
    EXECUTE 'DELETE FROM categories';
    EXECUTE 'DELETE FROM user_roles';
    EXECUTE 'DELETE FROM users';
    EXECUTE 'DELETE FROM roles';
    EXECUTE 'ALTER SEQUENCE categories_id_seq RESTART WITH 1';
    EXECUTE 'ALTER SEQUENCE runner_categories_id_seq RESTART WITH 1';
    EXECUTE 'ALTER SEQUENCE runners_id_seq RESTART WITH 1';
    EXECUTE 'ALTER SEQUENCE runners_times_id_seq RESTART WITH 1';
    EXECUTE 'ALTER SEQUENCE stage_runners_id_seq RESTART WITH 1';
    EXECUTE 'ALTER SEQUENCE stages_id_seq RESTART WITH 1';
    EXECUTE 'ALTER SEQUENCE user_roles_id_seq RESTART WITH 1';
    EXECUTE 'ALTER SEQUENCE users_id_seq RESTART WITH 2';
    EXECUTE 'INSERT INTO roles values (1, ''ADMIN'', 0), (2, ''TEAM'', 0)';
    EXECUTE 'INSERT INTO users values (''USR000001'', ''Admin'', ''admin@gmail.com'', ''$2a$10$BgGhMLoUAy0C3zNZPDtaW.jNGjKRJ3Ni136uuuSWwdwX1kYoQEwQW'', 0)';
    EXECUTE 'INSERT INTO user_roles values (DEFAULT, ''USR000001'', 1)';
    EXECUTE 'INSERT INTO categories values (DEFAULT, ''Homme'', 0), (DEFAULT, ''Femme'', 0), (DEFAULT, ''Junior'', 0), (DEFAULT, ''Senior'', 0)';
END;
$$;

CREATE OR REPLACE PROCEDURE import_stages()
LANGUAGE plpgsql
AS $$
BEGIN
    EXECUTE 'INSERT INTO stages(rank, start_date, start_time, name, path_length, runners_per_team, state) SELECT imported_stages.rank, imported_stages.start_date, imported_stages.start_time, imported_stages.name, imported_stages.path_length, imported_stages.runners_per_team, 0 FROM imported_stages ON CONFLICT DO NOTHING';
    EXECUTE 'TRUNCATE TABLE imported_stages';
END;
$$;

CREATE OR REPLACE PROCEDURE import_results()
LANGUAGE plpgsql
AS $$
BEGIN
    EXECUTE 'INSERT INTO users(name, username, password, state) SELECT imported_results.team, imported_results.team, ''$2a$10$OS2.ees3cO863WoBDICoE.to6LI4ZT5R6cPLyyCF2CLs8.oMm2i/O'', 0 FROM imported_results ON CONFLICT DO NOTHING';
    EXECUTE 'INSERT INTO user_roles(users_id, roles_id) SELECT users.id, 2 FROM imported_results JOIN users ON imported_results.team = users.username ON CONFLICT DO NOTHING';
    EXECUTE 'INSERT INTO runners(name, team_id, number, gender, birth_date, state) SELECT imported_results.runner_name, users.id, imported_results.runner_number, imported_results.runner_gender, imported_results.runner_birth_date, 0 FROM imported_results JOIN users ON users.name = imported_results.team ON CONFLICT DO NOTHING';
    EXECUTE 'INSERT INTO stage_runners(stage_id, runner_id) SELECT stages.id, runners.id FROM imported_results JOIN stages ON stages.rank = imported_results.stage_rank JOIN runners ON runners.name = imported_results.runner_name AND runners.number = imported_results.runner_number ON CONFLICT DO NOTHING';
    EXECUTE 'INSERT INTO runners_times(stage_runners_id, arrival_time) SELECT stage_runners.id, imported_results.arrival_time FROM imported_results JOIN stages ON stages.rank = imported_results.stage_rank JOIN runners ON runners.name = imported_results.runner_name AND runners.number = imported_results.runner_number JOIN stage_runners ON stages.id = stage_runners.stage_id AND runners.id = stage_runners.runner_id';
    EXECUTE 'TRUNCATE TABLE imported_results';
END;
$$;
