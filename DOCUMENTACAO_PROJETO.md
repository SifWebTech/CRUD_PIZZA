# Documentacao do Projeto CRUD_PIZZA

## Visao geral

Este documento registra, de forma didatica, como o projeto foi organizado de
acordo com a orientacao do professor:

```
main -> pull -> criar branch -> desenvolver -> commit -> voltar para main -> merge -> push -> proxima etapa
```

O projeto desenvolvido e um CRUD Java Web para cadastro de pizzas, usando:

- Java 17
- Maven
- JSP
- Servlets com `jakarta.servlet`
- Padrao MVC
- Camada DAO
- PostgreSQL
- Git e GitHub com branches por etapa
- Layout reaproveitado do primeiro projeto `atividade_git`

O trabalho ficou dividido em duas ideias que se complementam:

| Parte | Repositorio | Funcao |
|-------|-------------|--------|
| Primeira etapa | `atividade_git` | Site estatico institucional da Iglesias Siqueira Pizzas |
| Segunda etapa | `CRUD_PIZZA` | CRUD Java Web para administrar as pizzas no banco |

## 1. Estrutura inicial do projeto

Orientacao do professor:

- Criar o projeto Java Web.
- Organizar os pacotes iniciais:
  - `util`
  - `model`
  - `DAO`
  - `controller`
- Configurar o repositorio Git.
- Conectar o repositorio local ao GitHub.
- Trabalhar diretamente na `main`.
- Fazer o commit `Criando estrutura inicial do projeto`.
- Realizar o push para o GitHub.

Como ficou no projeto:

- O projeto Maven foi criado com empacotamento `war`.
- A estrutura principal foi organizada em:

```
src/main/java/br/com/pizzaria/
├── util
├── model
├── DAO
└── controller
```

- Tambem foi criada a estrutura web:

```
src/main/webapp/
├── WEB-INF/web.xml
└── index.jsp
```

Evidencia no Git:

```
71bc5f0 Criando estrutura inicial do projeto
```

## 2. Util e Banco de Dados

Orientacao do professor:

```bash
git checkout main
git pull
git checkout -b feature/util
```

Nesta branch deveriam ser criados:

- script SQL do banco de dados;
- tabelas necessarias;
- classe `ConnectionFactory` no pacote `util`;
- commit `Criando banco de dados e ConnectionFactory`;
- merge da branch na `main`;
- push para o GitHub.

Como ficou no projeto:

- Foi criada a branch `feature/util`.
- Foi criado o arquivo `database/script.sql`.
- O script cria a tabela `pizza`:

```sql
CREATE TABLE IF NOT EXISTS pizza (
    id_pizza        SERIAL PRIMARY KEY,
    nome_pizza      VARCHAR(60)    NOT NULL,
    descricao_pizza VARCHAR(200)   NOT NULL,
    tamanho_pizza   VARCHAR(20)    NOT NULL,
    preco_pizza     NUMERIC(10, 2) NOT NULL
);
```

- O script tambem insere dados de exemplo.
- Foi criada a classe:

```
src/main/java/br/com/pizzaria/util/ConnectionFactory.java
```

Essa classe centraliza a conexao JDBC com PostgreSQL e possui metodos para
fechar `Connection`, `PreparedStatement` e `ResultSet`.

Evidencias no Git:

```
5a1d883 Criando banco de dados e ConnectionFactory
51dc782 Merge branch 'feature/util'
```

Observacao sobre a senha:

- A senha do PostgreSQL nao fica gravada diretamente no codigo.
- O projeto le a senha pela variavel de ambiente `DB_PASSWORD` ou pelo arquivo local `.env`.
- O arquivo `.env` esta no `.gitignore`, por isso nao deve ser enviado ao GitHub.
- Se a variavel nao for informada, o valor padrao usado e `postgres`.

Exemplo no PowerShell:

```powershell
$env:DB_PASSWORD="sua_senha_do_postgres"
mvn jetty:run
```

Exemplo de arquivo `.env` local:

```env
DB_PASSWORD=sua_senha_do_postgres
```

## 3. Model

Orientacao do professor:

```bash
git checkout main
git pull
git checkout -b feature/model
```

Nesta etapa deveria ser criada a classe que representa a entidade principal do
projeto, com atributos, construtores, getters e setters.

Como ficou no projeto:

- Foi criada a branch `feature/model`.
- Foi criada a classe:

```
src/main/java/br/com/pizzaria/model/Pizza.java
```

