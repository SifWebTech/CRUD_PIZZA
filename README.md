# CRUD_PIZZA

Atividade pratica da disciplina de Topicos Especiais II (FATEC ABC):
CRUD em Java Web com arquitetura MVC + DAO, banco de dados e versionamento Git/GitHub.

Tema do projeto: **Pizza**.

## Pacotes

```
br.com.pizzaria
├── util         -> ConnectionFactory (conexao com o banco)
├── model        -> entidade Pizza
├── DAO          -> GenericDAO e PizzaDAOImpl
└── controller   -> Servlets do CRUD
```

A View fica em `src/main/webapp` (paginas JSP).

## Estrutura de arquivos

```
CRUD_PIZZA/
├── database/script.sql
├── pom.xml
└── src/main/
    ├── java/br/com/pizzaria/
    │   ├── util/ConnectionFactory.java
    │   ├── model/Pizza.java
    │   ├── DAO/GenericDAO.java
    │   ├── DAO/PizzaDAOImpl.java
    │   └── controller/
    │       ├── CadastrarPizza.java
    │       ├── ListarPizza.java
    │       ├── AlterarPizza.java
    │       └── ExcluirPizza.java
    └── webapp/
        ├── index.jsp
        ├── cadastrarpizza.jsp
        ├── listarpizza.jsp
        ├── alterarpizza.jsp
        ├── css/estilo.css
        └── WEB-INF/web.xml
```

## Banco de dados

PostgreSQL. Crie o banco e execute o script:

```
createdb -U postgres -p 5433 crud_pizza
psql -U postgres -p 5433 -d crud_pizza -f database/script.sql
```

Ajuste porta, usuario e senha em `util/ConnectionFactory.java` se necessario.

## Como executar

```
mvn clean package
mvn jetty:run
```

Acesse http://localhost:8080/crud-pizza

Para publicar no Tomcat 10, copie `target/crud-pizza.war` para a pasta `webapps`.

## Fluxo de branches

| Etapa | Branch |
|-------|--------|
| 1. Estrutura inicial | `main` |
| 2. Util e banco de dados | `feature/util` |
| 3. Model | `feature/model` |
| 4. DAO | `feature/dao` |
| 5. View | `feature/view` |
| 6. Controller | `feature/controller` |
