// Informa o pacote (a pasta lógica) desta classe: br.com.pizzaria.DAO.
package br.com.pizzaria.DAO;

// Traz a interface de lista do Java.
import java.util.List;

/**
 * Interface que define as operacoes basicas de persistencia do sistema.
 * Cada entidade do projeto tera uma implementacao desta interface.
 *
 * Falhas de banco sao lancadas como excecao para o controller avisar o usuario.
 * Em alterar e excluir, o retorno false indica que o registro nao foi encontrado.
 */
// Cria a interface: um contrato que lista os métodos, sem escrever o código deles.
public interface GenericDAO {

    // Grava um objeto novo. Object aceita qualquer entidade do sistema.
    public boolean cadastrar(Object object) throws Exception;

    // Atualiza um registro; devolve false se ele não for encontrado.
    public boolean alterar(Object object) throws Exception;

    // Apaga um registro pelo código; devolve false se ele não for encontrado.
    public boolean excluir(int idObject) throws Exception;

    // Devolve a lista com todos os registros.
    public List<Object> listar() throws Exception;

    // Busca um único registro pelo código.
    public Object buscarPorId(int idObject) throws Exception;
}
