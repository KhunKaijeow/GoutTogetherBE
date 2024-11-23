CREATE TABLE "bookings" (
    "booking_id" serial PRIMARY KEY,
    "user_id" int,
    "tour_id" int,
    "state" varchar(20) NOT NULL,
    "booking_date" timestamp NOT NULL,
    "last_updated" timestamp NOT NULL DEFAULT (now())
);

ALTER TABLE "bookings" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("user_id");
ALTER TABLE "bookings" ADD FOREIGN KEY ("tour_id") REFERENCES "tours" ("tour_id");