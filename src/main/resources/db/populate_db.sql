DELETE FROM paths;
DELETE FROM snapshots;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO snapshots (date_time, dir, dirs_count, files_count, total_size) VALUES
  ('2017-09-30 10:00:00', '/dir1', 1, 2, 1),
  ('2017-09-30 13:00:00', '/dir1/dir2', 0, 1, 10000);