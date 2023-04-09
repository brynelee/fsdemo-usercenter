    #!/bin/bash

    set -e
    set -u

    psql -v ON_ERROR_STOP=1 --username "postgres" --dbname "mediandb" <<-EOSQL

    
      -- ----------------------------
      -- Sequence structure for Article_id_seq
      -- ----------------------------
      DROP SEQUENCE IF EXISTS "Article_id_seq";
      CREATE SEQUENCE "Article_id_seq" 
      INCREMENT 1
      MINVALUE  1
      MAXVALUE 2147483647
      START 1
      CACHE 1;

      -- ----------------------------
      -- Table structure for Article
      -- ----------------------------
      DROP TABLE IF EXISTS "Article";
      CREATE TABLE "Article" (
        "id" int4 NOT NULL DEFAULT nextval('"Article_id_seq"'::regclass),
        "title" text COLLATE "pg_catalog"."default" NOT NULL,
        "description" text COLLATE "pg_catalog"."default",
        "body" text COLLATE "pg_catalog"."default" NOT NULL,
        "published" bool NOT NULL DEFAULT false,
        "createdAt" timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
        "updatedAt" timestamp(3) NOT NULL
      )
      ;

      -- ----------------------------
      -- Records of Article
      -- ----------------------------
      BEGIN;
      INSERT INTO "Article" VALUES (1, 'Prisma Adds Support for MongoDB', 'We are excited to share that today''s Prisma ORM release adds stable support for MongoDB!', 'Support for MongoDB has been one of the most requested features since the initial release of...', 'f', '2023-03-05 11:37:02.098', '2023-03-05 11:37:02.098');
      INSERT INTO "Article" VALUES (2, 'What''s new in Prisma? (Q1/22)', 'Learn about everything in the Prisma ecosystem and community from January to March 2022.', 'Our engineers have been working hard, issuing new releases with many improvements...', 't', '2023-03-05 11:37:02.104', '2023-03-05 11:37:02.104');
      INSERT INTO "Article" VALUES (3, '??????????? - ??', '?????????', '????????????????????', 'f', '2023-03-05 13:27:05.698', '2023-03-05 13:27:05.698');
      INSERT INTO "Article" VALUES (5, 'hello', '???', '????????????????????????', 't', '2023-03-05 14:43:54.834', '2023-03-05 14:43:54.834');
      COMMIT;

      -- ----------------------------
      -- Table structure for _prisma_migrations
      -- ----------------------------
      DROP TABLE IF EXISTS "_prisma_migrations";
      CREATE TABLE "_prisma_migrations" (
        "id" varchar(36) COLLATE "pg_catalog"."default" NOT NULL,
        "checksum" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
        "finished_at" timestamptz(6),
        "migration_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
        "logs" text COLLATE "pg_catalog"."default",
        "rolled_back_at" timestamptz(6),
        "started_at" timestamptz(6) NOT NULL DEFAULT now(),
        "applied_steps_count" int4 NOT NULL DEFAULT 0
      )
      ;

      -- ----------------------------
      -- Records of _prisma_migrations
      -- ----------------------------
      BEGIN;
      INSERT INTO "_prisma_migrations" VALUES ('0a4988c2-1611-4c41-a4be-f4e9d0c86268', 'c09b8e3dc7e214bacf78328cc31674d1fa8d7de9dcc8ed139030d58ca1d88354', '2023-03-05 11:28:51.786892+00', '20230305112851_init', NULL, NULL, '2023-03-05 11:28:51.774933+00', 1);
      COMMIT;

      -- ----------------------------
      -- Alter sequences owned by
      -- ----------------------------
      ALTER SEQUENCE "Article_id_seq"
      OWNED BY "Article"."id";
      SELECT setval('"Article_id_seq"', 6, true);

      -- ----------------------------
      -- Indexes structure for table Article
      -- ----------------------------
      CREATE UNIQUE INDEX "Article_title_key" ON "Article" USING btree (
        "title" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
      );

      -- ----------------------------
      -- Primary Key structure for table Article
      -- ----------------------------
      ALTER TABLE "Article" ADD CONSTRAINT "Article_pkey" PRIMARY KEY ("id");

      -- ----------------------------
      -- Primary Key structure for table _prisma_migrations
      -- ----------------------------
      ALTER TABLE "_prisma_migrations" ADD CONSTRAINT "_prisma_migrations_pkey" PRIMARY KEY ("id");
EOSQL