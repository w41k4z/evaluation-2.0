CREATE OR REPLACE PROCEDURE reinitialize()
LANGUAGE plpgsql
AS $$
BEGIN
    EXECUTE 'DELETE FROM runners_times';
    EXECUTE 'DELETE FROM stage_runners';
    EXECUTE 'DELETE FROM stages';
    EXECUTE 'DELETE FROM runners';
    EXECUTE 'DELETE FROM categories';
    EXECUTE 'DELETE FROM user_roles';
    EXECUTE 'DELETE FROM users';
    EXECUTE 'DELETE FROM roles';
    EXECUTE 'INSERT INTO roles values (1, ''ADMIN'', 0), (2, ''TEAM'', 0)';
    EXECUTE 'INSERT INTO users values (''USR000001'', ''Admin'', ''admin@gmail.com'', ''$2a$10$BgGhMLoUAy0C3zNZPDtaW.jNGjKRJ3Ni136uuuSWwdwX1kYoQEwQW'', 0)';
    EXECUTE 'INSERT INTO user_roles values (DEFAULT, ''USR000001'', 1)';
END;
$$;