package br.com.pizzaria.DAO;

import br.com.pizzaria.model.Pizza;
import br.com.pizzaria.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        Pizza pizza = (Pizza) object;

        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "UPDATE pizza SET nome_pizza = ?, descricao_pizza = ?, tamanho_pizza = ?, "
                   + "preco_pizza = ? WHERE id_pizza = ?";

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, pizza.getNomePizza());
            stmt.setString(2, pizza.getDescricaoPizza());
            stmt.setString(3, pizza.getTamanhoPizza());
            stmt.setBigDecimal(4, pizza.getPrecoPizza());
            stmt.setInt(5, pizza.getIdPizza());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Problema ao alterar a pizza: " + ex.getMessage());
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
    public void excluir(int idObject) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM pizza WHERE id_pizza = ?";

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Problema ao excluir a pizza: " + ex.getMessage());
            ex.printStackTrace();
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
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<Object>();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id_pizza, nome_pizza, descricao_pizza, tamanho_pizza, preco_pizza "
                   + "FROM pizza ORDER BY nome_pizza";

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Pizza pizza = new Pizza();
                pizza.setIdPizza(rs.getInt("id_pizza"));
                pizza.setNomePizza(rs.getString("nome_pizza"));
                pizza.setDescricaoPizza(rs.getString("descricao_pizza"));
                pizza.setTamanhoPizza(rs.getString("tamanho_pizza"));
                pizza.setPrecoPizza(rs.getBigDecimal("preco_pizza"));
                resultado.add(pizza);
            }
        } catch (Exception ex) {
            System.out.println("Problema ao listar as pizzas: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(con, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problema ao fechar a conexao: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    /**
     * Busca uma unica pizza pelo seu id. Utilizada para carregar a tela de alteracao.
     */
    @Override
    public Object buscarPorId(int idObject) {
        Pizza pizza = null;

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id_pizza, nome_pizza, descricao_pizza, tamanho_pizza, preco_pizza "
                   + "FROM pizza WHERE id_pizza = ?";

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();

            if (rs.next()) {
                pizza = new Pizza();
                pizza.setIdPizza(rs.getInt("id_pizza"));
                pizza.setNomePizza(rs.getString("nome_pizza"));
                pizza.setDescricaoPizza(rs.getString("descricao_pizza"));
                pizza.setTamanhoPizza(rs.getString("tamanho_pizza"));
                pizza.setPrecoPizza(rs.getBigDecimal("preco_pizza"));
            }
        } catch (Exception ex) {
            System.out.println("Problema ao buscar a pizza: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(con, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problema ao fechar a conexao: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return pizza;
    }
}
