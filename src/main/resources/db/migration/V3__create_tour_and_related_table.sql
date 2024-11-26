CREATE TABLE IF NOT EXISTS "tour_companies" (
  "tour_company_id" serial PRIMARY KEY,
  "tour_company_name" varchar(255) NOT NULL,
  "status" varchar(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS "tour_companies_login" (
  "tour_company_login_id" serial PRIMARY KEY,
  "tour_company_id" int,
  "username" varchar(255) NOT NULL,
  "password" varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "tours_companies_wallet" (
  "tour_company_wallet_id" serial PRIMARY KEY,
  "tour_company_id" int,
  "balance" float NOT NULL,
  "last_updated" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE IF NOT EXISTS "tours" (
  "tour_id" serial PRIMARY KEY,
  "tour_company_id" int,
  "title" varchar(255) NOT NULL,
  "description" varchar(255) NOT NULL,
  "location" varchar(255) NOT NULL,
  "number_of_people" int NOT NULL,
  "activity_date" date NOT NULL,
  "status" varchar(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS "tours_count" (
  "tour_count_id" serial PRIMARY KEY,
  "tour_id" int,
  "amount" int NOT NULL
);

ALTER TABLE "tour_companies_login" ADD FOREIGN KEY ("tour_company_id") REFERENCES "tour_companies" ("tour_company_id");

ALTER TABLE "tours_wallet" ADD FOREIGN KEY ("tour_company_id") REFERENCES "tour_companies" ("tour_company_id");

ALTER TABLE "tours" ADD FOREIGN KEY ("tour_company_id") REFERENCES "tour_companies" ("tour_company_id");

ALTER TABLE "tours_count" ADD FOREIGN KEY ("tour_id") REFERENCES "tours" ("tour_id");