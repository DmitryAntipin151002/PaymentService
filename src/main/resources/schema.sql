CREATE TABLE payments (
                          payment_id SERIAL PRIMARY KEY,
                          amount DECIMAL(10, 2) NOT NULL,
                          payment_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          status VARCHAR(50) NOT NULL
);

INSERT INTO payments (amount, payment_date, status) VALUES
                                                        (100.50, '2024-06-01 12:30:00', 'completed'),
                                                        (75.00, '2024-06-02 15:45:00', 'pending'),
                                                        (250.75, '2024-06-03 10:00:00', 'completed'),
                                                        (50.25, '2024-06-04 18:20:00', 'cancelled');