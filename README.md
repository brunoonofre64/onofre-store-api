# Integration-Test Project 👨🏻‍💻
## JAVA ☕ + SpringBoot 🍃
### Aqui neste projeto, foi desenvolvida uma RESTFUL API, onde pude usar as melhores boas práticas de desenvolvimento que possuo conhecimento, tais como:

* Orientação ao Objeto
* Data Transfer Object (DTO)
* Padrão Repository
* Clean Code
* Separação da regra de negócio dos casos de uso
* Tratamento personalizado de erros da API com Exception Handling
* Teste de integração
* Liquibase, para gerenciar a criação e exclusão de tabelas na minha cama de teste de integração
* Externalização das menssagem de erro lançadas pela API
* E etc..

### Teste de integração passo a passo e dica de boas práticas! 📝

##### É uma boa prática, que ao criar nossa camada de testes de integração, criarmos um caminho igual ao da nossa camada *****main*****, preservando o design da estrutura, que futuramente facilitará localizar os testes.
###### Exemplo abaixo:
###### Camada de Teste de Integração:

![Camada de teste de integração](images/integracao.jpg)

###### Camada main:

![Camada main](images/main.jpg)

##### OBS: Perceba que ambas fizeram o mesmo caminho até chegar no controller.



 
