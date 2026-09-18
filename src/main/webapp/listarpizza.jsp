<%@page import="br.com.pizzaria.model.Pizza"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pizzas Cadastradas - Iglesias Siqueira Pizzas</title>
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
                    <a href="ListarPizza" class="ativo">Listar</a>
                </nav>
            </div>
        </header>

        <section class="titulo-pagina">
            <h1>Pizzas Cadastradas</h1>
            <p>Consulte, altere ou remova sabores do banco de dados</p>
        </section>

        <main>
            <section>
                <h2>Cardápio administrativo</h2>
                <p class="mensagem">${sucesso}</p>

                <div class="botoes">
                    <a class="botao" href="cadastrarpizza.jsp">Cadastrar nova pizza</a>
                </div>

                <div class="tabela-rolagem">
                    <table>
                        <thead>
                            <tr>
                                <th>Código</th>
                                <th>Nome</th>
                                <th>Descrição</th>
                                <th>Tamanho</th>
                                <th>Preço</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Pizza> pizzas = (List<Pizza>) request.getAttribute("pizzas");

                                if (pizzas != null && !pizzas.isEmpty()) {
                                    for (Pizza pizza : pizzas) {
                            %>
                            <tr>
                                <td><%= pizza.getIdPizza()%></td>
                                <td><strong><%= pizza.getNomePizza()%></strong></td>
                                <td><%= pizza.getDescricaoPizza()%></td>
                                <td><%= pizza.getTamanhoPizza()%></td>
                                <td class="preco">R$ <%= pizza.getPrecoPizza()%></td>
                                <td>
                                    <div class="acoes-tabela">
                                        <a class="alterar" href="AlterarPizza?idPizza=<%= pizza.getIdPizza()%>">Alterar</a>
                                        <a class="excluir" href="ExcluirPizza?idPizza=<%= pizza.getIdPizza()%>">Excluir</a>
                                    </div>
                                </td>
                            </tr>
                            <%
                                    }
                                } else {
                            %>
                            <tr>
                                <td colspan="6">Nenhuma pizza cadastrada até o momento.</td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </section>
        </main>

        <footer>
            <p class="marca-rodape">Iglesias Siqueira Pizzas</p>
            <p class="lema-rodape">Espanha e Portugal fazendo as melhores pizzas da Itália</p>
            <p class="projeto">Felipe Iglesias Siqueira - 5º Semestre Sistemas para Internet - Prof. André Mussato</p>
        </footer>
    </body>
</html>
