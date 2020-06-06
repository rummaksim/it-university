DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS employee;

create sequence company_id_seq;

create table company
(
	id bigint default company_id_seq.nextval primary key not null,
	name varchar not null,
	address varchar not null,
	constraint company_pk
		primary key (id)
);

create sequence employee_id_seq;

create table employee
(
	id bigint default employee_id_seq.nextval primary key not null,
	name varchar not null,
	date_start DATE not null,
	date_dismissal DATE,
	company bigint not null,
	constraint employee_pk
		primary key (id),
	constraint employee_COMPANY_ID_fk
		foreign key (company) references COMPANY
);



