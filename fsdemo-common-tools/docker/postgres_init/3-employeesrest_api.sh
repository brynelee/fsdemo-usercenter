    #!/bin/bash

    set -e
    set -u

    psql -v ON_ERROR_STOP=1 --username "postgres" -d "employeesrest_api" <<-EOSQL

      -- ----------------------------
      -- Sequence structure for employees_id_seq
      -- ----------------------------
      DROP SEQUENCE IF EXISTS "public"."employees_id_seq";
      CREATE SEQUENCE "public"."employees_id_seq"
      INCREMENT 1
      MINVALUE  1
      MAXVALUE 2147483647
      START 1
      CACHE 1;
      ALTER SEQUENCE "public"."employees_id_seq" OWNER TO "postgres";

      -- ----------------------------
      -- Sequence structure for posts_id_seq
      -- ----------------------------
      DROP SEQUENCE IF EXISTS "public"."posts_id_seq";
      CREATE SEQUENCE "public"."posts_id_seq"
      INCREMENT 1
      MINVALUE  1
      MAXVALUE 2147483647
      START 1
      CACHE 1;
      ALTER SEQUENCE "public"."posts_id_seq" OWNER TO "postgres";

      -- ----------------------------
      -- Table structure for __diesel_schema_migrations
      -- ----------------------------
      DROP TABLE IF EXISTS "public"."__diesel_schema_migrations";
      CREATE TABLE "public"."__diesel_schema_migrations" (
        "version" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
        "run_on" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
      )
      ;
      ALTER TABLE "public"."__diesel_schema_migrations" OWNER TO "postgres";

      -- ----------------------------
      -- Records of __diesel_schema_migrations
      -- ----------------------------
      BEGIN;
      INSERT INTO "public"."__diesel_schema_migrations" VALUES ('20230213164707', '2023-02-13 18:09:06.752805');
      INSERT INTO "public"."__diesel_schema_migrations" VALUES ('00000000000000', '2023-02-13 18:15:39.181065');
      INSERT INTO "public"."__diesel_schema_migrations" VALUES ('20230213181414', '2023-02-13 18:15:39.189008');
      INSERT INTO "public"."__diesel_schema_migrations" VALUES ('20230218101741', '2023-02-19 03:46:15.693873');
      COMMIT;

      -- ----------------------------
      -- Table structure for employees
      -- ----------------------------
      DROP TABLE IF EXISTS "public"."employees";
      CREATE TABLE "public"."employees" (
        "id" int4 NOT NULL DEFAULT nextval('employees_id_seq'::regclass),
        "first_name" varchar COLLATE "pg_catalog"."default" NOT NULL,
        "last_name" varchar COLLATE "pg_catalog"."default" NOT NULL,
        "department" varchar COLLATE "pg_catalog"."default" NOT NULL,
        "salary" int4 NOT NULL,
        "age" int4 NOT NULL
      )
      ;
      ALTER TABLE "public"."employees" OWNER TO "postgres";

      -- ----------------------------
      -- Records of employees
      -- ----------------------------
      BEGIN;
      INSERT INTO "public"."employees" VALUES (1, 'dahai', 'gao', 'hr', 50000, 45);
      INSERT INTO "public"."employees" VALUES (2, 'xiaoming', 'li', 'cio', 80000, 46);
      INSERT INTO "public"."employees" VALUES (3, 'San', 'Zhang', 'bdepartment', 40000, 38);
      INSERT INTO "public"."employees" VALUES (4, 'XiaoMeng', 'Zhang', 'finance', 25000, 29);
      INSERT INTO "public"."employees" VALUES (5, 'YiMing', 'Wu', 'IT', 28000, 30);
      INSERT INTO "public"."employees" VALUES (6, 'XiaoHong', 'Liu', 'finance', 24000, 29);
      COMMIT;

      -- ----------------------------
      -- Table structure for posts
      -- ----------------------------
      DROP TABLE IF EXISTS "public"."posts";
      CREATE TABLE "public"."posts" (
        "id" int4 NOT NULL DEFAULT nextval('posts_id_seq'::regclass),
        "title" varchar COLLATE "pg_catalog"."default" NOT NULL,
        "body" text COLLATE "pg_catalog"."default" NOT NULL,
        "published" bool NOT NULL DEFAULT false
      )
      ;
      ALTER TABLE "public"."posts" OWNER TO "postgres";

      -- ----------------------------
      -- Records of posts
      -- ----------------------------
      BEGIN;
      INSERT INTO "public"."posts" VALUES (1, 'hello', 'I"m here to study
      ', 't');
      INSERT INTO "public"."posts" VALUES (2, '2nd login', 'I''m here today the 2nd timeeeeeeeeeeeeeeeeeeeeee.

      adding a new data here.
      good to know that.
      ', 't');
      COMMIT;

      -- removed by xiaodong for some issue, refer to fsdemo-emplyservice notes in wiz.cn                 

      -- ----------------------------
      -- Alter sequences owned by
      -- ----------------------------
      ALTER SEQUENCE "public"."employees_id_seq"
      OWNED BY "public"."employees"."id";
      SELECT setval('"public"."employees_id_seq"', 7, true);

      -- ----------------------------
      -- Alter sequences owned by
      -- ----------------------------
      ALTER SEQUENCE "public"."posts_id_seq"
      OWNED BY "public"."posts"."id";
      SELECT setval('"public"."posts_id_seq"', 3, true);

      -- ----------------------------
      -- Primary Key structure for table __diesel_schema_migrations
      -- ----------------------------
      ALTER TABLE "public"."__diesel_schema_migrations" ADD CONSTRAINT "__diesel_schema_migrations_pkey" PRIMARY KEY ("version");

      -- ----------------------------
      -- Primary Key structure for table employees
      -- ----------------------------
      ALTER TABLE "public"."employees" ADD CONSTRAINT "employees_pkey" PRIMARY KEY ("id");

      -- ----------------------------
      -- Primary Key structure for table posts
      -- ----------------------------
      ALTER TABLE "public"."posts" ADD CONSTRAINT "posts_pkey" PRIMARY KEY ("id");
EOSQL
    