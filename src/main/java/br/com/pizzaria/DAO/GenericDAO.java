package br.com.pizzaria.DAO;

import java.util.List;

/**
 * Interface que define as operacoes basicas de persistencia do sistema.
 * Cada entidade do projeto tera uma implementacao desta interface.
 */
public interface GenericDAO {

    public boolean cadastrar(Object object);

    public boolean alterar(Object object);

    public void excluir(int idObject);

    public List<Object> listar();

    public Object buscarPorId(int idObject);
}
