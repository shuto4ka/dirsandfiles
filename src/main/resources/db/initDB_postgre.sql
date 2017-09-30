DROP TABLE IF EXISTS paths;
DROP TABLE IF EXISTS snapshots;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE snapshots
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  date_time        TIMESTAMP    NOT NULL,
  dir              VARCHAR      NOT NULL,
  dirs_count       INTEGER      NOT NULL,
  files_count      INTEGER      NOT NULL,
  total_size       BIGINT       NOT NULL
);
CREATE UNIQUE INDEX snapshots_unique_date_time_dir_idx ON snapshots (date_time, dir);

CREATE TABLE paths
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR      NOT NULL,
  size             BIGINT,
  snapshot_id      INTEGER      NOT NULL,
  FOREIGN KEY (snapshot_id) REFERENCES snapshots (id) ON DELETE CASCADE
);