A classe `Pizza` representa a tabela `pizza` do banco de dados e possui os
seguintes atributos:

- `idPizza`
- `nomePizza`
- `descricaoPizza`
- `tamanhoPizza`
- `precoPizza`

Tambem foram implementados:

- construtor vazio;
- construtor sem id;
- construtor completo;
- getters e setters;
- metodo `toString`.

Evidencias no Git:

```
71aa778 Criando model do sistema
418fea2 Merge branch 'feature/model'
```

## 4. DAO

Orientacao do professor:

```bash
git checkout main
git pull
git checkout -b feature/dao
```

Nesta etapa deveria ser desenvolvida a camada DAO, responsavel pela comunicacao
entre a aplicacao e o banco de dados.

Operacoes exigidas:

- Cadastrar
- Listar
- Alterar
- Excluir

Como ficou no projeto:

- Foi criada a branch `feature/dao`.
- Foi criada a interface:

```
src/main/java/br/com/pizzaria/DAO/GenericDAO.java
```

- Foi criada a implementacao:

```
src/main/java/br/com/pizzaria/DAO/PizzaDAOImpl.java
```

A classe `PizzaDAOImpl` implementa:

- `cadastrar`
- `listar`
- `alterar`
- `excluir`
- `buscarPorId`

O metodo `buscarPorId` foi adicionado para carregar os dados atuais da pizza na
tela de alteracao.

As consultas SQL usam `PreparedStatement`, evitando concatenacao direta de dados
do usuario nas instrucoes SQL.

Evidencias no Git:

```
224b3f4 Criando estrutura do DAO
0e70f6e Implementando cadastro
4e1b4cd Implementando listagem
c60a9ba Implementando alteracao
12dda50 Implementando exclusao
8ef9ccd Merge branch 'feature/dao'
```

## 5. View

Orientacao do professor:

```bash
git checkout main
git pull
git checkout -b feature/view
```

Nesta etapa deveriam ser criadas as paginas de interface do sistema, usando JSP.

Como ficou no projeto:

- Foi criada a branch `feature/view`.
- Foram criadas as telas:

```
src/main/webapp/index.jsp
src/main/webapp/cadastrarpizza.jsp
src/main/webapp/listarpizza.jsp
src/main/webapp/alterarpizza.jsp
src/main/webapp/css/estilo.css
```

Funcao de cada tela:

- `index.jsp`: menu inicial do sistema.
- `cadastrarpizza.jsp`: formulario de cadastro.
- `listarpizza.jsp`: tabela com as pizzas cadastradas e links de alterar/excluir.
- `alterarpizza.jsp`: formulario preenchido com os dados atuais da pizza.
- `css/estilo.css`: estilos visuais da aplicacao.

Evidencias no Git:

```
8e5ea60 Criando pagina de cadastro
dc7d79f Criando pagina de listagem
5b03cf5 Criando pagina de alteracao
9a57af2 Merge branch 'feature/view'
```

## 6. Controller

Orientacao do professor:

```bash
git checkout main
git pull
git checkout -b feature/controller
```

Nesta etapa deveriam ser criados os Servlets responsaveis por controlar as
operacoes do CRUD, fazendo a comunicacao entre View e DAO.

Como ficou no projeto:

- Foi criada a branch `feature/controller`.
- Foram criados os Servlets:

```
src/main/java/br/com/pizzaria/controller/CadastrarPizza.java
src/main/java/br/com/pizzaria/controller/ListarPizza.java
src/main/java/br/com/pizzaria/controller/AlterarPizza.java
src/main/java/br/com/pizzaria/controller/ExcluirPizza.java
```

Responsabilidade de cada controller:

- `CadastrarPizza`: recebe dados do formulario e envia ao DAO para insercao.
- `ListarPizza`: busca as pizzas no DAO e encaminha para a JSP de listagem.
- `AlterarPizza`: carrega a pizza por id e grava as alteracoes.
- `ExcluirPizza`: exclui a pizza selecionada e retorna para a listagem.

Evidencias no Git:

```
c265ab1 Implementando controller de cadastro
441ae88 Implementando controller de listagem
e3b173b Implementando controller de alteracao
0e0a741 Implementando controller de exclusao
efb6a63 Merge branch 'feature/controller'
```

## 7. Integracao visual com o primeiro projeto

Depois que o CRUD completo ja estava funcional, foi realizada uma etapa extra
para aproximar visualmente este projeto do primeiro trabalho da disciplina.

