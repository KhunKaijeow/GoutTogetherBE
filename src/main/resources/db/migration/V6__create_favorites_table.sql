CREATE TABLE "favorites" (
     "favorite_id" serial PRIMARY KEY,
     "user_id" int,
     "tour_id" int
);

ALTER TABLE "favorites" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("user_id");
ALTER TABLE "favorites" ADD FOREIGN KEY ("tour_id") REFERENCES "tours" ("tour_id");