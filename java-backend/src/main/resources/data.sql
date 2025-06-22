-- Clear all data before adding
delete from review;
delete from users;
delete from book;

INSERT INTO users (user_uid, user_name, pass, user_address, user_description) VALUES 
('john@example.com', 'John Doe', '123', 'address1', 'about1'),
('raven@example.com', 'Raven Doe', '123', 'address2', 'about2'),
('david@example.com', 'David Doe', '123', 'address3', 'about3'),
('george@example.com', 'George Doe', '123', 'address4', 'about4');


INSERT INTO book (book_uid, book_name, author_names, publication_name, keywords, stock, discount, cost, book_description) VALUES
('BK001', 'Book AAA', 'George Orwell', 'Secker & Warburg', 'dystopia,totalitarianism,classic', 50, 10.0, 10, '{"about" : "A dystopian social science fiction novel and cautionary tale about the future."}'),
('BK002', 'Book BBB', 'J.K. Rowling', 'Bloomsbury', 'fantasy,magic,wizard', 120, 15.0, 15, '{ "about": "A young wizards journey through magic, friendship, and adventure."}'),
('BK003', 'Book CCC', 'Yuval Noah Harari', 'Harvill Secker', 'history,evolution,science', 75, 5.0, 20, '{ "about": "An exploration of humanitys creation and evolution."}'),
('BK004', 'Book DDD', 'Jane Austen', 'T. Egerton', 'romance,classics,women', 30, 0, 5, '{ "about": "A romantic novel of manners set in 19th-century England."}'),
('BK005', 'Book EEE', 'Stephen King', 'Viking Press', 'horror,thriller,supernatural', 60, 20.0, 12, '{ "about": "A chilling story of horror and psychological tension."}');


INSERT INTO review (review_id, rating, comments, book_uid, user_uid) VALUES
(1001, 5.0, 'Comments', 'BK001', 'john@example.com'),
(1002, 4.0, 'Comments', 'BK001', 'john@example.com'),
(1003, 5.0, 'Comments', 'BK001', 'john@example.com')