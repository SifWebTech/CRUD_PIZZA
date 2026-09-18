<%@page import="br.com.pizzaria.model.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Excluir Pizza - Iglesias Siqueira Pizzas</title>
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
            <h1>Excluir Pizza</h1>
            <p>Confirme a remoção antes de atualizar o cardápio</p>
        </section>

        <main>
            <section>
                <h2>Confirmação</h2>

                <%
                    Pizza pizza = (Pizza) request.getAttribute("pizza");
                    if (pizza != null) {
                %>

                <div class="confirmacao">
                    <p>Você está prestes a excluir esta pizza do banco de dados.</p>

                    <dl>
                        <dt>Código</dt>
                        <dd><%= pizza.getIdPizza()%></dd>
                        <dt>Nome</dt>
                        <dd><%= pizza.getNomePizza()%></dd>
                        <dt>Descrição</dt>
                        <dd><%= pizza.getDescricaoPizza()%></dd>
                        <dt>Tamanho</dt>
                        <dd><%= pizza.getTamanhoPizza()%></dd>
                        <dt>Preço</dt>
                        <dd>R$ <%= pizza.getPrecoPizza()%></dd>
                    </dl>

                    <form action="ExcluirPizza" method="post">
                        <input type="hidden" name="idPizza" value="<%= pizza.getIdPizza()%>">
                        <div class="botoes">
                            <input class="perigo" type="submit" value="Confirmar exclusão">
                            <a class="botao secundario" href="ListarPizza">Cancelar</a>
                        </div>
                    </form>
                </div>

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
