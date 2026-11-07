package br.com.pizzaria.controller;

import br.com.pizzaria.DAO.GenericDAO;
import br.com.pizzaria.DAO.PizzaDAOImpl;
import br.com.pizzaria.model.Pizza;
import br.com.pizzaria.util.Mensagem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet responsavel por excluir a pizza escolhida na listagem.
 * No GET carrega a tela de confirmacao; no POST executa a exclusao.
 */
@WebServlet(name = "ExcluirPizza", urlPatterns = {"/ExcluirPizza"})
public class ExcluirPizza extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Pizza pizza;

        try {
            int idPizza = Integer.parseInt(request.getParameter("idPizza"));

            GenericDAO dao = new PizzaDAOImpl();
            pizza = (Pizza) dao.buscarPorId(idPizza);
        } catch (NumberFormatException ex) {
            voltarParaListagem(request, response, Mensagem.ERRO, "Código de pizza inválido.");
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
            voltarParaListagem(request, response, Mensagem.ERRO, "Não foi possível carregar a pizza.");
            return;
        }

        request.setAttribute("pizza", pizza);
        request.getRequestDispatcher("excluirpizza.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int idPizza = Integer.parseInt(request.getParameter("idPizza"));

            GenericDAO dao = new PizzaDAOImpl();

            if (dao.excluir(idPizza)) {
                voltarParaListagem(request, response, Mensagem.SUCESSO, "Pizza excluída com sucesso!");
            } else {
                voltarParaListagem(request, response, Mensagem.ERRO, "Pizza não encontrada.");
            }
        } catch (NumberFormatException ex) {
            voltarParaListagem(request, response, Mensagem.ERRO, "Código de pizza inválido.");
        } catch (Exception ex) {
            ex.printStackTrace();
            voltarParaListagem(request, response, Mensagem.ERRO,
                    "Não foi possível excluir a pizza. Verifique a conexão com o PostgreSQL.");
        }
    }

    private void voltarParaListagem(HttpServletRequest request, HttpServletResponse response,
                                    String tipo, String texto) throws IOException {
        Mensagem.guardar(request, tipo, texto);
        response.sendRedirect("ListarPizza");
    }
}
