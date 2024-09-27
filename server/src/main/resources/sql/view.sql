CREATE VIEW teams AS
SELECT
    users.*
FROM users
JOIN user_roles
    ON user_roles.users_id = users.id
JOIN roles
    ON roles.id = user_roles.roles_id
WHERE roles.name = 'TEAM'
;

CREATE VIEW team_penalties AS
SELECT
    stage_id,
    team_id,
    SUM(penalty) AS penalty
FROM team_penalty
GROUP BY
    stage_id,
    team_id
;

CREATE VIEW team_runners AS
SELECT
    row_number() OVER (
        ORDER BY (SELECT 1)
    ) AS id,
    stage_runners.stage_id,
    users.id AS team_id,
    runner_id,
    (runners_times.arrival_time - (stages.start_date + stages.start_time)) AS chrono,
    (runners_times.arrival_time - (stages.start_date + stages.start_time)) + COALESCE(team_penalties.penalty, '00:00:00') AS final_chrono,
    COALESCE(team_penalties.penalty, '00:00:00') AS penalty
FROM stage_runners
LEFT JOIN runners_times
    ON runners_times.stage_runners_id = stage_runners.id
JOIN stages
    ON stages.id = stage_runners.stage_id
JOIN runners
    ON runners.id = stage_runners.runner_id
JOIN users
    ON users.id = runners.team_id
LEFT JOIN team_penalties
    ON team_penalties.team_id = users.id AND team_penalties.stage_id = stages.id
;

-- runner ranking per stage (no category)
CREATE VIEW ranking AS
SELECT
    DENSE_RANK() OVER (
        PARTITION BY stage_runners.stage_id
        ORDER BY (runners_times.arrival_time - (stages.start_date + stages.start_time) + COALESCE(team_penalties.penalty, '00:00:00')) ASC
    ) AS rank,
    stage_runners.stage_id,
    stage_runners.runner_id,
    runners_times.arrival_time - (stages.start_date + stages.start_time) AS chrono,
    runners_times.arrival_time - (stages.start_date + stages.start_time) + COALESCE(team_penalties.penalty, '00:00:00') AS final_chrono,
    COALESCE(team_penalties.penalty, '00:00:00') AS penalty
FROM runners_times
JOIN stage_runners
    ON stage_runners.id = runners_times.stage_runners_id
JOIN stages
    ON stages.id = stage_runners.stage_id
JOIN runners
    ON runners.id = stage_runners.runner_id
JOIN users
    ON users.id = runners.team_id
LEFT JOIN team_penalties
    ON team_penalties.team_id = users.id AND team_penalties.stage_id = stages.id
ORDER BY
    stage_runners.stage_id ASC,
    rank ASC
;

-- runner ranking per stage (with category)
CREATE VIEW category_ranking AS
SELECT
    DENSE_RANK() OVER (
        PARTITION BY stage_runners.stage_id, runner_categories.category_id
        ORDER BY (runners_times.arrival_time - (stages.start_date + stages.start_time) + COALESCE(team_penalties.penalty, '00:00:00')) ASC
    ) AS rank,
    stage_runners.stage_id,
    stage_runners.runner_id,
    runner_categories.category_id,
    runners_times.arrival_time - (stages.start_date + stages.start_time) AS chrono,
    runners_times.arrival_time - (stages.start_date + stages.start_time) + COALESCE(team_penalties.penalty, '00:00:00') AS final_chrono,
    COALESCE(team_penalties.penalty, '00:00:00') AS penalty
FROM runners_times
JOIN stage_runners
    ON stage_runners.id = runners_times.stage_runners_id
JOIN stages
    ON stages.id = stage_runners.stage_id
JOIN runners
    ON runners.id = stage_runners.runner_id
JOIN runner_categories
    ON runner_categories.runner_id = runners.id
JOIN users
    ON users.id = runners.team_id
