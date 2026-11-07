
-- Tabela dos usuários que podem fazer login na API.
-- A senha é guardada como hash BCrypt, nunca em texto puro.

create table usuarios(

    id bigint not null auto_increment,
    login varchar(100) not null unique,
    senha varchar(255) not null,

    primary key(id)

);
