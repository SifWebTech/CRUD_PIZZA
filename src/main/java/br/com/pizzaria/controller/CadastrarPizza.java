// Informa o pacote (a pasta lógica) desta classe: br.com.pizzaria.controller.
package br.com.pizzaria.controller;

// Traz a interface com as operações de banco do sistema.
import br.com.pizzaria.DAO.GenericDAO;
// Traz a classe que executa os comandos SQL da tabela pizza.
import br.com.pizzaria.DAO.PizzaDAOImpl;
// Traz a classe Pizza, que representa uma pizza no sistema.
import br.com.pizzaria.model.Pizza;
// Traz a classe que guarda mensagens de sucesso e de erro.
import br.com.pizzaria.util.Mensagem;
// Traz a classe que confere os dados da pizza.
import br.com.pizzaria.util.ValidadorPizza;

// Traz o tipo de erro que um Servlet pode lançar.
import jakarta.servlet.ServletException;
// Traz a anotação que liga o Servlet a um endereço do site.
import jakarta.servlet.annotation.WebServlet;
// Traz a classe base de todo Servlet web.
import jakarta.servlet.http.HttpServlet;
// Traz a classe que representa o pedido enviado pelo navegador.
import jakarta.servlet.http.HttpServletRequest;
// Traz a classe que representa a resposta enviada ao navegador.
import jakarta.servlet.http.HttpServletResponse;

// Traz o tipo de erro de entrada e saída de dados.
import java.io.IOException;

/**
 * Servlet responsavel pelo cadastro de uma nova pizza.
 * No GET abre o formulario; no POST valida e envia os dados para a camada DAO.
 */
// Registra o Servlet no servidor e o liga ao endereço /CadastrarPizza do site.
@WebServlet(name = "CadastrarPizza", urlPatterns = {"/CadastrarPizza"})
// Cria a classe CadastrarPizza; extends HttpServlet faz dela um Servlet, que responde ao navegador.
public class CadastrarPizza extends HttpServlet {

    // Indica que o método abaixo reimplementa um método herdado ou definido na interface.
    @Override
    // Método chamado em requisições GET (abrir um link ou digitar o endereço). request é o pedido e response é a resposta.
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            // throws avisa quais erros o método pode lançar; a chave abre o corpo do método.
            throws ServletException, IOException {

        // Busca as mensagens guardadas antes do redirecionamento.
        Mensagem.carregar(request);
        // Encaminha para o formulário de cadastro.
        request.getRequestDispatcher("cadastrarpizza.jsp").forward(request, response);
    }

    // Indica que o método abaixo reimplementa um método herdado ou definido na interface.
    @Override
    // Método chamado em requisições POST (envio de formulário). request é o pedido e response é a resposta.
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            // throws avisa quais erros o método pode lançar; a chave abre o corpo do método.
            throws ServletException, IOException {

        // Lê o formulário em UTF-8, mantendo os acentos.
        request.setCharacterEncoding("UTF-8");

        // Cria um objeto Pizza vazio.
        Pizza pizza = new Pizza();
        // Lê o nome do formulário e remove os espaços das pontas.
        pizza.setNomePizza(limpar(request.getParameter("nomePizza")));
        // Lê a descrição do formulário e remove os espaços das pontas.
        pizza.setDescricaoPizza(limpar(request.getParameter("descricaoPizza")));
        // Lê o tamanho escolhido no formulário.
        pizza.setTamanhoPizza(request.getParameter("tamanhoPizza"));
        // Lê o preço do formulário e converte para número.
        pizza.setPrecoPizza(ValidadorPizza.converterPreco(request.getParameter("precoPizza")));

        // Confere os dados; erro fica null quando está tudo certo.
        String erro = ValidadorPizza.validar(pizza);

        // Só tenta gravar se os dados estão válidos.
        if (erro == null) {
            // Início do trecho que pode dar erro; se der, o Java pula para o catch ou o finally.
            try {
                // Cria o objeto que conversa com o banco, usando o tipo da interface.
                GenericDAO dao = new PizzaDAOImpl();
                // Grava a pizza no banco.
                dao.cadastrar(pizza);

                // Redireciona para que o F5 nao envie o cadastro novamente.
                // Guarda o aviso para a próxima página.
                Mensagem.guardar(request, Mensagem.SUCESSO, "Pizza cadastrada com sucesso!");
                // Manda o navegador abrir o formulário de novo, com um GET.
                response.sendRedirect("CadastrarPizza");
                // Encerra o método aqui.
                return;
            // Se acontecer outro erro no try, a execução continua aqui.
            } catch (Exception ex) {
                // Define a mensagem mostrada ao usuário.
                erro = "Não foi possível cadastrar a pizza. Verifique a conexão com o PostgreSQL.";
                // Escreve no console o detalhe completo do erro.
                ex.printStackTrace();
            }
        }

        // Coloca o erro na requisição para a JSP mostrar.
        request.setAttribute(Mensagem.ERRO, erro);
        // Encaminha para o formulário de cadastro.
        request.getRequestDispatcher("cadastrarpizza.jsp").forward(request, response);
    }

    // Método interno que remove espaços das pontas do texto.
    private String limpar(String texto) {
        // Operador ternário: se for null devolve null; senão, devolve o texto sem espaços.
        return texto == null ? null : texto.trim();
    }
}
