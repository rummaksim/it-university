create database company_dev_db;

--schema public

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

INSERT INTO company (name, address) VALUES ('Компания_1', 'Адрес_1');
INSERT INTO company (name, address) VALUES ('Компания_2', 'Адрес_2');
INSERT INTO company (name, address) VALUES ('Компания_3', 'Адрес_3');
INSERT INTO company (name, address) VALUES ('Компания_4', 'Адрес_4');
INSERT INTO company (name, address) VALUES ('Компания_5', 'Адрес_5');

INSERT INTO employee (name, date_start, date_dismissal, company) VALUES ('ФИО_1', '2019-10-5', null,1);
INSERT INTO employee (name, date_start, date_dismissal, company) VALUES ('ФИО_2', '2018-10-15', '2019-12-31',1);
INSERT INTO employee (name, date_start, date_dismissal, company) VALUES ('ФИО_3', '2018-8-4', null,2);
INSERT INTO employee (name, date_start, date_dismissal, company) VALUES ('ФИО_4', '2019-10-5', '2019-11-5',3);
INSERT INTO employee (name, date_start, date_dismissal, company) VALUES ('ФИО_5', '2017-6-13', null,4);
INSERT INTO employee (name, date_start, date_dismissal, company) VALUES ('ФИО_6', '2016-1-12', '2020-3-3',4);
INSERT INTO employee (name, date_start, date_dismissal, company) VALUES ('ФИО_7', '2019-6-1', null,5);
