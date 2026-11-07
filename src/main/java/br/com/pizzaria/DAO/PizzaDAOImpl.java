// Informa o pacote (a pasta lógica) desta classe: br.com.pizzaria.DAO.
package br.com.pizzaria.DAO;

// Traz a classe Pizza, que representa uma pizza no sistema.
import br.com.pizzaria.model.Pizza;
// Traz a classe que abre e fecha a conexão com o banco.
import br.com.pizzaria.util.ConnectionFactory;

// Traz a classe que representa a conexão com o banco.
import java.sql.Connection;
// Traz a classe que executa SQL com parâmetros (os sinais de ?).
import java.sql.PreparedStatement;
// Traz a classe que guarda as linhas devolvidas por um SELECT.
import java.sql.ResultSet;
// Traz o tipo de erro lançado pelo banco de dados.
import java.sql.SQLException;
// Traz a lista dinâmica, que cresce conforme os itens entram.
import java.util.ArrayList;
// Traz a interface de lista do Java.
import java.util.List;

/**
 * Implementacao da GenericDAO para a entidade Pizza.
 * Responsavel por toda a comunicacao da aplicacao com a tabela "pizza".
 */
// Cria a classe; implements obriga a escrever todos os métodos da interface GenericDAO.
public class PizzaDAOImpl implements GenericDAO {

    // Indica que o método abaixo reimplementa um método herdado ou definido na interface.
    @Override
    // Grava uma pizza nova no banco. throws repassa erros para o controller tratar.
    public boolean cadastrar(Object object) throws Exception {
        // Converte (cast) o Object recebido para o tipo Pizza.
        Pizza pizza = (Pizza) object;

        // Declara a conexão vazia, fora do try, para o finally conseguir fechá-la.
        Connection con = null;
        // Declara o comando SQL vazio, pelo mesmo motivo.
        PreparedStatement stmt = null;
        // Texto do SQL: INSERT INTO insere uma linha nessas colunas da tabela pizza.
        String sql = "INSERT INTO pizza (nome_pizza, descricao_pizza, tamanho_pizza, preco_pizza) "
                   // Cada ? será trocado por um valor, o que protege contra SQL Injection.
                   + "VALUES (?, ?, ?, ?)";

        // Início do trecho que pode dar erro; se der, o Java pula para o catch ou o finally.
        try {
            // Abre a conexão com o banco.
            con = ConnectionFactory.getConnection();
            // Prepara o comando SQL nessa conexão.
            stmt = con.prepareStatement(sql);
            // Coloca o nome da pizza no 1º sinal de ?.
            stmt.setString(1, pizza.getNomePizza());
            // Coloca a descrição da pizza no 2º sinal de ?.
            stmt.setString(2, pizza.getDescricaoPizza());
            // Coloca o tamanho da pizza no 3º sinal de ?.
            stmt.setString(3, pizza.getTamanhoPizza());
            // Coloca o preço da pizza no 4º sinal de ?.
            stmt.setBigDecimal(4, pizza.getPrecoPizza());
            // Executa o SQL; devolve true se pelo menos uma linha foi afetada.
            return stmt.executeUpdate() > 0;
        // Este bloco roda sempre, com erro ou sem erro.
        } finally {
            // Fecha o comando e a conexão. Aqui não há ResultSet, por isso vai null.
            fechar(con, stmt, null);
        }
    }

