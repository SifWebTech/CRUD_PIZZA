package br.com.pizzaria.controller;

import br.com.pizzaria.DAO.GenericDAO;
import br.com.pizzaria.DAO.PizzaDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet responsavel por excluir a pizza escolhida na listagem.
 * Apos a exclusao devolve o usuario para a listagem atualizada.
 */
@WebServlet(name = "ExcluirPizza", urlPatterns = {"/ExcluirPizza"})
public class ExcluirPizza extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
