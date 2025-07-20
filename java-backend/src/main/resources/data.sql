-- Clear all data before adding
delete from review;
delete from book;
delete from seller;
delete from users;

INSERT INTO users (user_uid, user_name, pass, user_address, user_description) VALUES 
('john@example.com', 'John Doe', '123', 'address1', 'about1'),
('raven@example.com', 'Raven Doe', '123', 'address2', 'about2'),
('david@example.com', 'David Doe', '123', 'address3', 'about3'),
('george@example.com', 'George Doe', '123', 'address4', 'about4');


INSERT INTO seller (seller_uid, seller_name, pass, seller_address, seller_description, books_sold_count, earnings) VALUES 
('2001', 'John Doe', '123', 'address1', 'about1', 0, 0.0),
('2002', 'Raven Doe', '123', 'address2', 'about2', 0, 0.0),
('2003', 'Bob Doe', '123', 'address3', 'about3', 0, 0.0),
('2004', 'Jacob Doe', '123', 'address4', 'about4', 0, 0.0),
('2005', 'Sam Doe', '123', 'address5', 'about5', 0, 0.0);


INSERT INTO book (book_uid, book_name, author_names, publication_name, keywords, stock, discount, cost, book_description, seller_uid) VALUES
('BK001', 'Book AAA', 'George Orwell', 'Secker & Warburg', 'dystopia,totalitarianism,classic', 50, 10, 1.0, '{"about" : "A dystopian social science fiction novel and cautionary tale about the future."}', '2001'),
('BK002', 'Book BBB', 'J.K. Rowling', 'Bloomsbury', 'fantasy,magic,wizard', 120, 15, 1.5, '{ "about": "A young wizards journey through magic, friendship, and adventure."}', '2002'),
('BK003', 'Book CCC', 'Yuval Noah Harari', 'Harvill Secker', 'history,evolution,science', 75, 5, 2.0, '{ "about": "An exploration of humanitys creation and evolution."}', '2003'),
('BK004', 'Book DDD', 'Jane Austen', 'T. Egerton', 'romance,classics,women', 30, 0, 5., '{ "about": "A romantic novel of manners set in 19th-century England."}', '2004'),
('BK005', 'Book EEE', 'Stephen King', 'Viking Press', 'horror,thriller,supernatural', 60, 20, 1.2, '{ "about": "A chilling story of horror and psychological tension."}', '2005');


INSERT INTO review (rating, comments, book_uid, user_uid) VALUES
(5.0, 'Comments By John for Book 1', 'BK001', 'john@example.com'),
(4.0, 'Comments By John for Book 2', 'BK002', 'john@example.com'),
(5.0, 'Comments By Raven for Book 1', 'BK001', 'raven@example.com')