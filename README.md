# Iconit - teste técnico

O objetivo desse projeto é fazer, minimamente, um backend em java que simule o gerenciamento de um estoque de peças, seguindo as especificações passadas o projeto conta com duas entidades
- Products -> responsável pelos dados atuais do produto, salvo no banco de dados.
- StockHistory -> responsável por ser o histórico de movimento para cada produto, tendo os valores de venda e a quantidade.

Para fazer o gerenciamento, foi adotado o uso de três services e três controllers
- productService / productController -> para gerenciar SOMENTE ações ligadas à produtos.
- stockHistoryService / stockHistoryController -> para gerenciar SOMENTE ações ligadas ao historico do estoque.
- stockHistoryProductService / stockHistoryProductController -> para gerenciar ações que combinam ambas as entidades.

o CRUD é realizado sendo distribuido entre os 3 controllers.

A aplicação conta com Exception handler para mapear os erros e sempre retornar em um formato padrão, bem como um DTO de resposta default para manter um padrão de entrega.

Todos os métodos de Post/Put possuem validação antes de chegarem a atingir o nível do controller.

A arquitetura adotada tem o objetivo de organizar os módulos por domínio (DDD) e facilitar a leitura e navegação dentro do projeto. 

Dependências: 
- Java 21
- maven

Links:
- swagger -> http://localhost:8080/swagger-ui/index.html
- h2-console -> http://localhost:8080/h2-console/
- actuator -> http://localhost:8080/actuator