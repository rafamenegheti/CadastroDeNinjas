## Autenticação com Spring Security + JWT

Este projeto usa Spring Security com JWT para autenticação stateless e controle de acesso por papéis (ADMIN/USER).

### Dependências

- spring-boot-starter-security
- jjwt-api, jjwt-impl, jjwt-jackson
- spring-boot-starter-validation (opcional para DTOs)

### Configurações (application.properties)

```
security.jwt.secret=BASE64_SECRET
security.jwt.expirationMillis=3600000
```

- secret: chave base64 para assinar tokens (HS256)
- expirationMillis: validade do token (ms)

### Modelo de Usuário e Papéis

- `Role` (enum): `ADMIN`, `USER`
- `UserAccount` (entity): `id`, `username`, `password` (BCrypt), `roles` (ElementCollection EAGER)
- `UserAccountRepository`: busca por `username`

### Geração/Validação de JWT

- `JwtService`
  - `generateToken(UserAccount)`: cria JWT com subject = username, claim `roles` (CSV), iat/exp e assinatura HS256
  - `extractUsername(token)`, `isTokenValid(token)`

### Filtro de Autenticação

- `JwtAuthenticationFilter` (OncePerRequestFilter)
  - Lê header `Authorization: Bearer <token>`
  - Valida token com `JwtService`
  - Carrega usuário do `UserAccountRepository`
  - Popula `SecurityContext` com authorities derivadas de `roles`

### Configuração de Segurança

- `SecurityConfig`
  - Desabilita CSRF (API stateless)
  - `SessionCreationPolicy.STATELESS`
  - Regras:
    - `/auth/**` liberado
    - `GET /ninja/**`, `GET /missao/**`: `USER` ou `ADMIN`
    - `POST/PUT/DELETE /ninja/**` e `/missao/**`: somente `ADMIN`
  - Registra `JwtAuthenticationFilter` antes de `UsernamePasswordAuthenticationFilter`
  - `PasswordEncoder`: `BCryptPasswordEncoder`

### Endpoints de Autenticação

- `POST /auth/register`
  - Body: `{ "username": "...", "password": "...", "role": "ADMIN|USER" }`
  - Cria usuário com senha criptografada e papel informado (padrão USER)
- `POST /auth/login`
  - Body: `{ "username": "...", "password": "..." }`
  - Retorna `{ "token": "<jwt>" }`

### Como usar o token

1. Registre (opcional) e faça login para obter o token
2. Envie o token em requisições protegidas:

```
Authorization: Bearer <jwt_a_partir_do_login>
```

### Exemplos HTTP (para `requisicoes.http`)

```
### Registrar ADMIN
POST http://localhost:8080/auth/register
Content-Type: application/json

{
  "username": "admin",
  "password": "admin",
  "role": "ADMIN"
}

### Login
POST http://localhost:8080/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin"
}

### Criar Missao (requer ADMIN)
POST http://localhost:8080/missao
Authorization: Bearer {{token}}
Content-Type: application/json

{
  "nomeDaMissao": "Escoltar o Daimyo",
  "dificuldade": "B"
}

### Listar Missoes (USER ou ADMIN)
GET http://localhost:8080/missao?page=0&size=5
Authorization: Bearer {{token}}
```

### Controle de Acesso (resumo)

- USER: pode `GET` em `/ninja/**` e `/missao/**`
- ADMIN: pode `GET/POST/PUT/DELETE` em `/ninja/**` e `/missao/**`

### Dicas e Solução de Problemas

- 401 Unauthorized: token ausente/expirado/inválido → refaça login
- 403 Forbidden: papel insuficiente → use usuário ADMIN para escrita
- Mudou `security.jwt.secret`? Tokens antigos deixam de ser válidos
- Sempre use `BCrypt` para senha (já configurado em `SecurityConfig`)
