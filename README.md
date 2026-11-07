# API de Consertos - PRW3

Projeto desenvolvido em dupla para a disciplina de **Programação para Web III (PRW3)**.

A aplicação é uma API REST em Java com Spring Boot para cadastrar, consultar, atualizar e realizar a exclusão lógica de consertos de veículos.

As rotas de consertos são protegidas com Spring Security: é preciso fazer login e enviar o token JWT em cada requisição.

## Integrantes

- Lucas Dalossa Lopes
- Luiz Augusto de Oliveira Sellitto

## Login e token

O banco já vem com um usuário de teste (criado pela migration V5):

- login: `admin@oficina.com`
- senha: `123456`

1. Faça um `POST /login` com o JSON abaixo:

```json
{
  "login": "admin@oficina.com",
  "senha": "123456"
}
```

2. A resposta traz o token, que vale por 2 horas:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
```

3. Nas outras requisições (`/consertos`), envie o token no cabeçalho `Authorization`:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIs...
```

No Postman, isso fica na aba **Authorization**, tipo **Bearer Token**. Sem o token, ou com um token inválido ou expirado, a API responde `403`.

A chave usada para assinar o token vem da variável de ambiente `JWT_SECRET`. Se ela não existir, é usado um valor padrão para rodar localmente.

## Swagger (testar a API pelo navegador)

O projeto tem o Swagger, uma página que lista todas as rotas da API e permite testar cada uma sem precisar do Postman.

1. Rode a aplicação (classe `Prw320261ApiApplication`).
2. Com ela rodando, abra no navegador: http://localhost:8080/swagger-ui.html
3. Faça o login em `POST /login` (**Try it out** > colar o JSON > **Execute**) e copie o token da resposta.
4. Clique no botão **Authorize** (no topo da página), cole só o token (sem o `Bearer`) e confirme.
5. Agora é só escolher uma rota de consertos e clicar em **Try it out**.
6. Preencha os dados (o JSON do corpo ou o `id` na URL) e clique em **Execute**.
7. Logo abaixo aparecem o código de status e a resposta da API.

Exemplo de JSON para o `POST /consertos`:

```json
{
  "dataEntrada": "01/10/2026",
  "dataSaida": "03/10/2026",
  "mecanico": { "nome": "Joao Silva", "anosExperiencia": 8 },
  "veiculo": { "marca": "Fiat", "modelo": "Uno", "ano": "2010", "cor": "Prata" }
}
```
