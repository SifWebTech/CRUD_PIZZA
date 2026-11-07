package br.com.pizzaria.DAO;

import java.util.List;

/**
 * Interface que define as operacoes basicas de persistencia do sistema.
 * Cada entidade do projeto tera uma implementacao desta interface.
 *
 * Falhas de banco sao lancadas como excecao para o controller avisar o usuario.
 * Em alterar e excluir, o retorno false indica que o registro nao foi encontrado.
 */
public interface GenericDAO {

    public boolean cadastrar(Object object) throws Exception;

    public boolean alterar(Object object) throws Exception;

    public boolean excluir(int idObject) throws Exception;

    public List<Object> listar() throws Exception;

    public Object buscarPorId(int idObject) throws Exception;
}
