create database company_prod_db;

create table company
(
	id bigserial not null
		constraint company_pk
			primary key,
	name VARCHAR not null,
	address VARCHAR not null
);

create table employee
(
	id bigserial not null
		constraint employee_pk
			primary key,
	name VARCHAR not null,
	date_start DATE not null,
	date_dismissal DATE,
	company bigint not null
		constraint employee_company_id_fk
			references company
);

INSERT INTO company (name, address) VALUES ('Компания_11', 'Адрес_11');
INSERT INTO company (name, address) VALUES ('Компания_12', 'Адрес_12');
INSERT INTO company (name, address) VALUES ('Компания_13', 'Адрес_13');
INSERT INTO company (name, address) VALUES ('Компания_14', 'Адрес_14');
INSERT INTO company (name, address) VALUES ('Компания_15', 'Адрес_15');

INSERT INTO employee (name, date_start, date_dismissal, company) VALUES ('ФИО_11', '2019-10-5', null,1);
INSERT INTO employee (name, date_start, date_dismissal, company) VALUES ('ФИО_12', '2018-10-15', '2019-12-31',1);
INSERT INTO employee (name, date_start, date_dismissal, company) VALUES ('ФИО_13', '2018-8-4', null,2);
INSERT INTO employee (name, date_start, date_dismissal, company) VALUES ('ФИО_14', '2019-10-5', '2019-11-5',3);
INSERT INTO employee (name, date_start, date_dismissal, company) VALUES ('ФИО_15', '2017-6-13', null,4);
INSERT INTO employee (name, date_start, date_dismissal, company) VALUES ('ФИО_16', '2016-1-12', '2020-3-3',4);
INSERT INTO employee (name, date_start, date_dismissal, company) VALUES ('ФИО_17', '2019-6-1', null,5);