Objetivo da etapa:

- reaproveitar a identidade visual da pizzaria criada no projeto `atividade_git`;
- mostrar que o primeiro projeto e a vitrine/cardapio da pizzaria;
- mostrar que o CRUD e a area administrativa da mesma pizzaria;
- deixar as paginas do CRUD mais apresentaveis para a entrega;
- criar uma tela propria para confirmar exclusao.

Fluxo Git usado nesta etapa:

```powershell
git checkout main
git pull
git checkout -b feature/layout
```

Arquivos e recursos reaproveitados do primeiro projeto:

```
src/main/webapp/imagens/
src/main/webapp/css/fontes/UnifrakturMaguntia.woff2
```

Os creditos e a origem dos assets visuais foram registrados em:

```
CREDITOS_ASSETS.md
```

Paginas atualizadas:

```
src/main/webapp/index.jsp
src/main/webapp/cadastrarpizza.jsp
src/main/webapp/listarpizza.jsp
src/main/webapp/alterarpizza.jsp
src/main/webapp/excluirpizza.jsp
src/main/webapp/css/estilo.css
```

Como cada pagina ficou:

- `index.jsp`: passou a ser o painel administrativo da Iglesias Siqueira Pizzas.
- `cadastrarpizza.jsp`: recebeu o layout visual da pizzaria e formulario estilizado.
- `listarpizza.jsp`: recebeu tabela administrativa com botoes de alterar e excluir.
- `alterarpizza.jsp`: recebeu o mesmo padrao visual do cadastro.
- `excluirpizza.jsp`: nova pagina para confirmar a exclusao antes de remover o registro.
- `css/estilo.css`: foi refeito com a paleta, cabecalho, rodape, botoes, tabela e formularios.

Alteracao no controller de exclusao:

- Antes, o link de excluir removia a pizza diretamente.
- Agora, o metodo `GET` do servlet `ExcluirPizza` carrega a pizza e abre `excluirpizza.jsp`.
- A exclusao real acontece apenas no metodo `POST`, depois da confirmacao.

Commit da etapa:

```
4a26ec4 Integrando layout da pizzaria ao CRUD
```

Merge e push realizados:

```powershell
git checkout main
git merge feature/layout
git push
```

Resultado:

- a branch `main` recebeu o layout integrado;
- o GitHub foi atualizado;
- a aplicacao manteve o CRUD funcional;
- o sistema ficou visualmente alinhado ao primeiro projeto.

## 8. Testes e finalizacao

Orientacao do professor:

```bash
git checkout main
git pull
```

Depois, executar e testar:

```
Cadastrar -> Listar -> Alterar -> Excluir
```

Como testar o projeto:

1. Entrar na pasta do projeto:

```powershell
cd "D:\0 - FATEC\0001 - ABC - fatec - todos arquivos\5º Semestre - Matérias\6ª - Tópicos Especiais II - ANDRÉ MUSSATO\CRUD_PIZZA"
```

2. Confirmar que esta na `main`:

```powershell
git status
```

Resultado esperado:

```
On branch main
Your branch is up to date with 'origin/main'.
nothing to commit, working tree clean
```

3. Preparar o banco de dados:

```powershell
$env:PGPASSWORD="sua_senha_do_postgres"
& "C:\Program Files\PostgreSQL\18\bin\psql.exe" -h localhost -p 5433 -U postgres -d postgres -c "CREATE DATABASE crud_pizza;"
& "C:\Program Files\PostgreSQL\18\bin\psql.exe" -h localhost -p 5433 -U postgres -d crud_pizza -f database\script.sql
```

4. Definir a senha para a aplicacao:

```powershell
$env:DB_PASSWORD="sua_senha_do_postgres"
```

Tambem e possivel criar um arquivo `.env` na raiz do projeto:

```env
DB_PASSWORD=sua_senha_do_postgres
```

5. Rodar o projeto:

```powershell
mvn jetty:run
```

6. Acessar no navegador:

```
http://localhost:8080/crud-pizza/
```

7. Testar as funcionalidades:

- Cadastrar uma nova pizza.
- Listar as pizzas cadastradas.
- Alterar uma pizza.
- Excluir uma pizza.

Testes tecnicos realizados depois do layout:

```text
HOME=200
CSS=200
LISTAR=200
ALTERAR=200
EXCLUIR=200
BANCO_LISTAGEM=ok
```

Esses testes confirmaram que:

