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
import java.math.BigDecimal;

/**
 * Servlet responsavel pela alteracao de uma pizza.
 * No doGet carrega os dados atuais no formulario; no doPost grava as alteracoes.
 */
@WebServlet(name = "AlterarPizza", urlPatterns = {"/AlterarPizza"})
public class AlterarPizza extends HttpServlet {

    /**
     * Carrega a pizza escolhida na listagem e abre o formulario de alteracao.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idPizza = Integer.parseInt(request.getParameter("idPizza"));

        GenericDAO dao = new PizzaDAOImpl();
        Pizza pizza = (Pizza) dao.buscarPorId(idPizza);

        request.setAttribute("pizza", pizza);
        request.getRequestDispatcher("alterarpizza.jsp").forward(request, response);
    }

    /**
     * Grava as alteracoes enviadas pelo formulario e volta para a listagem.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String mensagem;

        try {
            Pizza pizza = new Pizza();
            pizza.setIdPizza(Integer.parseInt(request.getParameter("idPizza")));
            pizza.setNomePizza(request.getParameter("nomePizza"));
            pizza.setDescricaoPizza(request.getParameter("descricaoPizza"));
            pizza.setTamanhoPizza(request.getParameter("tamanhoPizza"));
            pizza.setPrecoPizza(new BigDecimal(request.getParameter("precoPizza").replace(",", ".")));

            GenericDAO dao = new PizzaDAOImpl();

            if (dao.alterar(pizza)) {
                mensagem = "Pizza alterada com sucesso!";
            } else {
                mensagem = "Nao foi possivel alterar a pizza.";
            }
        } catch (Exception ex) {
            mensagem = "Erro ao alterar a pizza: " + ex.getMessage();
            ex.printStackTrace();
        }

        request.setAttribute("sucesso", mensagem);
        request.getRequestDispatcher("ListarPizza").forward(request, response);
    }
}
