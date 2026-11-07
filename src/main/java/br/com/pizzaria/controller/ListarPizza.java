// Informa o pacote (a pasta lógica) desta classe: br.com.pizzaria.controller.
package br.com.pizzaria.controller;

// Traz a interface com as operações de banco do sistema.
import br.com.pizzaria.DAO.GenericDAO;
// Traz a classe que executa os comandos SQL da tabela pizza.
import br.com.pizzaria.DAO.PizzaDAOImpl;
// Traz a classe que guarda mensagens de sucesso e de erro.
import br.com.pizzaria.util.Mensagem;

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
// Traz a interface de lista do Java.
import java.util.List;

/**
 * Servlet responsavel por buscar as pizzas cadastradas
 * e envia-las para a pagina listarpizza.jsp.
 */
// Registra o Servlet no servidor e o liga ao endereço /ListarPizza do site.
@WebServlet(name = "ListarPizza", urlPatterns = {"/ListarPizza"})
// Cria a classe ListarPizza; extends HttpServlet faz dela um Servlet, que responde ao navegador.
public class ListarPizza extends HttpServlet {

    // Indica que o método abaixo reimplementa um método herdado ou definido na interface.
    @Override
    // Método chamado em requisições GET (abrir um link ou digitar o endereço). request é o pedido e response é a resposta.
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            // throws avisa quais erros o método pode lançar; a chave abre o corpo do método.
            throws ServletException, IOException {

        // Busca as mensagens guardadas antes do redirecionamento.
        Mensagem.carregar(request);

        // Início do trecho que pode dar erro; se der, o Java pula para o catch ou o finally.
        try {
            // Cria o objeto que conversa com o banco, usando o tipo da interface.
            GenericDAO dao = new PizzaDAOImpl();
            // Pede ao DAO a lista de pizzas.
            List<Object> pizzas = dao.listar();
            // Coloca a lista na requisição para a JSP usar.
            request.setAttribute("pizzas", pizzas);
        // Se acontecer outro erro no try, a execução continua aqui.
        } catch (Exception ex) {
            // Coloca na requisição a mensagem de erro abaixo.
            request.setAttribute(Mensagem.ERRO,
                    // Texto mostrado ao usuário.
                    "Não foi possível acessar o banco de dados. Verifique a conexão com o PostgreSQL.");
            // Escreve no console o detalhe completo do erro.
            ex.printStackTrace();
        }

        // Encaminha para listarpizza.jsp, que monta o HTML.
        request.getRequestDispatcher("listarpizza.jsp").forward(request, response);
    }
}
