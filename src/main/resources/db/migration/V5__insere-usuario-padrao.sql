
-- Usuário para testar o login (o h2-console fica bloqueado pelo Spring Security).
-- login: admin@oficina.com
-- senha: 123456  (abaixo está o hash BCrypt dela)

insert into usuarios (login, senha)
values ('admin@oficina.com', '$2a$10$OtFcEjknfrEfeW8BZUpCye2eFQSoa/vx1eouLhmwIBmzp2ZDEsW16');
