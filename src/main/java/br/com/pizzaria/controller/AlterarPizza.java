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
        request.getRequestDispatcher("alterarpizza.jsp").forward(request, response);
    }

    /**
     * Grava as alteracoes enviadas pelo formulario e volta para a listagem.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Pizza pizza = new Pizza();

        try {
            pizza.setIdPizza(Integer.parseInt(request.getParameter("idPizza")));
        } catch (NumberFormatException ex) {
            voltarParaListagem(request, response, Mensagem.ERRO, "Código de pizza inválido.");
            return;
        }

        pizza.setNomePizza(limpar(request.getParameter("nomePizza")));
        pizza.setDescricaoPizza(limpar(request.getParameter("descricaoPizza")));
        pizza.setTamanhoPizza(request.getParameter("tamanhoPizza"));
        pizza.setPrecoPizza(ValidadorPizza.converterPreco(request.getParameter("precoPizza")));

        String erro = ValidadorPizza.validar(pizza);

        if (erro == null) {
            try {
                GenericDAO dao = new PizzaDAOImpl();

                if (dao.alterar(pizza)) {
                    voltarParaListagem(request, response, Mensagem.SUCESSO, "Pizza alterada com sucesso!");
                } else {
                    voltarParaListagem(request, response, Mensagem.ERRO, "Pizza não encontrada.");
                }
                return;
            } catch (Exception ex) {
                erro = "Não foi possível alterar a pizza. Verifique a conexão com o PostgreSQL.";
                ex.printStackTrace();
            }
        }

        request.setAttribute(Mensagem.ERRO, erro);
        request.setAttribute("pizza", pizza);
        request.getRequestDispatcher("alterarpizza.jsp").forward(request, response);
    }

    private void voltarParaListagem(HttpServletRequest request, HttpServletResponse response,
                                    String tipo, String texto) throws IOException {
        Mensagem.guardar(request, tipo, texto);
        response.sendRedirect("ListarPizza");
    }

    private String limpar(String texto) {
        return texto == null ? null : texto.trim();
    }
}
