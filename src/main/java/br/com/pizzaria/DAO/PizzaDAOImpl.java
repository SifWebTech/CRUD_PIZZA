package br.com.pizzaria.DAO;

import br.com.pizzaria.model.Pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementacao da GenericDAO para a entidade Pizza.
 * Responsavel por toda a comunicacao da aplicacao com a tabela "pizza".
 */
public class PizzaDAOImpl implements GenericDAO {

    @Override
    public boolean cadastrar(Object object) {
        return false;
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
