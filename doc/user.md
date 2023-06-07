#User API specs

##Register User

Endpoint : POST /register

Request Body:
```json
{
  "username": "anggi",
  "password": "admin",
  "nama": "anggi saputra",
  "email": "anggis@aja.com",
  "saldo": "10000"
}
```

Response body (Success):

```json
{
  "code": "SS",
  "status": "OK",
  "message": "SUCCESS UPDATE",
  "data": "..."
}
```
##Login User to get token