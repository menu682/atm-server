create table money (
	"id" serial primary key,
	"amount" int,
	"bill" int,
	"created" timestamp,
	"updated" timestamp
);

create table users (
	"id" serial primary key,
	"name" varchar(255),
	"password" varchar(255),
	"created" timestamp,
	"updated" timestamp
);

create table carts (
	"id" serial primary key,
	"number" bigint not null,
	"pin" varchar(4) not null,
	"credit" bigint,
	"debit" bigint,
	"created" timestamp,
	"updated" timestamp,
	"user_id" bigint,
	constraint cart_to_user foreign key ("user_id") references users("id")
);

create table roles (
	"id" serial primary key,
	"name" varchar(255) not null,
	"created" timestamp,
	"updated" timestamp
);

create table users_roles (
	"id" serial primary key,
	"user_id" bigint,
	"role_id" bigint,
	"created" timestamp,
	"updated" timestamp,
	foreign key ("user_id") references users("id"),
	foreign key ("role_id") references roles("id"),
	constraint "user_role_key" unique ("user_id", "role_id")
);

delete from money;

insert into money (bill, amount) values
(1000, 0),
(500, 0),
(200, 0),
(100, 0),
(50, 0);

select * from money;

delete from users;

insert into users (name, password) values
('Neo', '0000'),
('Morfeus', '0000'),
('Trinity', '0000'),
('Pifia', '0000'),
('Smith', '0000');

select * from users;

delete from carts;

insert into carts (pin, number, credit, debit, user_id) values
('1111', 1111222233334444, 10000, 3000, 1),
('1234', 1234123412341234, 50000, 4300, 2),
('9999', 9999888877776666, 54321, 12345, 3),
('5555', 5555444466663333, 678432, 456731, 4);

select * from carts join users as u on "user_id" = u.id;

delete from roles;

insert into roles (name) values
('admin'),
('user');

select * from roles;

insert into users_roles (user_id, role_id) values
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 1);

select * from users_roles join users as u on "user_id" = u.id join roles as r on "role_id" = r.id;

select bill from money where amount > 0 order by bill asc limit 1
