<%-- Diretiva da JSP: importa a classe que protege os textos exibidos. --%>
<%@page import="br.com.pizzaria.util.Html"%>
<%-- Diretiva da JSP: importa a classe Pizza para o código Java da página. --%>
<%@page import="br.com.pizzaria.model.Pizza"%>
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
        <title>Excluir Pizza - Iglesias Siqueira Pizzas</title>
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
                    <%-- Link do menu para o Servlet que lista as pizzas. --%>
                    <a href="ListarPizza">Listar</a>
                </nav>
            </div>
        </header>

        <%-- Faixa com o título da página. --%>
        <section class="titulo-pagina">
            <%-- Título principal da página. --%>
            <h1>Excluir Pizza</h1>
            <%-- Parágrafo de texto. --%>
            <p>Confirme a remoção antes de atualizar o cardápio</p>
        </section>

        <%-- Área principal da página. --%>
        <main>
            <%-- Seção com o conteúdo principal. --%>
            <section>
                <%-- Título da seção. --%>
                <h2>Confirmação</h2>

                <%-- Abre um bloco de código Java dentro da página (scriptlet). --%>
                <%
                    // Pega a pizza colocada na requisição pelo Servlet e converte para Pizza.
                    Pizza pizza = (Pizza) request.getAttribute("pizza");
                    // Testa se a pizza existe; se existir, mostra o conteúdo abaixo.
                    if (pizza != null) {
                %>

                <%-- Caixa com os dados da pizza a ser excluída. --%>
                <div class="confirmacao">
                    <%-- Parágrafo de texto. --%>
                    <p>Você está prestes a excluir esta pizza do banco de dados.</p>

                    <%-- Lista de descrições: cada dt é um rótulo e cada dd é o valor dele. --%>
                    <dl>
                        <%-- Rótulo: Código. --%>
                        <dt>Código</dt>
                        <%-- Mostra o código da pizza. --%>
                        <dd><%= pizza.getIdPizza()%></dd>
                        <%-- Rótulo: Nome. --%>
                        <dt>Nome</dt>
                        <%-- Mostra o nome da pizza, com Html.escapar protegendo o texto. --%>
                        <dd><%= Html.escapar(pizza.getNomePizza())%></dd>
                        <%-- Rótulo: Descrição. --%>
                        <dt>Descrição</dt>
                        <%-- Mostra a descrição, com Html.escapar protegendo o texto. --%>
                        <dd><%= Html.escapar(pizza.getDescricaoPizza())%></dd>
                        <%-- Rótulo: Tamanho. --%>
                        <dt>Tamanho</dt>
                        <%-- Mostra o tamanho, com Html.escapar protegendo o texto. --%>
                        <dd><%= Html.escapar(pizza.getTamanhoPizza())%></dd>
                        <%-- Rótulo: Preço. --%>
                        <dt>Preço</dt>
                        <%-- Mostra o preço, com Html.escapar protegendo o texto. --%>
                        <dd>R$ <%= Html.escapar(pizza.getPrecoPizza())%></dd>
                    </dl>

                    <%-- Formulário que confirma a exclusão, enviando para o Servlet com post. --%>
                    <form action="ExcluirPizza" method="post">
                        <%-- Campo oculto: envia o código da pizza sem mostrá-lo na tela. --%>
                        <input type="hidden" name="idPizza" value="<%= pizza.getIdPizza()%>">
                        <%-- Caixa que agrupa os botões. --%>
                        <div class="botoes">
                            <%-- Botão que confirma a exclusão, enviando o formulário. --%>
                            <input class="perigo" type="submit" value="Confirmar exclusão">
                            <%-- Botão que volta para a listagem. --%>
                            <a class="botao secundario" href="ListarPizza">Cancelar</a>
                        </div>
                    </form>
                </div>

                <%-- Abre um bloco de código Java dentro da página (scriptlet). --%>
                <%
                    // Se o teste anterior falhou, mostra o conteúdo abaixo.
                    } else {
                %>
                <%-- Parágrafo de texto. --%>
                <p>Pizza não encontrada.</p>
                <%-- Botão que volta para a listagem. --%>
                <a class="botao secundario" href="ListarPizza">Voltar para a listagem</a>
                <%-- Abre um bloco de código Java dentro da página (scriptlet). --%>
                <%
                    }
                %>
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
