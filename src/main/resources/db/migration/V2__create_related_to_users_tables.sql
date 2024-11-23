CREATE TABLE IF NOT EXISTS "users_login" (
  "userlogin_id" serial PRIMARY KEY,
  "user_id" int,
  "email" varchar(255) NOT NULL,
  "password" varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "roles" (
  "role_id" serial PRIMARY KEY,
  "role_name" varchar(20)
);

CREATE TABLE IF NOT EXISTS "users_role" (
  "user_role_id" serial PRIMARY KEY,
  "user_id" int,
  "role_id" int
);

CREATE TABLE IF NOT EXISTS "users_wallet" (
  "user_wallet_id" serial PRIMARY KEY,
  "user_id" int,
  "balance" float NOT NULL,
  "last_updated" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE IF NOT EXISTS "users_points" (
  "user_point_id" serial PRIMARY KEY,
  "user_id" int,
  "balance" float NOT NULL,
  "last_updated" timestamp NOT NULL DEFAULT (now())
);

ALTER TABLE "users_login" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("user_id");

ALTER TABLE "users_role" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("user_id");

ALTER TABLE "users_role" ADD FOREIGN KEY ("role_id") REFERENCES "roles" ("role_id");

ALTER TABLE "users_wallet" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("user_id");

ALTER TABLE "users_points" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("user_id");