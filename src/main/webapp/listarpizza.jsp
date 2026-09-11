<%@page import="br.com.pizzaria.model.Pizza"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Pizzas Cadastradas</title>
        <link rel="stylesheet" href="css/estilo.css">
    </head>
    <body>
        <h1>Pizzas Cadastradas</h1>

        <p class="mensagem">${sucesso}</p>

        <table>
            <tr>
                <th>Codigo</th>
                <th>Nome</th>
                <th>Descricao</th>
                <th>Tamanho</th>
                <th>Preco</th>
                <th>Alterar</th>
                <th>Excluir</th>
            </tr>
            <%
                List<Pizza> pizzas = (List<Pizza>) request.getAttribute("pizzas");

                if (pizzas != null) {
                    for (Pizza pizza : pizzas) {
            %>
            <tr>
                <td><%= pizza.getIdPizza()%></td>
                <td><%= pizza.getNomePizza()%></td>
                <td><%= pizza.getDescricaoPizza()%></td>
                <td><%= pizza.getTamanhoPizza()%></td>
                <td>R$ <%= pizza.getPrecoPizza()%></td>
                <td><a href="AlterarPizza?idPizza=<%= pizza.getIdPizza()%>">Alterar</a></td>
                <td><a href="ExcluirPizza?idPizza=<%= pizza.getIdPizza()%>"
                       onclick="return confirm('Deseja realmente excluir esta pizza?');">Excluir</a></td>
            </tr>
            <%
                    }
                }
            %>
        </table>

        <p>
            <a href="cadastrarpizza.jsp">Cadastrar nova pizza</a> |
            <a href="index.jsp">Voltar ao menu</a>
        </p>
    </body>
</html>
