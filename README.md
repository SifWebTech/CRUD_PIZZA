# CRUD_PIZZA

Atividade prática da disciplina **Tópicos Especiais II** — FATEC ABC
(prof. André Mussato).

CRUD em **Java Web** com arquitetura **MVC + DAO**, conexão com banco de dados
e versionamento com Git/GitHub.

- **Tema:** Pizza
- **Aluno:** Felipe Iglesias

## Funcionalidades

A aplicação permite as quatro operações sobre a entidade `Pizza`:

| Operação | Tela / rota |
|----------|-------------|
| Cadastrar | `cadastrarpizza.jsp` → `CadastrarPizza` |
| Listar | `ListarPizza` → `listarpizza.jsp` |
| Alterar | `AlterarPizza` → `alterarpizza.jsp` |
| Excluir | `ExcluirPizza` → `excluirpizza.jsp` → volta para a listagem |

## Arquitetura

```
br.com.pizzaria
├── util         -> ConnectionFactory (conexão), Html (escape), Mensagem e ValidadorPizza
├── model        -> Pizza: entidade do projeto
├── DAO          -> GenericDAO (interface) e PizzaDAOImpl (implementação)
└── controller   -> um Servlet para cada operação do CRUD
```

A **View** são páginas JSP em `src/main/webapp`. O fluxo segue sempre
View → Controller → DAO → Banco, e o retorno faz o caminho inverso.

O layout do CRUD foi integrado ao visual do primeiro projeto da disciplina,
o site estático **Iglesias Siqueira Pizzas**. Assim, o primeiro trabalho funciona
como vitrine/cardápio institucional e este projeto funciona como área
administrativa para gerenciar as pizzas no banco de dados.

## Estrutura de arquivos

```
CRUD_PIZZA/
├── database/script.sql
├── pom.xml
└── src/main/
    ├── java/br/com/pizzaria/
    │   ├── util/ConnectionFactory.java
    │   ├── util/Html.java
    │   ├── util/Mensagem.java
    │   ├── util/ValidadorPizza.java
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
        ├── excluirpizza.jsp
        ├── imagens/
        ├── css/estilo.css
        ├── css/fontes/
        └── WEB-INF/
            ├── web.xml
            └── mensagens.jspf
```

## Requisitos

- Java 17 ou superior
- Maven 3.9+
- PostgreSQL
- Tomcat 10+ (opcional — o projeto roda com o plugin do Jetty)

## Banco de dados

Crie o banco e execute o script (ajuste a porta se a sua instalação usar 5432):

```
createdb -U postgres -p 5433 crud_pizza
psql -U postgres -p 5433 -d crud_pizza -f database/script.sql
```

No Windows, se o `psql` não estiver no PATH, use o caminho completo:
`"C:\Program Files\PostgreSQL\18\bin\psql.exe"`.

A tabela criada é:

```sql
CREATE TABLE pizza (
    id_pizza        SERIAL PRIMARY KEY,
    nome_pizza      VARCHAR(60)    NOT NULL,
    descricao_pizza VARCHAR(200)   NOT NULL,
    tamanho_pizza   VARCHAR(20)    NOT NULL,
    preco_pizza     NUMERIC(10, 2) NOT NULL
);
```

Usuário, porta e nome do banco ficam em
`src/main/java/br/com/pizzaria/util/ConnectionFactory.java`.
A senha deve ser informada pela variável de ambiente `DB_PASSWORD` ou pelo
arquivo local `.env`. O `.env` fica no `.gitignore`, portanto não é enviado ao
GitHub. Se nenhuma configuração for definida, o projeto usa `postgres` como
valor padrão para ambiente local.

Exemplo de `.env`:

```
DB_PASSWORD=sua_senha_do_postgres
```

## Como executar

```
mvn clean package
mvn jetty:run
```

No Windows PowerShell, caso sua senha do PostgreSQL não seja `postgres`, defina
a variável antes de iniciar o Jetty ou crie o arquivo `.env` na raiz do projeto:

```
$env:DB_PASSWORD="sua_senha_do_postgres"
mvn jetty:run
```

Acesse **http://localhost:8080/crud-pizza**

Para publicar no Tomcat 10, copie `target/crud-pizza.war` para a pasta `webapps`
do servidor.

## Observações sobre o projeto de referência

O projeto segue a apostila da aula (`ConnectionFactory`, `GenericDAO` +
implementação, um Servlet por operação, JSP com scriptlets), com duas
diferenças necessárias para rodar no ambiente usado:

1. **`jakarta.servlet` no lugar de `javax.servlet`** — a apostila usa NetBeans
   com Tomcat 9. Aqui o servidor é o Tomcat 10, que adotou o namespace
   `jakarta` e não reconhece mais `javax`.
2. **PostgreSQL na porta 5433** — porta da instalação local do PostgreSQL 18.

Também foi acrescentado o método `buscarPorId` à interface `GenericDAO`, usado
para carregar os dados atuais no formulário de alteração.

## Integração com o primeiro projeto

Depois da conclusão do CRUD funcional, foi criada uma etapa extra de layout para
aproximar este projeto do primeiro trabalho entregue na disciplina:

- repositório de referência: `https://github.com/SifWebTech/atividade_git`;
- identidade visual reaproveitada: nome, brasão, cores, fonte e imagens;
- assets copiados para `src/main/webapp/imagens` e `src/main/webapp/css/fontes`;
- telas JSP atualizadas com cabeçalho, navegação, títulos e rodapé da pizzaria;
- criação da tela `excluirpizza.jsp`, deixando a exclusão com confirmação visual;
- ajuste do servlet `ExcluirPizza` para abrir a confirmação no `GET` e excluir no `POST`.

Com isso, os dois trabalhos ficam conectados conceitualmente:

| Projeto | Papel |
|---------|-------|
| `atividade_git` | Site estático institucional/cardápio da pizzaria |
| `CRUD_PIZZA` | Sistema Java Web para administrar o cardápio no banco |

Os créditos dos assets visuais usados na integração ficam registrados em
`CREDITOS_ASSETS.md`.

## Fluxo de branches

Cada etapa foi desenvolvida em uma branch própria e incorporada à `main` por
meio de merge, conforme o enunciado da atividade:

| Etapa | Branch | Commits |
|-------|--------|---------|
| 1. Estrutura inicial | `main` | Criando estrutura inicial do projeto |
| 2. Util e banco de dados | `feature/util` | Criando banco de dados e ConnectionFactory |
| 3. Model | `feature/model` | Criando model do sistema |
| 4. DAO | `feature/dao` | Estrutura do DAO, cadastro, listagem, alteração, exclusão |
| 5. View | `feature/view` | Páginas de cadastro, listagem e alteração |
| 6. Controller | `feature/controller` | Controllers de cadastro, listagem, alteração e exclusão |
| 7. Documentação técnica | `main` | Documentando fluxo do projeto e configuração local do banco |
| 8. Layout integrado | `feature/layout` | Integrando layout da pizzaria ao CRUD |

Os merges foram feitos com `--no-ff` para que a ramificação fique visível no
histórico nas etapas principais. A etapa extra `feature/layout` foi incorporada
por fast-forward após o commit da branch. Para visualizar:

```
git log --graph --oneline
```
