-- Clear all data before adding
delete from users;

INSERT INTO users (uid, uname, pass) VALUES 
('john@example.com', 'John Doe', '123'),
('raven@example.com', 'Raven Doe', '123'),
('david@example.com', 'David Doe', '123'),
('george@example.com', 'George Doe', '123');