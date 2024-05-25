create database if not exists main;
use main;
create table if not exists pessoa (
	id bigint primary key auto_increment,
    nome varchar(255) not null,
    data_nascimento date not null,
    cpf varchar(255) not null,
    sexo char(1) not null,
	altura real(4,2) not null,
    peso real(4,2) not null
);