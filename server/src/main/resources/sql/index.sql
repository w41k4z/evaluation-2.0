-- analyze index usage
SELECT
  indexrelid::regclass AS index,
  idx_scan AS number_of_scans,
  idx_tup_read AS tuples_read,
  idx_tup_fetch AS tuples_fetched
FROM
  pg_stat_user_indexes
WHERE
  schemaname = 'public';

-- check for unused index
SELECT
  indexrelid::regclass AS index
FROM
  pg_stat_user_indexes
WHERE
  schemaname = 'public'
  AND idx_scan = 0;
