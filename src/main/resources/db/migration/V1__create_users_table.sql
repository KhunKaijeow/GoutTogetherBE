CREATE TABLE IF NOT EXISTS "users" (
  "user_id" serial PRIMARY KEY,
  "first_name" varchar(100) NOT NULL,
  "last_name" varchar(100) NOT NULL,
  "phone_number" varchar(10) UNIQUE
);