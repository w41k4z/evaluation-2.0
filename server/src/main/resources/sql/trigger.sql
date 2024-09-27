CREATE OR REPLACE FUNCTION log_changes()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'UPDATE') THEN
        NEW.updated_at := CURRENT_TIMESTAMP;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- user, category, point, runner, stage, runner_times, team_penalty, user_role, runner_category, stage_runner
CREATE TRIGGER user_changes
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE PROCEDURE log_changes();

CREATE TRIGGER category_changes
BEFORE UPDATE ON categories
FOR EACH ROW
EXECUTE PROCEDURE log_changes();

CREATE TRIGGER point_changes
BEFORE UPDATE ON points
FOR EACH ROW
EXECUTE PROCEDURE log_changes();

CREATE TRIGGER runner_changes
BEFORE UPDATE ON runners
FOR EACH ROW
EXECUTE PROCEDURE log_changes();

CREATE TRIGGER stage_changes
BEFORE UPDATE ON stages
FOR EACH ROW
EXECUTE PROCEDURE log_changes();

CREATE TRIGGER runner_times_changes
BEFORE UPDATE ON runners_times
FOR EACH ROW
EXECUTE PROCEDURE log_changes();

CREATE TRIGGER user_role_changes
BEFORE UPDATE ON user_roles
FOR EACH ROW
EXECUTE PROCEDURE log_changes();

CREATE TRIGGER runner_category_changes
BEFORE UPDATE ON runner_categories
FOR EACH ROW
EXECUTE PROCEDURE log_changes();

CREATE TRIGGER stage_runners_changes
BEFORE UPDATE ON stage_runners
FOR EACH ROW
EXECUTE PROCEDURE log_changes();

CREATE TRIGGER team_penalty_changes
BEFORE UPDATE ON team_penalty
FOR EACH ROW
EXECUTE PROCEDURE log_changes();

DROP TRIGGER team_penalty_changes ON team_penalty;
DROP TRIGGER stage_runners_changes ON stage_runners;
DROP TRIGGER runner_category_changes ON runner_categories;
DROP TRIGGER user_role_changes ON user_roles;
DROP TRIGGER runner_times_changes ON runners_times;
DROP TRIGGER stage_changes ON stages;
DROP TRIGGER runner_changes ON runners;
DROP TRIGGER point_changes ON points;
DROP TRIGGER category_changes ON categories;
DROP TRIGGER user_changes ON users;
DROP FUNCTION log_changes();