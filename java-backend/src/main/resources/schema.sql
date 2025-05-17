CREATE TABLE IF NOT EXISTS users (
    uid varchar(50) primary key,
    pass varchar(50),
    uname varchar(100),
    is_seller BOOLEAN,
    address varchar(200),
    description varchar(200)
);