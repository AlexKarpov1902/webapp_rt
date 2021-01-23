insert into city (name, population) values ('Пермь', 1000000),
                               ('Москва', 10000000),
                               ('Новосибирск', 10000000),
                               ('Киров', 500000),
                               ('Екатеринбург', 13000000),
                               ('Краснодар', 1300000),
                               ('Орел', 700000),
                               ('Санкт-Петербург', 5000000);

insert into person (lastname, firstname, middlename, city_id ) values ('Фамилия1','Имя1','Отчество1',1);
insert into person (lastname, firstname, middlename, city_id ) values ('Фамилия2','Имя2','Отчество1',2);
insert into person (lastname, firstname, middlename, city_id ) values ('Фамилия3','Имя3','Отчество1',3);
insert into person (lastname, firstname, middlename, city_id ) values ('Фамилия4','Имя4','Отчество1',4);
insert into person (lastname, firstname, middlename, city_id ) values ('Фамилия5','Имя5','Отчество1',5);
insert into person (lastname, firstname, middlename, city_id ) values ('Фамилия6','Имя6','Отчество1',6);
insert into person (lastname, firstname, middlename, city_id ) values ('Фамилия7','Имя7','Отчество1',7);

insert into  auto (model, color, person_id ) values
('audi','красный', 1), ('bmw','зеленый', 2), ('kia','серый', 1),('toyota','желтый', 3),
('lada','зеленый' ,4),('renault','белый' ,5),('ford','красный' ,5),('ferrary','белый' ,6),
('nissan','бардовый' ,6),('opel','зеленый' ,7),('skoda','красный' ,1),('uaz','серый' ,3);
