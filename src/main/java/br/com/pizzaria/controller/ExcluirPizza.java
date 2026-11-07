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
 * Servlet responsavel por excluir a pizza escolhida na listagem.
 * No GET carrega a tela de confirmacao; no POST executa a exclusao.
 */
// Registra o Servlet no servidor e o liga ao endereço /ExcluirPizza do site.
@WebServlet(name = "ExcluirPizza", urlPatterns = {"/ExcluirPizza"})
// Cria a classe ExcluirPizza; extends HttpServlet faz dela um Servlet, que responde ao navegador.
public class ExcluirPizza extends HttpServlet {

    // Indica que o método abaixo reimplementa um método herdado ou definido na interface.
    @Override
    // Método chamado em requisições GET (abrir um link ou digitar o endereço). request é o pedido e response é a resposta.
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            // throws avisa quais erros o método pode lançar; a chave abre o corpo do método.
            throws ServletException, IOException {

        // Declara a variável fora do try, para usá-la depois dele.
        Pizza pizza;

        // Início do trecho que pode dar erro; se der, o Java pula para o catch ou o finally.
        try {
            // Lê o idPizza recebido e converte o texto para inteiro.
            int idPizza = Integer.parseInt(request.getParameter("idPizza"));

            // Cria o objeto que conversa com o banco, usando o tipo da interface.
            GenericDAO dao = new PizzaDAOImpl();
            // Busca a pizza no banco e converte o Object para Pizza.
            pizza = (Pizza) dao.buscarPorId(idPizza);
        // Se o texto recebido não for um número, a execução continua aqui.
        } catch (NumberFormatException ex) {
            // Avisa o erro e volta para a listagem.
            voltarParaListagem(request, response, Mensagem.ERRO, "Código de pizza inválido.");
            // Encerra o método aqui.
            return;
        // Se acontecer outro erro no try, a execução continua aqui.
        } catch (Exception ex) {
            // Escreve no console o detalhe completo do erro.
            ex.printStackTrace();
            // Avisa o erro e volta para a listagem.
            voltarParaListagem(request, response, Mensagem.ERRO, "Não foi possível carregar a pizza.");
            // Encerra o método aqui.
            return;
        }

        // Coloca a pizza na requisição para a JSP usar.
        request.setAttribute("pizza", pizza);
        // Encaminha para a tela de confirmação.
        request.getRequestDispatcher("excluirpizza.jsp").forward(request, response);
    }

    // Indica que o método abaixo reimplementa um método herdado ou definido na interface.
    @Override
    // Método chamado em requisições POST (envio de formulário). request é o pedido e response é a resposta.
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            // throws avisa quais erros o método pode lançar; a chave abre o corpo do método.
            throws ServletException, IOException {

        // Início do trecho que pode dar erro; se der, o Java pula para o catch ou o finally.
        try {
            // Lê o idPizza recebido e converte o texto para inteiro.
            int idPizza = Integer.parseInt(request.getParameter("idPizza"));

            // Cria o objeto que conversa com o banco, usando o tipo da interface.
            GenericDAO dao = new PizzaDAOImpl();

            // Apaga no banco; o if testa se alguma linha foi apagada.
            if (dao.excluir(idPizza)) {
                // Avisa o sucesso e volta para a listagem.
                voltarParaListagem(request, response, Mensagem.SUCESSO, "Pizza excluída com sucesso!");
            // Caso contrário, executa o bloco abaixo.
            } else {
                // Nenhuma linha foi afetada: a pizza não existe.
                voltarParaListagem(request, response, Mensagem.ERRO, "Pizza não encontrada.");
            }
        // Se o texto recebido não for um número, a execução continua aqui.
        } catch (NumberFormatException ex) {
            // Avisa o erro e volta para a listagem.
            voltarParaListagem(request, response, Mensagem.ERRO, "Código de pizza inválido.");
        // Se acontecer outro erro no try, a execução continua aqui.
        } catch (Exception ex) {
            // Escreve no console o detalhe completo do erro.
            ex.printStackTrace();
            // Avisa o erro abaixo e volta para a listagem.
            voltarParaListagem(request, response, Mensagem.ERRO,
                    // Texto mostrado ao usuário.
                    "Não foi possível excluir a pizza. Verifique a conexão com o PostgreSQL.");
        }
    }

    // Método interno que guarda a mensagem e redireciona para a listagem.
    private void voltarParaListagem(HttpServletRequest request, HttpServletResponse response,
                                    // Continuação dos parâmetros: tipo da mensagem e texto dela.
                                    String tipo, String texto) throws IOException {
        // Guarda a mensagem na sessão.
        Mensagem.guardar(request, tipo, texto);
        // Manda o navegador abrir a listagem, com um GET.
        response.sendRedirect("ListarPizza");
    }
}
