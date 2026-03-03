-- Inserting a Regular User
--INSERT INTO users (name, email, password, role)
--VALUES ('Charan', 'charan@test.com', '1234', 'USER');
--INSERT INTO users (name, email, password, role)
--VALUES ('Prabhas', 'prabhas@test.com', '1234', 'USER');
-- Inserting a Vendor
-- Note: Check if you have a 'vendors' table. Hibernate created one in your logs.
INSERT INTO vendors (name, location, category, price_per_hour)
VALUES ('Premium Turf', 'Hyderabad', 'Sports', 1200.0);

-- Pre-loading some Slots (Matching Hibernate's column names)
-- vendor_id 1 refers to the vendor we just created above.
INSERT INTO slots (vendor_id, start_time, end_time, is_booked)
VALUES (1, '2026-03-04 10:00:00', '2026-03-04 11:00:00', false);

INSERT INTO slots (vendor_id, start_time, end_time, is_booked)
VALUES (1, '2026-03-04 11:00:00', '2026-03-04 12:00:00', false);