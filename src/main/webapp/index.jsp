<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Administração - Iglesias Siqueira Pizzas</title>
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
                    <a href="index.jsp" class="ativo">Início</a>
                    <a href="cadastrarpizza.jsp">Cadastrar</a>
                    <a href="ListarPizza">Listar</a>
                </nav>
            </div>
        </header>

        <section class="titulo-pagina">
            <h1>Administração do Cardápio</h1>
            <p>Área de controle das pizzas cadastradas no sistema</p>
        </section>

        <section class="faixa-admin">
            <strong>CRUD Java Web</strong>
            <p>Cadastro, listagem, alteração e exclusão de pizzas</p>
        </section>

        <main>
            <section>
                <h2>Painel da pizzaria</h2>
                <p>
                    Este módulo complementa o primeiro site da Iglesias Siqueira Pizzas.
                    Aqui ficam as operações administrativas que mantêm o cardápio do
                    banco de dados atualizado.
                </p>

                <ul class="painel-acoes">
                    <li class="cartao-acao">
                        <img src="imagens/pizza-margherita.svg" alt="Ilustração de pizza">
                        <h3>Cadastrar pizza</h3>
                        <p>Inclua novos sabores, tamanhos, descrições e preços.</p>
                        <a class="botao" href="cadastrarpizza.jsp">Cadastrar</a>
                    </li>
                    <li class="cartao-acao">
                        <img src="imagens/pizza-quattro-formaggi.svg" alt="Ilustração de pizza">
                        <h3>Listar pizzas</h3>
                        <p>Consulte o cardápio cadastrado e acesse as ações de edição.</p>
                        <a class="botao secundario" href="ListarPizza">Ver listagem</a>
                    </li>
                </ul>
            </section>
        </main>

        <footer>
            <p class="marca-rodape">Iglesias Siqueira Pizzas</p>
            <p class="lema-rodape">Espanha e Portugal fazendo as melhores pizzas da Itália</p>
            <p class="projeto">Felipe Iglesias Siqueira - 5º Semestre Sistemas para Internet - Prof. André Mussato</p>
        </footer>
    </body>
</html>
