create table users (
	username char(20) not null,
	password char(20) not null,
	authority char(10) not null
);

INSERT INTO users VALUES ('admin', '123456', 'ADMIN');