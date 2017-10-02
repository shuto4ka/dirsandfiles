DELETE FROM paths;
DELETE FROM snapshots;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO snapshots (date_time, dir, dirs_count, files_count, total_size) VALUES
  ('2017-09-30 10:00:00', '/opt/tomcat/temp', 12, 251, 134217728),
  ('2017-09-30 13:00:00', '/opt/tomcat/temp', 12, 51, 262144);

INSERT INTO paths (name, size, snapshot_id) VALUES
  ('innerTemp', null, 100000),
  ('X-FILES', null, 100000),
  ('f.txt', 4383, 100000),
  ('F1.txt', 12872, 100000),
  ('f4_99.JPG', 1593836, 100000),
  ('F4_00127.pdf', 923116, 100000),
  ('f0008.doc', 26634, 100000),
  ('function.cpp', 3656, 100000)