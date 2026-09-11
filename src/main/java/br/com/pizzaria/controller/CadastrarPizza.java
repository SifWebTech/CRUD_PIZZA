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
 * Servlet responsavel pelo cadastro de uma nova pizza.
 * Recebe os dados da pagina cadastrarpizza.jsp e envia para a camada DAO.
 */
@WebServlet(name = "CadastrarPizza", urlPatterns = {"/CadastrarPizza"})
public class CadastrarPizza extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nomePizza = request.getParameter("nomePizza");
        String descricaoPizza = request.getParameter("descricaoPizza");
        String tamanhoPizza = request.getParameter("tamanhoPizza");
        String precoPizza = request.getParameter("precoPizza");

        String mensagem;

        try {
            Pizza pizza = new Pizza();
            pizza.setNomePizza(nomePizza);
            pizza.setDescricaoPizza(descricaoPizza);
            pizza.setTamanhoPizza(tamanhoPizza);
            pizza.setPrecoPizza(new BigDecimal(precoPizza.replace(",", ".")));

            GenericDAO dao = new PizzaDAOImpl();

            if (dao.cadastrar(pizza)) {
                mensagem = "Pizza cadastrada com sucesso!";
            } else {
                mensagem = "Nao foi possivel cadastrar a pizza.";
            }
        } catch (Exception ex) {
            mensagem = "Erro ao cadastrar a pizza: " + ex.getMessage();
            ex.printStackTrace();
        }

        request.setAttribute("sucesso", mensagem);
        request.getRequestDispatcher("cadastrarpizza.jsp").forward(request, response);
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
