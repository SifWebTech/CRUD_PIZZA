<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Cadastro de Pizza</title>
        <link rel="stylesheet" href="css/estilo.css">
    </head>
    <body>
        <h1>Cadastro de Pizza</h1>

        <form name="cadastrarPizza" action="CadastrarPizza" method="post">

            <label for="nomePizza">Nome</label>
            <input type="text" id="nomePizza" name="nomePizza" maxlength="60" required>

            <label for="descricaoPizza">Descricao</label>
            <input type="text" id="descricaoPizza" name="descricaoPizza" maxlength="200" required>

            <label for="tamanhoPizza">Tamanho</label>
            <select id="tamanhoPizza" name="tamanhoPizza" required>
                <option value="Pequena">Pequena</option>
                <option value="Media">Media</option>
                <option value="Grande">Grande</option>
            </select>

            <label for="precoPizza">Preco (R$)</label>
            <input type="number" id="precoPizza" name="precoPizza" step="0.01" min="0" required>

            <div class="botoes">
                <input type="submit" value="Cadastrar">
                <input type="reset" value="Limpar">
                <a href="ListarPizza">Ver listagem</a>
            </div>
        </form>

        <p class="mensagem">${sucesso}</p>

        <p><a href="index.jsp">Voltar ao menu</a></p>
    </body>
</html>
