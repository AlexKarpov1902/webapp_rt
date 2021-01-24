insert into city (name, population) values ('Пермь', 1000000),
                               ('Москва', 10000000),
                               ('Новосибирск', 10000000),
                               ('Киров', 500000),
                               ('Екатеринбург', 13000000),
                               ('Краснодар', 1300000),
                               ('Орел', 700000),
                               ('Санкт-Петербург', 5000000);

insert into person (lastname, firstname, middlename, city_id ) values ('Иванов','Алексей','Петрович',1);
insert into person (lastname, firstname, middlename, city_id ) values ('Петров','Федор','Максимович',2);
insert into person (lastname, firstname, middlename, city_id ) values ('Сидоров','Иван','Павлович',3);
insert into person (lastname, firstname, middlename, city_id ) values ('Краснов','Семен','Денисович',4);
insert into person (lastname, firstname, middlename, city_id ) values ('Чернов','Павел','Львович',5);
insert into person (lastname, firstname, middlename, city_id ) values ('Белов','Денис','Семенович',6);
insert into person (lastname, firstname, middlename, city_id ) values ('Петров','Максим','Алексеевич',7);

insert into  auto (model, color, person_id ) values
('audi','красный', 1), ('bmw','зеленый', 2), ('kia','серый', 1),('toyota','желтый', 3),
('lada','зеленый' ,4),('renault','белый' ,5),('ford','красный' ,5),('ferrary','белый' ,6),
('nissan','бардовый' ,6),('opel','зеленый' ,7),('skoda','красный' ,1),('uaz','серый' ,3);
