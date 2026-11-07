<%@page import="br.com.pizzaria.util.Html"%>
<%@page import="br.com.pizzaria.model.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Alterar Pizza - Iglesias Siqueira Pizzas</title>
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
                    <a href="cadastrarpizza.jsp">Cadastrar</a>
                    <a href="ListarPizza">Listar</a>
                </nav>
            </div>
        </header>

        <section class="titulo-pagina">
            <h1>Alterar Pizza</h1>
            <p>Atualize as informações de uma pizza cadastrada</p>
        </section>

        <main>
            <section>
                <h2>Edição do cardápio</h2>
                <%@ include file="/WEB-INF/mensagens.jspf" %>

                <%
                    Pizza pizza = (Pizza) request.getAttribute("pizza");
                    if (pizza != null) {
                %>

                <form name="alterarPizza" action="AlterarPizza" method="post">
                    <input type="hidden" name="idPizza" value="<%= pizza.getIdPizza()%>">

                    <label for="nomePizza">Nome</label>
                    <input type="text" id="nomePizza" name="nomePizza" maxlength="60" required
                           value="<%= Html.escapar(pizza.getNomePizza())%>">

                    <label for="descricaoPizza">Descrição</label>
                    <input type="text" id="descricaoPizza" name="descricaoPizza" maxlength="200" required
                           value="<%= Html.escapar(pizza.getDescricaoPizza())%>">

                    <label for="tamanhoPizza">Tamanho</label>
                    <select id="tamanhoPizza" name="tamanhoPizza" required>
                        <option value="Pequena" <%= "Pequena".equals(pizza.getTamanhoPizza()) ? "selected" : ""%>>Pequena</option>
                        <option value="Media"   <%= "Media".equals(pizza.getTamanhoPizza()) ? "selected" : ""%>>Média</option>
                        <option value="Grande"  <%= "Grande".equals(pizza.getTamanhoPizza()) ? "selected" : ""%>>Grande</option>
                    </select>

                    <label for="precoPizza">Preço (R$)</label>
                    <input type="number" id="precoPizza" name="precoPizza" step="0.01" min="0.01" required
                           value="<%= Html.escapar(pizza.getPrecoPizza())%>">

                    <div class="botoes">
                        <input type="submit" value="Salvar alterações">
                        <a class="botao secundario" href="ListarPizza">Cancelar</a>
                    </div>
                </form>

                <%
                    } else {
                %>
                <p>Pizza não encontrada.</p>
                <a class="botao secundario" href="ListarPizza">Voltar para a listagem</a>
                <%
                    }
                %>
            </section>
        </main>

        <footer>
            <p class="marca-rodape">Iglesias Siqueira Pizzas</p>
            <p class="lema-rodape">Espanha e Portugal fazendo as melhores pizzas da Itália</p>
            <p class="projeto">Felipe Iglesias Siqueira - 5º Semestre Sistemas para Internet - Prof. André Mussato</p>
        </footer>
    </body>
</html>
