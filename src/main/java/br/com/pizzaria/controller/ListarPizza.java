package br.com.pizzaria.controller;

import br.com.pizzaria.DAO.GenericDAO;
import br.com.pizzaria.DAO.PizzaDAOImpl;
import br.com.pizzaria.util.Mensagem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet responsavel por buscar as pizzas cadastradas
 * e envia-las para a pagina listarpizza.jsp.
 */
@WebServlet(name = "ListarPizza", urlPatterns = {"/ListarPizza"})
public class ListarPizza extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Mensagem.carregar(request);

        try {
            GenericDAO dao = new PizzaDAOImpl();
            List<Object> pizzas = dao.listar();
            request.setAttribute("pizzas", pizzas);
        } catch (Exception ex) {
            request.setAttribute(Mensagem.ERRO,
                    "Não foi possível acessar o banco de dados. Verifique a conexão com o PostgreSQL.");
            ex.printStackTrace();
        }

        request.getRequestDispatcher("listarpizza.jsp").forward(request, response);
    }
}
