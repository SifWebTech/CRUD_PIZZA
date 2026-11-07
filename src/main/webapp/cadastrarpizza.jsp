<%@page import="br.com.pizzaria.util.Html"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cadastrar Pizza - Iglesias Siqueira Pizzas</title>
        <link rel="icon" href="imagens/logo.svg" type="image/svg+xml">
        <link rel="stylesheet" href="css/estilo.css">
    </head>
    <body>
        <header>
            <div class="topo">
                <div class="marca">
                    <img src="imagens/logo.svg" alt="Brasão da Iglesias Siqueira Pizzas">
                    <div>
                        <p class="marca-nome">Iglesias Siqueira Pizzas</p>
                        <p class="marca-slogan">Espanha e Portugal fazendo as melhores pizzas da Itália</p>
                    </div>
                </div>

                <nav>
                    <a href="index.jsp">Início</a>
                    <a href="cadastrarpizza.jsp" class="ativo">Cadastrar</a>
                    <a href="ListarPizza">Listar</a>
                </nav>
            </div>
        </header>

        <section class="titulo-pagina">
            <h1>Cadastrar Pizza</h1>
            <p>Inclua uma nova receita no cardápio administrativo</p>
        </section>

        <main>
            <section>
                <h2>Dados da pizza</h2>

                <form name="cadastrarPizza" action="CadastrarPizza" method="post">
                    <label for="nomePizza">Nome</label>
                    <input type="text" id="nomePizza" name="nomePizza" maxlength="60" placeholder="Ex.: Margherita" required
                           value="<%= Html.escapar(request.getParameter("nomePizza"))%>">

                    <label for="descricaoPizza">Descrição</label>
                    <input type="text" id="descricaoPizza" name="descricaoPizza" maxlength="200" placeholder="Ingredientes principais" required
                           value="<%= Html.escapar(request.getParameter("descricaoPizza"))%>">

                    <label for="tamanhoPizza">Tamanho</label>
                    <select id="tamanhoPizza" name="tamanhoPizza" required>
                        <option value="Pequena" <%= "Pequena".equals(request.getParameter("tamanhoPizza")) ? "selected" : ""%>>Pequena</option>
                        <option value="Media"   <%= "Media".equals(request.getParameter("tamanhoPizza")) ? "selected" : ""%>>Média</option>
                        <option value="Grande"  <%= "Grande".equals(request.getParameter("tamanhoPizza")) ? "selected" : ""%>>Grande</option>
                    </select>

                    <label for="precoPizza">Preço (R$)</label>
                    <input type="number" id="precoPizza" name="precoPizza" step="0.01" min="0.01" placeholder="0.00" required
                           value="<%= Html.escapar(request.getParameter("precoPizza"))%>">

                    <div class="botoes">
                        <input type="submit" value="Cadastrar">
                        <input type="reset" value="Limpar">
                        <a class="botao secundario" href="ListarPizza">Ver listagem</a>
                    </div>
                </form>

                <%@ include file="/WEB-INF/mensagens.jspf" %>
            </section>
        </main>

        <footer>
            <p class="marca-rodape">Iglesias Siqueira Pizzas</p>
            <p class="lema-rodape">Espanha e Portugal fazendo as melhores pizzas da Itália</p>
            <p class="projeto">Felipe Iglesias Siqueira - 5º Semestre Sistemas para Internet - Prof. André Mussato</p>
        </footer>
    </body>
</html>
