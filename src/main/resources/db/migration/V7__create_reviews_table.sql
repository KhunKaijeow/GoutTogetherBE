CREATE TABLE "reviews" (
   "review_id" serial PRIMARY KEY,
   "user_id" int,
   "tour_id" int,
   "rate" int NOT NULL,
   "description" varchar(1000)
);

ALTER TABLE "reviews" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("user_id");
ALTER TABLE "reviews" ADD FOREIGN KEY ("tour_id") REFERENCES "tours" ("tour_id");