LEFT JOIN team_penalties
    ON team_penalties.team_id = users.id AND team_penalties.stage_id = stages.id
ORDER BY
    stage_runners.stage_id ASC,
    runner_categories.category_id ASC,
    rank ASC
;

-- runner ranking per stage (no category, with score)
-- ranked by their chrono
CREATE VIEW general_ranking AS
SELECT
    ROW_NUMBER() OVER (
        ORDER BY (SELECT 1)
    ) AS id,
    ranking.rank,
    ranking.runner_id,
    ranking.stage_id,
    ranking.chrono,
    ranking.final_chrono,
    ranking.penalty,
    COALESCE(points.score, 0) AS score
FROM ranking
LEFT JOIN points
    ON points.rank = ranking.rank
ORDER BY
    ranking.stage_id ASC,
    rank ASC
;

-- runner ranking per stage (with category, with score)
-- ranked by their chrono
CREATE VIEW general_category_ranking AS
SELECT
    ROW_NUMBER() OVER (
        ORDER BY (SELECT 1)
    ) AS id,
    category_ranking.stage_id,
    category_ranking.category_id,
    category_ranking.rank,
    category_ranking.runner_id,
    category_ranking.chrono,
    category_ranking.final_chrono,
    category_ranking.penalty,
    COALESCE(points.score, 0) AS score
FROM category_ranking
LEFT JOIN points
    ON points.rank = category_ranking.rank
ORDER BY
    category_ranking.stage_id ASC,
    category_ranking.category_id ASC,
    rank ASC
;

-- global runner ranking (all stages, no category)
-- ranked by their score
CREATE VIEW runner_ranking AS
SELECT
    ROW_NUMBER() OVER (
        ORDER BY (SELECT 1)
    ) AS id,
    DENSE_RANK() OVER (
        ORDER BY SUM(score) DESC
    ) AS rank,
    runner_id,
    SUM(score) AS total_score,
    SUM(chrono) AS total_chrono,
    SUM(final_chrono) AS total_final_chrono,
    SUM(penalty) AS total_penalty
FROM general_ranking
GROUP BY
    runner_id
ORDER BY
    rank ASC
;

-- global runner ranking (all stages, with category)
-- ranked by their score
CREATE VIEW runner_category_ranking AS
SELECT
    ROW_NUMBER() OVER (
        ORDER BY (SELECT 1)
    ) AS id,
    DENSE_RANK() OVER (
        PARTITION BY category_id
        ORDER BY SUM(score) DESC
    ) AS rank,
    category_id,
    runner_id,
    SUM(score) AS total_score,
    SUM(chrono) AS total_chrono,
    SUM(final_chrono) AS total_final_chrono,
    SUM(penalty) AS total_penalty
FROM general_category_ranking
JOIN runners
    ON runners.id = runner_id
JOIN categories
    ON categories.id = category_id
GROUP BY
    category_id,
    runner_id
ORDER BY
    category_id,
    rank ASC
;

-- global team ranking (all stages, no category)
-- ranked by score
CREATE VIEW team_ranking AS
SELECT
    ROW_NUMBER() OVER (
        ORDER BY (SELECT 1)
    ) AS id,
    DENSE_RANK() OVER (
        ORDER BY SUM(final_ranking.total_score) DESC
    ) AS rank,
    final_ranking.team_id,
    SUM(final_ranking.total_chrono) AS total_chrono,
    SUM(final_ranking.total_final_chrono) AS total_final_chrono,
    SUM(final_ranking.total_penalty) AS total_penalty,
    SUM(final_ranking.total_score) AS total_score
