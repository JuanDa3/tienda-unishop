insert into ciudad values (1, 'Armenia');
insert into ciudad values (2, 'Pereira');
insert into ciudad values (3, 'Manizales');

insert into usuario values ('1', 'juan@gmail.com', 'Juan', '1234', '1');
insert into usuario values ('2', 'jhan@gmail.com', 'Jhan', '4321', '2');
insert into usuario values ("3", "sebas@gmail.com", "Sebastian", "0000", '1');
insert into usuario values ("4", "Honores@gmail.com", "Michael", "1111", '3');

insert into categoria values ("1", "electodomestico");
insert into categoria values ("2", "cosmetico");
insert into categoria values ("3", "hogar");

insert into compra values ("1", "2021-10-18", '0', "1");
insert into compra values ("2", "2021-10-18", '1', "1");
insert into compra values ("3", "2021-10-18", '0', "1");

insert into producto values ("1", "Descripcion Prueba2", '10', "2021-10-18", "producto 1", '1000', '2','1', '1');
insert into producto values ("2", "Descripcion Prueba2", '20', "2021-10-18", "producto 2", '1000', '10','2', '2');
insert into producto values ("3", "Descripcion Prueba3", '30', "2021-10-18", "producto 3", '1000', '5','3', '3');

insert into Detalle_Compra values("1", '20000', '12', '1', '1');
insert into Detalle_Compra values("2", '40000', '11', '2', '2');
insert into Detalle_Compra values("3", '30000', '20', '3', '3');

insert into comentario values ("1", '5', "2021-10-18", "Mensaje Prueba1", "Respuesta Prueba1", '1', '1');
insert into comentario values ("2", '3', "2021-10-18", "Mensaje Prueba2", "Respuesta Prueba2", '2', '2');
insert into comentario values ("3", '1', "2021-10-18", "Mensaje Prueba3", "Respuesta Prueba3", '3', '3');

insert into subasta values ("1", "2021-10-18", '1');
insert into subasta values ("2", "2021-10-18", '2');
insert into subasta values ("3", "2021-10-18", '3');

insert into detalle_subasta values ("1", "2021-10-18",'30000', '1', '1');
insert into detalle_subasta values ("2", "2021-10-18",'40000', '1', '2');
insert into detalle_subasta values ("3", "2021-10-18",'50000', '1', '3');

insert into administrador values ('123','juan@mail.com','juan jacinto','12345');
insert into administrador values ('124','jhan@mail.com','jhan','contrasenia');
insert into administrador values ('125','josesito@mail.com','juan jose','password');

insert into chat values ('1', '1', '1');
insert into chat values ('2', '2', '2');
insert into chat values ('3', '3', '3');

insert into Mensaje values('1', 'pedro', '2020-12-01', 'hola', '1');
insert into Mensaje values("2", "pedro", "2020-12-01", "hola","2");
insert into Mensaje values("3", "pedro", "2020-12-01", "hola", "3");

