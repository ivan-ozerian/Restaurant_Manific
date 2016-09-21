create table employees(
	ID serial primary key,
	NAME varchar(20) not null,
	SURNAME varchar(20) not null,
	BIRTHDATE date not null,
	PHONE_NUMBER varchar(20),
	SALARY real,
	POSITION varchar(20) not null
);

create table orders (
	ID serial primary key,
	ORDER_TIME timestamp not null,
	TABLE_NUMBER int not null,
	WAITER_ID int references employees(id),
	STATUS boolean not null
);

create table dishes(
	ID serial primary key,
	DISH_NAME varchar(30) not null,
	CATEGORY varchar(30) not null,
	PRICE DECIMAL,
	WEIGHT INT
);

create table order_content(
	ORDER_ID int references orders(id),
	DISH_ID int references dishes(id)
);

create table prepared_dishes(
  ID serial primary key,
	ORDER_ID int references orders(id),
	DISH_ID int references dishes(id),
	COOK_ID int references employees(id),
	PREPARED_TIME timestamp not null
);

create table ingredients(
	ID serial primary key,
	NAME varchar(20) not null
);

create table dish_content(
	DISH_ID int references dishes(id),
	INGREDIENT_ID int references ingredients(id)
);

create table menus(
	ID serial primary key,
	MENU_NAME varchar(30) not null
);

create table dishes_menu(
	MENU_ID int references menus(id),
	DISH_ID int references dishes(id)
);

create table storage(
	INGREDIENT_ID int references ingredients(id),
	QUANTITY int not null
);