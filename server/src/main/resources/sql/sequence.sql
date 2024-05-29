CREATE SEQUENCE users_id_seq START WITH 2;
CREATE OR REPLACE FUNCTION generate_user_id() RETURNS VARCHAR AS $$
DECLARE
    seq_val BIGINT;
BEGIN
    SELECT nextval('users_id_seq') INTO seq_val;
    RETURN 'USR' || LPAD(seq_val::TEXT, 6, '0'); -- Adjust 6 as needed to match your desired length
END;
$$ LANGUAGE plpgsql;