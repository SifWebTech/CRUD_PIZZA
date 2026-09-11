package br.com.pizzaria.DAO;

import br.com.pizzaria.model.Pizza;
import br.com.pizzaria.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementacao da GenericDAO para a entidade Pizza.
 * Responsavel por toda a comunicacao da aplicacao com a tabela "pizza".
 */
public class PizzaDAOImpl implements GenericDAO {

    @Override
    public boolean cadastrar(Object object) {
        Pizza pizza = (Pizza) object;

        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO pizza (nome_pizza, descricao_pizza, tamanho_pizza, preco_pizza) "
                   + "VALUES (?, ?, ?, ?)";

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, pizza.getNomePizza());
            stmt.setString(2, pizza.getDescricaoPizza());
            stmt.setString(3, pizza.getTamanhoPizza());
            stmt.setBigDecimal(4, pizza.getPrecoPizza());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problema ao cadastrar a pizza: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(con, stmt);
            } catch (Exception ex) {
                System.out.println("Problema ao fechar a conexao: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public boolean alterar(Object object) {
        return false;
    }

    @Override
    public void excluir(int idObject) {
    }

    @Override
    public List<Object> listar() {
        return new ArrayList<Object>();
    }

    @Override
    public Object buscarPorId(int idObject) {
        return null;
    }
}
