create database dbt_product;
go
use dbt_product;
go

create table categories(
	id int primary key identity,
	name nvarchar(255),
	status tinyint default 0,
	created_at date default GETDATE()
)
go

create table products(
	id int primary key identity,
	category_id int not null,
	name nvarchar(255),
	price float,
	description text,
	thumbnail text,
	created_at date default GETDATE()
)
go

alter table products add foreign key (category_id) references categories(id)