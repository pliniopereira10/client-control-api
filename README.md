# Client-Control-Api

> Projeto visa demonstrar conhecimentos adqueridos para implantação de um **CRUD** de **web service REST**, a fim de acessar um recurso de clientes.  **API** criada utilizando recursos do ecossistema **Spring**. Está com um ambiente configurado acessando o banco de dados **H2**, usando gerenciador de dependência **Maven** e **Java 11** como linguagem. 

## 💎 Modelo UML da entidade

![uml-client-entity](/media/pliniopsp/Compartilhamento/CursosDevSuperior/BootCamp_Spring_3.0/imagens/imagens-anotacoes/uml-client-entity.png)

## 👣 Passo-a-Passo

1. Criação do projeto com **Spring Boot**, adicionando as seguintes dependências:
   * Spring Boot DevTools
   * Validation
   * Spring Data JPA
   * H2 Database
   * Spring Web
2. Implementação da entidade de domínio **ClientModel**.
   * Serializable
   * Atributos básicos
   * Construtores
   * Getters e Setters
   * HashCode e Equals
3. Mapeamento objeto-relacional **JPA** na entidade.
4. Configuração do arquivo `application.properties` para acesso ao ***ambiente de teste*** no **banco de dados H2**.
5. Criação do arquivo `application-test.properties`  e configurado para o banco de dados **H2** com acesso em memória.
6. Construção dos scripts **SQL** para povoar o **banco de dados** no  arquivo `import.sql`.
7. Criação do JpaRepository para a entidade(*camada de acesso a dados* **interface extends JpaRepository** ).
8. Criação da camada DTO,  reponsável para carregar os dados entre o ***controlador Rest e Serviço***.
9. Criação da camada controller com os endpoints:
   * GET/clients
   * GET/clients/{id}
   * POST/clients
   * PUT/clients/{id}
   * DELETE/clients/{id}
10. Criação da camada de serviços