    // Indica que o método abaixo reimplementa um método herdado ou definido na interface.
    @Override
    // Atualiza uma pizza existente. throws repassa erros para o controller tratar.
    public boolean alterar(Object object) throws Exception {
        // Converte (cast) o Object recebido para o tipo Pizza.
        Pizza pizza = (Pizza) object;

        // Declara a conexão vazia, fora do try, para o finally conseguir fechá-la.
        Connection con = null;
        // Declara o comando SQL vazio, pelo mesmo motivo.
        PreparedStatement stmt = null;
        // Texto do SQL: UPDATE muda os valores das colunas indicadas em SET.
        String sql = "UPDATE pizza SET nome_pizza = ?, descricao_pizza = ?, tamanho_pizza = ?, "
                   // Continuação: WHERE limita a mudança à pizza com esse código.
                   + "preco_pizza = ? WHERE id_pizza = ?";

        // Início do trecho que pode dar erro; se der, o Java pula para o catch ou o finally.
        try {
            // Abre a conexão com o banco.
            con = ConnectionFactory.getConnection();
            // Prepara o comando SQL nessa conexão.
            stmt = con.prepareStatement(sql);
            // Coloca o nome da pizza no 1º sinal de ?.
            stmt.setString(1, pizza.getNomePizza());
            // Coloca a descrição da pizza no 2º sinal de ?.
            stmt.setString(2, pizza.getDescricaoPizza());
            // Coloca o tamanho da pizza no 3º sinal de ?.
            stmt.setString(3, pizza.getTamanhoPizza());
            // Coloca o preço da pizza no 4º sinal de ?.
            stmt.setBigDecimal(4, pizza.getPrecoPizza());
            // Coloca o código da pizza no 5º sinal de ?.
            stmt.setInt(5, pizza.getIdPizza());
            // Executa o SQL; devolve true se pelo menos uma linha foi afetada.
            return stmt.executeUpdate() > 0;
        // Este bloco roda sempre, com erro ou sem erro.
        } finally {
            // Fecha o comando e a conexão. Aqui não há ResultSet, por isso vai null.
            fechar(con, stmt, null);
        }
    }

    // Indica que o método abaixo reimplementa um método herdado ou definido na interface.
    @Override
    // Apaga a pizza do código informado. throws repassa erros para o controller tratar.
    public boolean excluir(int idObject) throws Exception {
        // Declara a conexão vazia, fora do try, para o finally conseguir fechá-la.
        Connection con = null;
        // Declara o comando SQL vazio, pelo mesmo motivo.
        PreparedStatement stmt = null;
        // Texto do SQL: DELETE apaga a linha com esse código.
        String sql = "DELETE FROM pizza WHERE id_pizza = ?";

        // Início do trecho que pode dar erro; se der, o Java pula para o catch ou o finally.
        try {
            // Abre a conexão com o banco.
            con = ConnectionFactory.getConnection();
            // Prepara o comando SQL nessa conexão.
            stmt = con.prepareStatement(sql);
            // Coloca o código recebido no 1º sinal de ?.
            stmt.setInt(1, idObject);
            // Executa o SQL; devolve true se pelo menos uma linha foi afetada.
            return stmt.executeUpdate() > 0;
        // Este bloco roda sempre, com erro ou sem erro.
        } finally {
            // Fecha o comando e a conexão. Aqui não há ResultSet, por isso vai null.
            fechar(con, stmt, null);
        }
    }

    // Indica que o método abaixo reimplementa um método herdado ou definido na interface.
    @Override
    // Busca todas as pizzas. throws repassa erros para o controller tratar.
    public List<Object> listar() throws Exception {
        // Cria a lista vazia que receberá as pizzas.
        List<Object> resultado = new ArrayList<Object>();

        // Declara a conexão vazia, fora do try, para o finally conseguir fechá-la.
        Connection con = null;
        // Declara o comando SQL vazio, pelo mesmo motivo.
        PreparedStatement stmt = null;
        // Declara o resultado da consulta vazio, pelo mesmo motivo.
        ResultSet rs = null;
        // Texto do SQL: SELECT escolhe as colunas que serão lidas.
        String sql = "SELECT id_pizza, nome_pizza, descricao_pizza, tamanho_pizza, preco_pizza "
                   // Continuação: lê da tabela pizza, em ordem de nome.
                   + "FROM pizza ORDER BY nome_pizza";

        // Início do trecho que pode dar erro; se der, o Java pula para o catch ou o finally.
        try {
            // Abre a conexão com o banco.
            con = ConnectionFactory.getConnection();
            // Prepara o comando SQL nessa conexão.
            stmt = con.prepareStatement(sql);
            // Executa o SELECT e guarda as linhas devolvidas.
            rs = stmt.executeQuery();

            // Repete para cada linha; next() avança e devolve false quando acabam.
            while (rs.next()) {
                // Transforma a linha em Pizza e coloca na lista.
                resultado.add(montarPizza(rs));
            }
        // Este bloco roda sempre, com erro ou sem erro.
        } finally {
            // Fecha o resultado, o comando e a conexão.
            fechar(con, stmt, rs);
        }
        // Devolve a lista pronta.
        return resultado;
    }

