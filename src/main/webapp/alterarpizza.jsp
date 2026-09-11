<%@page import="br.com.pizzaria.model.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Alterar Pizza</title>
        <link rel="stylesheet" href="css/estilo.css">
    </head>
    <body>
        <h1>Alterar Pizza</h1>

        <%
            Pizza pizza = (Pizza) request.getAttribute("pizza");
            if (pizza != null) {
        %>

        <form name="alterarPizza" action="AlterarPizza" method="post">

            <input type="hidden" name="idPizza" value="<%= pizza.getIdPizza()%>">

            <label for="nomePizza">Nome</label>
            <input type="text" id="nomePizza" name="nomePizza" maxlength="60" required
                   value="<%= pizza.getNomePizza()%>">

            <label for="descricaoPizza">Descricao</label>
            <input type="text" id="descricaoPizza" name="descricaoPizza" maxlength="200" required
                   value="<%= pizza.getDescricaoPizza()%>">

            <label for="tamanhoPizza">Tamanho</label>
            <select id="tamanhoPizza" name="tamanhoPizza" required>
                <option value="Pequena" <%= "Pequena".equals(pizza.getTamanhoPizza()) ? "selected" : ""%>>Pequena</option>
                <option value="Media"   <%= "Media".equals(pizza.getTamanhoPizza()) ? "selected" : ""%>>Media</option>
                <option value="Grande"  <%= "Grande".equals(pizza.getTamanhoPizza()) ? "selected" : ""%>>Grande</option>
            </select>

            <label for="precoPizza">Preco (R$)</label>
            <input type="number" id="precoPizza" name="precoPizza" step="0.01" min="0" required
                   value="<%= pizza.getPrecoPizza()%>">

            <div class="botoes">
                <input type="submit" value="Salvar alteracoes">
                <a href="ListarPizza">Cancelar</a>
            </div>
        </form>

        <%
            } else {
        %>
        <p>Pizza nao encontrada.</p>
        <p><a href="ListarPizza">Voltar para a listagem</a></p>
        <%
            }
        %>
    </body>
</html>
