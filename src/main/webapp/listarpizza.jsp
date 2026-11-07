<%-- Diretiva da JSP: importa a classe que protege os textos exibidos. --%>
<%@page import="br.com.pizzaria.util.Html"%>
<%-- Diretiva da JSP: importa a classe Pizza para o código Java da página. --%>
<%@page import="br.com.pizzaria.model.Pizza"%>
<%-- Diretiva da JSP: importa a interface List do Java. --%>
<%@page import="java.util.List"%>
<%-- Diretiva da JSP: a página gera HTML e o arquivo usa UTF-8. --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Informa ao navegador que o documento é HTML5. --%>
<!DOCTYPE html>
<%-- Início do HTML; lang informa que o idioma é português do Brasil. --%>
<html lang="pt-BR">
    <%-- Início do cabeçalho técnico da página, que não aparece no conteúdo. --%>
    <head>
        <%-- Define a codificação UTF-8, para os acentos aparecerem certos. --%>
        <meta charset="UTF-8">
        <%-- Ajusta a largura da página à tela do aparelho. --%>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%-- Texto mostrado na aba do navegador. --%>
        <title>Pizzas Cadastradas - Iglesias Siqueira Pizzas</title>
        <%-- Define o ícone da aba do navegador. --%>
        <link rel="icon" href="imagens/logo.svg" type="image/svg+xml">
        <%-- Liga a página ao arquivo de estilos CSS. --%>
        <link rel="stylesheet" href="css/estilo.css">
    </head>
    <%-- Início do conteúdo visível da página. --%>
    <body>
        <%-- Cabeçalho visível do site. --%>
        <header>
            <%-- Caixa que organiza a marca e o menu no topo. --%>
            <div class="topo">
                <%-- Caixa que agrupa o brasão e o nome da pizzaria. --%>
                <div class="marca">
                    <%-- Exibe o brasão; alt é o texto alternativo da imagem. --%>
                    <img src="imagens/logo.svg" alt="Brasão da Iglesias Siqueira Pizzas">
                    <%-- Caixa que agrupa o nome e o slogan. --%>
                    <div>
                        <%-- Nome da pizzaria no topo. --%>
                        <p class="marca-nome">Iglesias Siqueira Pizzas</p>
                        <%-- Slogan da pizzaria no topo. --%>
                        <p class="marca-slogan">Espanha e Portugal fazendo as melhores pizzas da Itália</p>
                    </div>
                </div>

                <%-- Menu de navegação do site. --%>
                <nav>
                    <%-- Link do menu para a página inicial. --%>
                    <a href="index.jsp">Início</a>
                    <%-- Link do menu para o formulário de cadastro. --%>
                    <a href="cadastrarpizza.jsp">Cadastrar</a>
                    <%-- Link do menu para o Servlet que lista as pizzas. class="ativo" destaca a página atual. --%>
                    <a href="ListarPizza" class="ativo">Listar</a>
                </nav>
            </div>
        </header>

        <%-- Faixa com o título da página. --%>
        <section class="titulo-pagina">
            <%-- Título principal da página. --%>
            <h1>Pizzas Cadastradas</h1>
            <%-- Parágrafo de texto. --%>
            <p>Consulte, altere ou remova sabores do banco de dados</p>
        </section>

        <%-- Área principal da página. --%>
        <main>
            <%-- Seção com o conteúdo principal. --%>
            <section>
                <%-- Título da seção. --%>
                <h2>Cardápio administrativo</h2>
                <%-- Insere aqui o arquivo que mostra as mensagens de sucesso e de erro. --%>
                <%@ include file="/WEB-INF/mensagens.jspf" %>

                <%-- Caixa que agrupa os botões. --%>
                <div class="botoes">
                    <%-- Botão que abre o formulário de cadastro. --%>
                    <a class="botao" href="cadastrarpizza.jsp">Cadastrar nova pizza</a>
                </div>

                <%-- Caixa que permite rolar a tabela para o lado em telas pequenas. --%>
                <div class="tabela-rolagem">
                    <%-- Início da tabela de pizzas. --%>
                    <table>
                        <%-- Cabeçalho da tabela. --%>
                        <thead>
                            <%-- Início de uma linha da tabela. --%>
                            <tr>
                                <%-- Título da coluna de código. --%>
                                <th>Código</th>
                                <%-- Título da coluna de nome. --%>
                                <th>Nome</th>
                                <%-- Título da coluna de descrição. --%>
                                <th>Descrição</th>
                                <%-- Título da coluna de tamanho. --%>
                                <th>Tamanho</th>
                                <%-- Título da coluna de preço. --%>
                                <th>Preço</th>
                                <%-- Título da coluna de ações. --%>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <%-- Corpo da tabela, com os dados. --%>
                        <tbody>
                            <%-- Abre um bloco de código Java dentro da página (scriptlet). --%>
                            <%
                                // Pega a lista de pizzas colocada na requisição pelo Servlet.
                                List<Pizza> pizzas = (List<Pizza>) request.getAttribute("pizzas");

                                // A lista só fica null quando houve erro ao acessar o banco.
                                if (pizzas == null) {
                            %>
                            <%-- Início de uma linha da tabela. --%>
                            <tr>
                                <%-- Linha de aviso; colspan faz a célula ocupar as 6 colunas. --%>
                                <td colspan="6">Não foi possível carregar as pizzas.</td>
                            </tr>
                            <%-- Abre um bloco de código Java dentro da página (scriptlet). --%>
                            <%
                                // Se a lista tem pizzas, monta uma linha para cada uma.
                                } else if (!pizzas.isEmpty()) {
                                    // Repete o HTML abaixo para cada pizza da lista.
                                    for (Pizza pizza : pizzas) {
                            %>
                            <%-- Início de uma linha da tabela. --%>
                            <tr>
                                <%-- Célula com o código da pizza. --%>
                                <td><%= pizza.getIdPizza()%></td>
                                <%-- Célula com o nome, com Html.escapar protegendo o texto. --%>
                                <td><strong><%= Html.escapar(pizza.getNomePizza())%></strong></td>
                                <%-- Célula com a descrição, com Html.escapar protegendo o texto. --%>
                                <td><%= Html.escapar(pizza.getDescricaoPizza())%></td>
                                <%-- Célula com o tamanho, com Html.escapar protegendo o texto. --%>
                                <td><%= Html.escapar(pizza.getTamanhoPizza())%></td>
                                <%-- Célula com o preço, com Html.escapar protegendo o texto. --%>
                                <td class="preco">R$ <%= Html.escapar(pizza.getPrecoPizza())%></td>
                                <%-- Célula com os botões de ação. --%>
                                <td>
                                    <%-- Caixa que agrupa os botões Alterar e Excluir da linha. --%>
                                    <div class="acoes-tabela">
                                        <%-- Link que abre a alteração, levando o código da pizza na URL. --%>
                                        <a class="alterar" href="AlterarPizza?idPizza=<%= pizza.getIdPizza()%>">Alterar</a>
                                        <%-- Link que abre a confirmação de exclusão, levando o código na URL. --%>
                                        <a class="excluir" href="ExcluirPizza?idPizza=<%= pizza.getIdPizza()%>">Excluir</a>
                                    </div>
                                </td>
                            </tr>
                            <%-- Abre um bloco de código Java dentro da página (scriptlet). --%>
                            <%
                                    }
                                // Se o teste anterior falhou, mostra o conteúdo abaixo.
                                } else {
                            %>
                            <%-- Início de uma linha da tabela. --%>
                            <tr>
                                <%-- Linha de aviso; colspan faz a célula ocupar as 6 colunas. --%>
                                <td colspan="6">Nenhuma pizza cadastrada até o momento.</td>
                            </tr>
                            <%-- Abre um bloco de código Java dentro da página (scriptlet). --%>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </section>
        </main>

        <%-- Rodapé da página. --%>
        <footer>
            <%-- Nome da pizzaria no rodapé. --%>
            <p class="marca-rodape">Iglesias Siqueira Pizzas</p>
            <%-- Slogan da pizzaria no rodapé. --%>
            <p class="lema-rodape">Espanha e Portugal fazendo as melhores pizzas da Itália</p>
            <%-- Identificação do aluno e da disciplina. --%>
            <p class="projeto">Felipe Iglesias Siqueira - 5º Semestre Sistemas para Internet - Prof. André Mussato</p>
        </footer>
    </body>
</html>