FROM (
    SELECT
        users.id AS team_id,
        SUM(COALESCE(general_ranking.chrono, '00:00:00')) AS total_chrono,
        SUM(COALESCE(general_ranking.final_chrono, '00:00:00')) AS total_final_chrono,
        SUM(COALESCE(general_ranking.penalty, '00:00:00')) AS total_penalty,
        SUM(COALESCE(general_ranking.score, 0)) AS total_score
    FROM general_ranking
    JOIN runners
        ON runners.id = general_ranking.runner_id
    JOIN users
        ON users.id = runners.team_id
    GROUP BY
        users.id
    UNION
    SELECT
        id,
        '00:00:00',
        '00:00:00',
        '00:00:00',
        0
    FROM teams
) AS final_ranking
GROUP BY
    final_ranking.team_id
;

-- global team ranking details (all stages, no category)
-- ranked by score
CREATE VIEW team_ranking_details AS
SELECT
    ROW_NUMBER() OVER (
        ORDER BY (SELECT 1)
    ) AS id,
    DENSE_RANK() OVER (
        ORDER BY SUM(final_ranking.total_score) DESC
    ) AS rank,
    final_ranking.team_id,
    final_ranking.stage_id,
    SUM(final_ranking.total_chrono) AS total_chrono,
    SUM(final_ranking.total_final_chrono) AS total_final_chrono,
    SUM(final_ranking.total_penalty) AS total_penalty,
    SUM(final_ranking.total_score) AS total_score
FROM (
    SELECT
        users.id AS team_id,
        general_ranking.stage_id,
        SUM(COALESCE(general_ranking.chrono, '00:00:00')) AS total_chrono,
        SUM(COALESCE(general_ranking.final_chrono, '00:00:00')) AS total_final_chrono,
        SUM(COALESCE(general_ranking.penalty, '00:00:00')) AS total_penalty,
        SUM(COALESCE(general_ranking.score, 0)) AS total_score
    FROM general_ranking
    JOIN runners
        ON runners.id = general_ranking.runner_id
    JOIN users
        ON users.id = runners.team_id
    GROUP BY
        users.id,
        general_ranking.stage_id
) AS final_ranking
GROUP BY
    final_ranking.team_id,
    final_ranking.stage_id
ORDER BY
    final_ranking.stage_id
;

-- global team ranking (all stages, with category)
-- ranked by score
CREATE VIEW team_category_ranking AS
SELECT
    ROW_NUMBER() OVER (
        ORDER BY (SELECT 1)
    ) AS id,
    DENSE_RANK() OVER (
        PARTITION BY final_ranking.category_id
        ORDER BY final_ranking.total_score DESC
    ) AS rank,
    final_ranking.team_id,
    final_ranking.category_id,
    final_ranking.total_chrono,
    final_ranking.total_final_chrono,
    final_ranking.total_penalty,
    final_ranking.total_score
FROM (
    SELECT
        users.id AS team_id,
        general_category_ranking.category_id,
        SUM(COALESCE(general_category_ranking.chrono, '00:00:00')) AS total_chrono,
        SUM(COALESCE(general_category_ranking.final_chrono, '00:00:00')) AS total_final_chrono,
        SUM(COALESCE(general_category_ranking.penalty, '00:00:00')) AS total_penalty,
        SUM(COALESCE(general_category_ranking.score, 0)) AS total_score
    FROM general_category_ranking
    JOIN runners
        ON runners.id = general_category_ranking.runner_id
    JOIN users -- team
        ON users.id = runners.team_id
    GROUP BY
        users.id,
        general_category_ranking.category_id
    ORDER BY
        general_category_ranking.category_id
) AS final_ranking
;

CREATE VIEW team_all_category_ranking AS
SELECT
    ROW_NUMBER() OVER (
        ORDER BY (SELECT 1)
    ) AS id,
    DENSE_RANK() OVER (
        ORDER BY SUM(total_score) DESC
    ) AS rank,
    team_id,
    SUM(total_chrono) AS total_chrono,
    SUM(total_final_chrono) AS total_final_chrono,
    SUM(total_penalty) AS total_penalty,
    SUM(total_score) AS total_score
FROM team_category_ranking
GROUP BY
    team_id
;
