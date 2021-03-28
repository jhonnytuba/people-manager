# people-manager

O projeto está dividido em três módulos: Docker,  Backend (Java / Spring Boot) e Frontend (Angular),
ambos utilizando as suas versões mais recentes até o momento.

### Docker
* Disponível em: `docker pull jhonnytuba/people-manager:latest`
* Possui os bashes: `build-image.sh` `build-and-run-image.sh` `build-and-deploy-image.sh`
* Para rodar: `docker run -p 8081:80 jhonnytuba/people-manager`

### Backend
##### Swagger
* Disponível em: http://localhost:8080/swagger-ui/
* Possui os serviços:
  `CRUD /v1/people`
  `CRUD /v2/people`
  `GET /source`
* Está utilizando o banco de dados em memória H2.
* Para utilizar os CRUDs é necessário passar o header da autenticação base64(user:user):
  `Authorization: Basic dXNlcjp1c2Vy`
  
### Frontend
* Disponível em: http://localhost:4200/
* Possui as rotas: `/#/people` `/#/source` 
* Existe um interceptor que sempre envia o login base64(user:user).
