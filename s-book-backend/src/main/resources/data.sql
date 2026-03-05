-- Inserting a Vendor (ID 1)
INSERT INTO vendors (id, name, location, category, price_per_hour, email, password)
VALUES (1, 'Premium Turf', 'Hyderabad', 'Sports', 1200.0, 'vendor@test.com', '1234');

-- Pre-loading Slots for Vendor ID 1
-- ADD the vendor_id column here!
--INSERT INTO slots (vendor_id, start_time, end_time, is_booked)
--VALUES (1, '2026-03-05T10:00:00', '2026-03-05T11:00:00', false);
--
--INSERT INTO slots (vendor_id, start_time, end_time, is_booked)
--VALUES (1, '2026-03-05T11:00:00', '2026-03-05T12:00:00', false);

-- Inserting second Vendor (ID 2)
INSERT INTO vendors (id, name, location, category, price_per_hour, email, password)
VALUES (2, 'Gachibowli Arena', 'Gachibowli', 'Football', 1500.0, 'arena@test.com', 'pass123');
INSERT INTO users (id, name, email, password, role)
VALUES (1, 'louda', 'louda.com', '123', 'USER');