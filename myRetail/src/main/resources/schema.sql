create table product_detail
(
   id integer not null,
   product_id varchar(50) not null,
   name varchar(50),
   description varchar(255),
   supplier varchar(100),
   price_amount integer,
   price_currency varchar(10),
   primary key(id)
);