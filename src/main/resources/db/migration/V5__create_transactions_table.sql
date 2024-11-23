CREATE TABLE "transactions" (
    "transaction_id" serial PRIMARY KEY,
    "user_id" int,
    "tour_id" int,
    "type" varchar(10),
    "amount" float NOT NULL,
    "transaction_date" timestamp NOT NULL
);