- a pagina inicial carregou;
- o CSS novo foi servido corretamente;
- a listagem continuou conectando ao banco;
- a tela de alteracao abriu corretamente;
- a nova tela de exclusao abriu corretamente;
- o banco retornou a pizza `Margherita` na listagem.

## Conferencia do historico Git

Comando para visualizar o fluxo completo:

```bash
git log --graph --oneline --all
```

O historico do projeto mostra:

```
71bc5f0 Criando estrutura inicial do projeto
51dc782 Merge branch 'feature/util'
418fea2 Merge branch 'feature/model'
8ef9ccd Merge branch 'feature/dao'
9a57af2 Merge branch 'feature/view'
efb6a63 Merge branch 'feature/controller'
9d3a6b3 Documentando fluxo do projeto e configuracao local do banco
4a26ec4 Integrando layout da pizzaria ao CRUD
```

Branches existentes no remoto:

```
origin/feature/util
origin/feature/model
origin/feature/dao
origin/feature/view
origin/feature/controller
origin/main
```

Portanto, o fluxo solicitado foi atendido:

```
main -> branch -> desenvolvimento -> commit -> merge -> main
```

## Confirmacao tecnica realizada

Durante a verificacao local:

- O banco `crud_pizza` foi criado no PostgreSQL.
- O script `database/script.sql` foi executado.
- A tabela `pizza` foi criada.
- Foram inseridos 4 registros de exemplo.
- O projeto foi compilado pelo Maven.
- O Jetty iniciou a aplicacao no contexto `/crud-pizza`.
- A pagina inicial respondeu HTTP `200`.
- A rota de listagem respondeu HTTP `200`.
- O layout integrado respondeu HTTP `200` nas paginas principais.
- A tela de exclusao passou a ter confirmacao antes do `POST`.

## Roteiro para apresentacao HTML

Esta documentacao pode ser transformada em uma apresentacao HTML didatica. A
estrutura sugerida da apresentacao e:

1. **Capa**
   - Nome do projeto: CRUD_PIZZA.
   - Disciplina: Topicos Especiais II.
   - Professor: Andre Mussato.
   - Aluno: Felipe Iglesias.

2. **Objetivo da atividade**
   - Vivenciar o fluxo Git com `main`, branches, commits, merges e push.
   - Desenvolver um CRUD Java Web completo.

3. **Primeiro projeto: site da pizzaria**
   - Mostrar que `atividade_git` foi a vitrine da Iglesias Siqueira Pizzas.
   - Destacar identidade visual: brasao, cores, imagens e cardapio.

4. **Segundo projeto: CRUD_PIZZA**
   - Explicar que o CRUD e a area administrativa da mesma pizzaria.
   - Mostrar tecnologias: Java 17, Maven, JSP, Servlets, DAO e PostgreSQL.

5. **Fluxo Git exigido**
   - `main -> pull -> branch -> desenvolvimento -> commit -> merge -> push`.
   - Mostrar as branches `feature/util`, `feature/model`, `feature/dao`,
     `feature/view`, `feature/controller` e `feature/layout`.

6. **Etapas de desenvolvimento**
   - Estrutura inicial.
   - Util e banco.
   - Model.
   - DAO.
   - View.
   - Controller.
   - Layout integrado.

7. **Banco de dados**
   - Tabela `pizza`.
   - Campos principais.
   - Script `database/script.sql`.
   - Configuracao segura com `.env`.

8. **CRUD funcionando**
   - Cadastrar pizza.
   - Listar pizzas.
   - Alterar pizza.
   - Confirmar exclusao.

9. **Evidencias**
   - Commits.
   - Merges.
   - Push para GitHub.
   - Testes HTTP `200`.

10. **Creditos visuais**
    - Informar que os assets vieram do primeiro projeto.
    - Mostrar que os creditos das fotos estao registrados em `CREDITOS_ASSETS.md`.

11. **Conclusao**
    - A `main` contem o projeto completo.
    - O CRUD esta funcional.
    - O layout ficou alinhado ao primeiro projeto.
    - O fluxo Git foi praticado conforme a orientacao.

## Conclusao

O projeto esta de acordo com a orientacao do professor:

- possui CRUD completo;
- usa Java Web com JSP e Servlet;
- segue MVC + DAO;
- usa PostgreSQL;
- reaproveita o layout do primeiro projeto da pizzaria;
- possui branches por etapa;
- possui commits claros;
- possui merges das etapas na `main`;
- a `main` contem o projeto completo e funcional.
