create table histories (
	username char(20) not null,
	bookname char(80) not null,
	lastIndex int not null,
	lastTime timestamp not null
);
