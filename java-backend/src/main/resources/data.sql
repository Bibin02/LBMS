-- Clear all data before adding
delete from orders;
delete from cart_book;
delete from cart;
delete from review;
delete from book;
delete from seller;
delete from users;

INSERT INTO users (user_uid, user_name, pass, user_address, user_description, user_role) VALUES 
('john@example.com', 'John Doe', '123', 'address1', 'about1', 'ADMIN'),
('raven@example.com', 'Raven Doe', '123', 'address2', 'about2', 'SELLER'),
('david@example.com', 'David Doe', '123', 'address3', 'about3', 'BUYER'),
('george@example.com', 'George Doe', '123', 'address4', 'about4', 'BUYER'),
('bob@example.com', 'Bob Doe', '123', 'address5', 'about5', 'SELLER'),
('jacob@example.com', 'Jacob Doe', '123', 'address6', 'about6', 'SELLER'),
('sam@example.com', 'Sam Doe', '123', 'address7', 'about7', 'SELLER');


INSERT INTO seller (seller_uid, books_sold_count, earnings) VALUES 
('john@example.com', 5, 10.0),
('raven@example.com', 10, 102.0),
('bob@example.com', 200, 9000.55),
('jacob@example.com', 8, 213.0),
('sam@example.com', 0, 0.0);


INSERT INTO book (book_uid, book_name, author_names, publication_name, keywords, stock, discount, cost, book_description, seller_uid) VALUES
('BK001', 'Book AAA', 'George Orwell', 'Secker & Warburg', 'dystopia,totalitarianism,classic', 50, 10, 1.0, '{"about" : "A dystopian social science fiction novel and cautionary tale about the future."}', 'john@example.com'),
('BK002', 'Book BBB', 'J.K. Rowling', 'Bloomsbury', 'fantasy,magic,wizard', 120, 15, 1.5, '{ "about": "A young wizards journey through magic, friendship, and adventure."}', 'raven@example.com'),
('BK003', 'Book CCC', 'Yuval Noah Harari', 'Harvill Secker', 'history,evolution,science', 75, 5, 2.0, '{ "about": "An exploration of humanitys creation and evolution."}', 'bob@example.com'),
('BK004', 'Book DDD', 'Jane Austen', 'T. Egerton', 'romance,classics,women', 30, 0, 5., '{ "about": "A romantic novel of manners set in 19th-century England."}', 'jacob@example.com'),
('BK005', 'Book EEE', 'Stephen King', 'Viking Press', 'horror,thriller,supernatural', 60, 20, 1.2, '{ "about": "A chilling story of horror and psychological tension."}', 'sam@example.com');


INSERT INTO review (rating, comments, book_uid, user_uid) VALUES
(5.0, 'Comments By John for Book 1', 'BK001', 'john@example.com'),
(4.0, 'Comments By John for Book 2', 'BK002', 'john@example.com'),
(5.0, 'Comments By Raven for Book 1', 'BK001', 'raven@example.com');

INSERT INTO cart (cart_uid, is_ordered, user_uid) VALUES
('9001', true, 'john@example.com'),
('9002', false, 'raven@example.com'),
('9003', true, 'david@example.com'),
('9004', false, 'george@example.com');

INSERT INTO cart_book (cart_uid, book_uid, book_count) VALUES
('9001', 'BK001', 1),
('9001', 'BK002', 3),
('9001', 'BK003', 5),
('9004', 'BK001', 5),
('9004', 'BK002', 2),
('9004', 'BK005', 1),
('9001', 'BK004', 1);

INSERT INTO orders (order_uid, order_status_code, order_status_message, total_amount, is_paid, is_delivered, cart_uid) VALUES 
('9001', 200, 'Delivered', 500, true, true, '9001'),
('9002', 500, 'Not Paid', 100, false, false, '9002'),
('9003', 300, 'Not Delivered', 300, true, false, '9003'),
('9004', 900, 'Partial Delivered', 200, true, false, '9004');
