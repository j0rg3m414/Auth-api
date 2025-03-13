# Auth-api
Implement authentication on a web api



Implementação de autenticação JSON Web Token em uma aplicação Spring Boot. 
Aqui estão as principais etapas desse processo:

- Filtro de autenticação JWT para extrair e validar o token do cabeçalho do pedido.
- Colocar na lista de permissões algumas rotas de API e proteger aquelas que exigem um token.
- Executar a autenticação, gerar o JWT e definir um tempo de expiração.
- Utilizar o JWT gerado para aceder a rotas protegidas.
- Detetar exceções de autenticação para personalizar a resposta enviada ao cliente.

Implementação de Role-based Acces Control (RBAC)
