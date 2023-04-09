    #!/bin/bash

    set -e
    set -u

    # 必须使用''来使用EOSQL，否则执行失败
    # 必须使用""来引用变量信息，比如postgres, employeesrest_api

    psql -v ON_ERROR_STOP=1 --username "postgres" <<-EOSQL

      CREATE DATABASE employeesrest_api
          WITH
          OWNER = postgres
          ENCODING = 'UTF8'
          LC_COLLATE = 'en_US.utf8'
          LC_CTYPE = 'en_US.utf8'
          TABLESPACE = pg_default
          CONNECTION LIMIT = -1;

      GRANT ALL PRIVILEGES ON DATABASE employeesrest_api TO postgres;

      CREATE DATABASE mediandb
        WITH
        OWNER = postgres
        ENCODING = 'UTF8'
        LC_COLLATE = 'en_US.utf8'
        LC_CTYPE = 'en_US.utf8'
        TABLESPACE = pg_default
        CONNECTION LIMIT = -1;

      GRANT ALL PRIVILEGES ON DATABASE mediandb TO postgres;
EOSQL


