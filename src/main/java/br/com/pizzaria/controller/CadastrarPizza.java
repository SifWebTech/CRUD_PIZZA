package br.com.pizzaria.controller;

import br.com.pizzaria.DAO.GenericDAO;
import br.com.pizzaria.DAO.PizzaDAOImpl;
import br.com.pizzaria.model.Pizza;
import br.com.pizzaria.util.Mensagem;
import br.com.pizzaria.util.ValidadorPizza;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet responsavel pelo cadastro de uma nova pizza.
 * No GET abre o formulario; no POST valida e envia os dados para a camada DAO.
 */
@WebServlet(name = "CadastrarPizza", urlPatterns = {"/CadastrarPizza"})
public class CadastrarPizza extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Mensagem.carregar(request);
        request.getRequestDispatcher("cadastrarpizza.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Pizza pizza = new Pizza();
        pizza.setNomePizza(limpar(request.getParameter("nomePizza")));
        pizza.setDescricaoPizza(limpar(request.getParameter("descricaoPizza")));
        pizza.setTamanhoPizza(request.getParameter("tamanhoPizza"));
        pizza.setPrecoPizza(ValidadorPizza.converterPreco(request.getParameter("precoPizza")));

        String erro = ValidadorPizza.validar(pizza);

        if (erro == null) {
            try {
                GenericDAO dao = new PizzaDAOImpl();
                dao.cadastrar(pizza);

                // Redireciona para que o F5 nao envie o cadastro novamente.
                Mensagem.guardar(request, Mensagem.SUCESSO, "Pizza cadastrada com sucesso!");
                response.sendRedirect("CadastrarPizza");
                return;
            } catch (Exception ex) {
                erro = "Não foi possível cadastrar a pizza. Verifique a conexão com o PostgreSQL.";
                ex.printStackTrace();
            }
        }

        request.setAttribute(Mensagem.ERRO, erro);
        request.getRequestDispatcher("cadastrarpizza.jsp").forward(request, response);
    }

    private String limpar(String texto) {
        return texto == null ? null : texto.trim();
    }
}
