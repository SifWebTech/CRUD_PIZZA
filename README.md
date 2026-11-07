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
| Cadastrar | `CadastrarPizza`: `GET` abre `cadastrarpizza.jsp`, `POST` valida e grava |
| Listar | `ListarPizza` → `listarpizza.jsp` |
| Alterar | `AlterarPizza`: `GET` abre `alterarpizza.jsp`, `POST` valida e grava |
| Excluir | `ExcluirPizza`: `GET` abre a confirmação em `excluirpizza.jsp`, `POST` exclui |

### Regras de validação

As regras ficam em `ValidadorPizza` e são conferidas no servidor, mesmo que o
formulário do navegador seja ignorado:

| Campo | Regra |
|-------|-------|
| Nome | Obrigatório, até 60 caracteres |
| Descrição | Obrigatória, até 200 caracteres |
| Tamanho | `Pequena`, `Media` ou `Grande` |
| Preço | Maior que zero, até 99999999,99, com no máximo 2 casas decimais; aceita vírgula ou ponto |

Se algum dado for inválido, nada é gravado e a tela mostra o erro. No cadastro,
os valores digitados são mantidos no formulário.

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

Outras decisões do código:

- **Erros do banco:** o DAO lança a exceção e o Servlet mostra uma mensagem na
  tela, sem esconder a falha. `alterar` e `excluir` informam se alguma linha foi
  realmente afetada.
- **Post/Redirect/Get:** depois de gravar, o Servlet redireciona para outra
  página. Assim, atualizar a tela não repete o cadastro.
- **Mensagens:** `Mensagem` guarda o aviso na sessão e `WEB-INF/mensagens.jspf`
  mostra esse aviso uma única vez.
- **Segurança:** `Html.escapar` trata os textos exibidos nas JSPs, evitando XSS,
  e o `PreparedStatement` evita SQL Injection.
- **Comentários:** o código tem explicações linha a linha em Java, JSP, CSS, XML
  e SQL. Os comentários das JSPs usam `<%-- --%>` e não chegam ao navegador.

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
- Servidor: não precisa instalar, porque o plugin do Jetty 12 é baixado pelo Maven.
  Para usar outro servidor, ele precisa ser compatível com Jakarta Servlet 6.0,
  como o Tomcat 10.1 ou superior

## Banco de dados

Crie o banco e execute o script. A porta do projeto é 5433; se a sua instalação
usar outra, ajuste também a `URL` em `ConnectionFactory.java`:

```
createdb -U postgres -p 5433 crud_pizza
psql -U postgres -p 5433 -d crud_pizza -f database/script.sql
```

No Windows, se o `psql` não estiver no PATH, use o caminho completo:
`"C:\Program Files\PostgreSQL\18\bin\psql.exe"`.

Execute o script apenas uma vez: rodar de novo duplica as pizzas de exemplo.

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

Crie o `.env` na raiz do projeto a partir do `.env.example`:

```
DB_PASSWORD=sua_senha_do_postgres
```

A variável `DB_PASSWORD` tem prioridade sobre o `.env`. A senha é lida quando o
servidor inicia, portanto é preciso reiniciá-lo depois de alterar qualquer uma
das duas.

## Como executar

```
mvn clean package
mvn jetty:run
```

Com o arquivo `.env` criado, basta rodar `mvn jetty:run` na raiz do projeto. Sem
ele, no Windows PowerShell, defina a senha no mesmo comando:

```
$env:DB_PASSWORD='sua_senha_do_postgres'; mvn jetty:run
```

Acesse **http://localhost:8080/crud-pizza/**

Para parar o servidor, use `Ctrl+C` no terminal.

Para publicar no Tomcat 10.1 ou superior, copie `target/crud-pizza.war` para a
pasta `webapps` do servidor. Nesse caso, prefira a variável `DB_PASSWORD`, porque
o `.env` é procurado na pasta em que o servidor foi iniciado.

### Problemas comuns

| Mensagem | Causa provável | Solução |
|----------|----------------|---------|
| `Não foi possível acessar o banco de dados` ou `Não foi possível cadastrar a pizza` | Senha errada, banco desligado ou banco inexistente | Confira o `.env` ou a variável `DB_PASSWORD`, o serviço do PostgreSQL e a porta 5433, depois reinicie o servidor |
| `autenticação do tipo senha falhou` no log do PostgreSQL | O servidor iniciou sem a senha correta | Corrija a senha e reinicie. Se o terminal tiver uma `DB_PASSWORD` errada, abra um terminal novo |

## Observações sobre o projeto de referência

O projeto segue a apostila da aula (`ConnectionFactory`, `GenericDAO` +
implementação, um Servlet por operação, JSP com scriptlets), com três
diferenças em relação ao ambiente da aula:

1. **`jakarta.servlet` no lugar de `javax.servlet`** — a apostila usa `javax`,
   com NetBeans e GlassFish Server 4.0. Este projeto usa Java 17 e Jakarta
   Servlet 6.0, que não roda no GlassFish 4.0.
2. **Jetty pelo Maven** — a apostila roda o projeto pelo NetBeans. Aqui o comando
   `mvn jetty:run` sobe um servidor sem precisar instalar nada à parte. O Jetty
   foi uma escolha deste projeto e não faz parte da apostila.
3. **PostgreSQL na porta 5433** — porta da instalação local do PostgreSQL 18; a
   apostila usa 5432.

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
| 9. Documentação do layout | `feature/documentacao-layout` | Documentando integração visual e créditos dos assets |
| 10. Correções e comentários | `fix/correcoes-crud-e-comentarios` | Correções de erros, validação e segurança; comentários linha a linha |

Os merges foram feitos com `--no-ff` para que a ramificação fique visível no
histórico nas etapas principais. A etapa extra `feature/layout` foi incorporada
por fast-forward após o commit da branch, e por isso ela e
`feature/documentacao-layout` não aparecem entre as branches do GitHub, embora
seus commits estejam na `main`. Para visualizar:

```
git log --graph --oneline
```
