package br.com.pizzaria.controller;

import br.com.pizzaria.DAO.GenericDAO;
import br.com.pizzaria.DAO.PizzaDAOImpl;
import br.com.pizzaria.model.Pizza;

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

        try {
            int idPizza = Integer.parseInt(request.getParameter("idPizza"));

            GenericDAO dao = new PizzaDAOImpl();
            Pizza pizza = (Pizza) dao.buscarPorId(idPizza);

            request.setAttribute("pizza", pizza);
            request.getRequestDispatcher("excluirpizza.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("sucesso", "Erro ao carregar a pizza: " + ex.getMessage());
            request.getRequestDispatcher("ListarPizza").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mensagem;

        try {
            int idPizza = Integer.parseInt(request.getParameter("idPizza"));

            GenericDAO dao = new PizzaDAOImpl();
            dao.excluir(idPizza);

            mensagem = "Pizza excluida com sucesso!";
        } catch (Exception ex) {
            mensagem = "Erro ao excluir a pizza: " + ex.getMessage();
            ex.printStackTrace();
        }

        request.setAttribute("sucesso", mensagem);
        request.getRequestDispatcher("ListarPizza").forward(request, response);
    }
}