    /**
     * Busca uma unica pizza pelo seu id. Retorna null se ela nao existir.
     */
    // Indica que o método abaixo reimplementa um método herdado ou definido na interface.
    @Override
    // Busca uma pizza pelo código. throws repassa erros para o controller tratar.
    public Object buscarPorId(int idObject) throws Exception {
        // Começa sem pizza; continua null se o código não existir.
        Pizza pizza = null;

        // Declara a conexão vazia, fora do try, para o finally conseguir fechá-la.
        Connection con = null;
        // Declara o comando SQL vazio, pelo mesmo motivo.
        PreparedStatement stmt = null;
        // Declara o resultado da consulta vazio, pelo mesmo motivo.
        ResultSet rs = null;
        // Texto do SQL: SELECT escolhe as colunas que serão lidas.
        String sql = "SELECT id_pizza, nome_pizza, descricao_pizza, tamanho_pizza, preco_pizza "
                   // Continuação: lê da tabela pizza só a linha com esse código.
                   + "FROM pizza WHERE id_pizza = ?";

        // Início do trecho que pode dar erro; se der, o Java pula para o catch ou o finally.
        try {
            // Abre a conexão com o banco.
            con = ConnectionFactory.getConnection();
            // Prepara o comando SQL nessa conexão.
            stmt = con.prepareStatement(sql);
            // Coloca o código recebido no 1º sinal de ?.
            stmt.setInt(1, idObject);
            // Executa o SELECT e guarda as linhas devolvidas.
            rs = stmt.executeQuery();

            // Testa se o banco devolveu uma linha.
            if (rs.next()) {
                // Transforma a linha encontrada em Pizza.
                pizza = montarPizza(rs);
            }
        // Este bloco roda sempre, com erro ou sem erro.
        } finally {
            // Fecha o resultado, o comando e a conexão.
            fechar(con, stmt, rs);
        }
        // Devolve a pizza, ou null se ela não existir.
        return pizza;
    }

    // Método interno que converte a linha atual do banco em um objeto Pizza.
    private Pizza montarPizza(ResultSet rs) throws SQLException {
        // Cria um objeto Pizza vazio.
        Pizza pizza = new Pizza();
        // Lê o código da coluna id_pizza e guarda no objeto.
        pizza.setIdPizza(rs.getInt("id_pizza"));
        // Lê o nome da coluna nome_pizza e guarda no objeto.
        pizza.setNomePizza(rs.getString("nome_pizza"));
        // Lê a descrição da coluna descricao_pizza e guarda no objeto.
        pizza.setDescricaoPizza(rs.getString("descricao_pizza"));
        // Lê o tamanho da coluna tamanho_pizza e guarda no objeto.
        pizza.setTamanhoPizza(rs.getString("tamanho_pizza"));
        // Lê o preço da coluna preco_pizza e guarda no objeto.
        pizza.setPrecoPizza(rs.getBigDecimal("preco_pizza"));
        // Devolve a pizza, ou null se ela não existir.
        return pizza;
    }

    // Método interno que fecha os recursos sem esconder o erro principal.
    private void fechar(Connection con, PreparedStatement stmt, ResultSet rs) {
        // Início do trecho que pode dar erro; se der, o Java pula para o catch ou o finally.
        try {
            // Fecha o resultado, o comando e a conexão.
            ConnectionFactory.closeConnection(con, stmt, rs);
        // Se der erro do banco nesse trecho, a execução continua aqui.
        } catch (SQLException ex) {
            // Escreve o problema no console do servidor.
            System.out.println("Problema ao fechar a conexao: " + ex.getMessage());
            // Escreve no console o detalhe completo do erro.
            ex.printStackTrace();
        }
    }
}
