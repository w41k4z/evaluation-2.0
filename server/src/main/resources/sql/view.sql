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

CREATE VIEW team_runners AS
SELECT
    row_number() OVER (
        ORDER BY stage_runners.stage_id ASC
    ) AS id,
    stage_id,
    users.id AS team_id,
    runner_id
FROM stage_runners
JOIN runners
    ON runners.id = stage_runners.runner_id
JOIN users
    ON users.id = runners.team_id
;

CREATE VIEW ranking AS
SELECT
    DENSE_RANK() OVER (
        PARTITION BY stage_runners.stage_id
        ORDER BY (runners_times.end_time - runners_times.start_time) ASC
    ) AS rank,
    stage_runners.runner_id,
    stage_runners.stage_id,
    runners_times.start_time,
    runners_times.end_time
FROM runners_times
JOIN stage_runners
    ON stage_runners.id = runners_times.stage_runners_id
ORDER BY
    stage_runners.stage_id ASC,
    rank ASC
;

CREATE VIEW general_ranking AS
SELECT
    ROW_NUMBER() OVER (
        ORDER BY (SELECT 1)
    ) AS id,
    ranking.rank,
    ranking.runner_id,
    ranking.stage_id,
    ranking.start_time,
    ranking.end_time,
    COALESCE(points.score, 0) AS score
FROM ranking
LEFT JOIN points
    ON points.rank = ranking.rank
ORDER BY
    ranking.stage_id ASC,
    rank ASC
;

CREATE VIEW team_ranking AS
SELECT
    ROW_NUMBER() OVER (
        ORDER BY (SELECT 1)
    ) AS id,
    DENSE_RANK() OVER (
        ORDER BY SUM(final_ranking.total_score) DESC
    ) AS rank,
    final_ranking.team_id,
    SUM(final_ranking.total_score) AS total_score
FROM (
    SELECT
        users.id AS team_id,
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
        0
    FROM teams
) AS final_ranking
GROUP BY
    final_ranking.team_id
;
