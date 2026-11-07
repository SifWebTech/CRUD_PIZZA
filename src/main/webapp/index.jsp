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
        <title>Administração - Iglesias Siqueira Pizzas</title>
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
                    <%-- Link do menu para a página inicial. class="ativo" destaca a página atual. --%>
                    <a href="index.jsp" class="ativo">Início</a>
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
            <h1>Administração do Cardápio</h1>
            <%-- Parágrafo de texto. --%>
            <p>Área de controle das pizzas cadastradas no sistema</p>
        </section>

        <%-- Faixa de destaque que resume o sistema. --%>
        <section class="faixa-admin">
            <%-- Texto em destaque. --%>
            <strong>CRUD Java Web</strong>
            <%-- Parágrafo de texto. --%>
            <p>Cadastro, listagem, alteração e exclusão de pizzas</p>
        </section>

        <%-- Área principal da página. --%>
        <main>
            <%-- Seção com o conteúdo principal. --%>
            <section>
                <%-- Título da seção. --%>
                <h2>Painel da pizzaria</h2>
                <%-- Parágrafo de apresentação do painel. --%>
                <p>
                    Este módulo complementa o primeiro site da Iglesias Siqueira Pizzas.
                    Aqui ficam as operações administrativas que mantêm o cardápio do
                    banco de dados atualizado.
                </p>

                <%-- Lista com os cartões de ação. --%>
                <ul class="painel-acoes">
                    <%-- Cartão que leva a uma função do sistema. --%>
                    <li class="cartao-acao">
                        <%-- Exibe a ilustração do cartão; alt é o texto alternativo. --%>
                        <img src="imagens/pizza-margherita.svg" alt="Ilustração de pizza">
                        <%-- Título do cartão. --%>
                        <h3>Cadastrar pizza</h3>
                        <%-- Parágrafo de texto. --%>
                        <p>Inclua novos sabores, tamanhos, descrições e preços.</p>
                        <%-- Botão que abre o formulário de cadastro. --%>
                        <a class="botao" href="cadastrarpizza.jsp">Cadastrar</a>
                    </li>
                    <%-- Cartão que leva a uma função do sistema. --%>
                    <li class="cartao-acao">
                        <%-- Exibe a ilustração do cartão; alt é o texto alternativo. --%>
                        <img src="imagens/pizza-quattro-formaggi.svg" alt="Ilustração de pizza">
                        <%-- Título do cartão. --%>
                        <h3>Listar pizzas</h3>
                        <%-- Parágrafo de texto. --%>
                        <p>Consulte o cardápio cadastrado e acesse as ações de edição.</p>
                        <%-- Botão que volta para a listagem. --%>
                        <a class="botao secundario" href="ListarPizza">Ver listagem</a>
                    </li>
                </ul>
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
