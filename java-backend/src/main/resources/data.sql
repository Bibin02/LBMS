-- Clear all data before adding
delete from users;
delete from book;

INSERT INTO users (user_uid, user_name, pass, is_seller, user_address, user_description) VALUES 
('john@example.com', 'John Doe', '123', FALSE, 'address1', 'about1'),
('raven@example.com', 'Raven Doe', '123', FALSE, 'address2', 'about2'),
('david@example.com', 'David Doe', '123', FALSE, 'address3', 'about3'),
('george@example.com', 'George Doe', '123', TRUE, 'address4', 'about4');


INSERT INTO book (book_uid, author_names, publication_name, keywords, stock, discount, book_description) VALUES
('BK001', 'George Orwell', 'Secker & Warburg', 'dystopia,totalitarianism,classic', 50, 10, 'A dystopian social science fiction novel and cautionary tale about the future.'),
('BK002', 'J.K. Rowling', 'Bloomsbury', 'fantasy,magic,wizard', 120, 15, 'A young wizards journey through magic, friendship, and adventure.'),
('BK003', 'Yuval Noah Harari', 'Harvill Secker', 'history,evolution,science', 75, 5, 'An exploration of humanitys creation and evolution.'),
('BK004', 'Jane Austen', 'T. Egerton', 'romance,classics,women', 30, 0, 'A romantic novel of manners set in 19th-century England.'),
('BK005', 'Stephen King', 'Viking Press', 'horror,thriller,supernatural', 60, 20, 'A chilling story of horror and psychological tension.');
