<%-- Diretiva da JSP: importa a classe que protege os textos exibidos. --%>
<%@page import="br.com.pizzaria.util.Html"%>
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
        <title>Cadastrar Pizza - Iglesias Siqueira Pizzas</title>
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
                    <%-- Link do menu para o formulário de cadastro. class="ativo" destaca a página atual. --%>
                    <a href="cadastrarpizza.jsp" class="ativo">Cadastrar</a>
                    <%-- Link do menu para o Servlet que lista as pizzas. --%>
                    <a href="ListarPizza">Listar</a>
                </nav>
            </div>
        </header>

        <%-- Faixa com o título da página. --%>
        <section class="titulo-pagina">
            <%-- Título principal da página. --%>
            <h1>Cadastrar Pizza</h1>
            <%-- Parágrafo de texto. --%>
            <p>Inclua uma nova receita no cardápio administrativo</p>
        </section>

        <%-- Área principal da página. --%>
        <main>
            <%-- Seção com o conteúdo principal. --%>
            <section>
                <%-- Título da seção. --%>
                <h2>Dados da pizza</h2>

                <%-- Formulário: action é o Servlet que recebe os dados e post é o método de envio. --%>
                <form name="cadastrarPizza" action="CadastrarPizza" method="post">
                    <%-- Rótulo ligado ao campo nomePizza pelo atributo for. --%>
                    <label for="nomePizza">Nome</label>
                    <%-- Campo do nome: maxlength limita a 60 caracteres, required torna obrigatório e value preenche o valor. --%>
                    <input type="text" id="nomePizza" name="nomePizza" maxlength="60" placeholder="Ex.: Margherita" required
                           value="<%= Html.escapar(request.getParameter("nomePizza"))%>">

                    <%-- Rótulo ligado ao campo descricaoPizza pelo atributo for. --%>
                    <label for="descricaoPizza">Descrição</label>
                    <%-- Campo da descrição: maxlength limita a 200 caracteres, required torna obrigatório e value preenche o valor. --%>
                    <input type="text" id="descricaoPizza" name="descricaoPizza" maxlength="200" placeholder="Ingredientes principais" required
                           value="<%= Html.escapar(request.getParameter("descricaoPizza"))%>">

                    <%-- Rótulo ligado ao campo tamanhoPizza pelo atributo for. --%>
                    <label for="tamanhoPizza">Tamanho</label>
                    <%-- Caixa de seleção do tamanho, de preenchimento obrigatório. --%>
                    <select id="tamanhoPizza" name="tamanhoPizza" required>
                        <%-- Opção de tamanho; o ternário escreve selected quando ela é a escolhida. --%>
                        <option value="Pequena" <%= "Pequena".equals(request.getParameter("tamanhoPizza")) ? "selected" : ""%>>Pequena</option>
                        <%-- Opção de tamanho; o ternário escreve selected quando ela é a escolhida. --%>
                        <option value="Media"   <%= "Media".equals(request.getParameter("tamanhoPizza")) ? "selected" : ""%>>Média</option>
                        <%-- Opção de tamanho; o ternário escreve selected quando ela é a escolhida. --%>
                        <option value="Grande"  <%= "Grande".equals(request.getParameter("tamanhoPizza")) ? "selected" : ""%>>Grande</option>
                    </select>

                    <%-- Rótulo ligado ao campo precoPizza pelo atributo for. --%>
                    <label for="precoPizza">Preço (R$)</label>
                    <%-- Campo do preço: step aceita centavos, min define o mínimo e value preenche o valor. --%>
                    <input type="number" id="precoPizza" name="precoPizza" step="0.01" min="0.01" placeholder="0.00" required
                           value="<%= Html.escapar(request.getParameter("precoPizza"))%>">

                    <%-- Caixa que agrupa os botões. --%>
                    <div class="botoes">
                        <%-- Botão que envia o formulário. --%>
                        <input type="submit" value="Cadastrar">
                        <%-- Botão que limpa o formulário. --%>
                        <input type="reset" value="Limpar">
                        <%-- Botão que volta para a listagem. --%>
                        <a class="botao secundario" href="ListarPizza">Ver listagem</a>
                    </div>
                </form>

                <%-- Insere aqui o arquivo que mostra as mensagens de sucesso e de erro. --%>
                <%@ include file="/WEB-INF/mensagens.jspf" %>
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
