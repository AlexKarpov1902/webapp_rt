drop table if exists auto;
drop table if exists city cascade;
drop table if exists person;

create table city
(
    id serial primary key not null,
    name varchar(50),
    population int
);

create table person
(
    id         serial primary key not null,
    lastname   varchar(50),
    firstname  varchar(50),
    middlename varchar(50),
    city_id int references city(id)
 );

create table auto
(
    id   serial primary key not null,
    model varchar(50),
    color varchar(50),
    person_id int references person(id)
);

