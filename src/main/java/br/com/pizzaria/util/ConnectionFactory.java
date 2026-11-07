// Informa o pacote (a pasta lógica) desta classe: br.com.pizzaria.util.
package br.com.pizzaria.util;

// Traz a classe que lê um arquivo de texto linha por linha.
import java.io.BufferedReader;
// Traz os comandos para trabalhar com arquivos.
import java.nio.file.Files;
// Traz a classe que representa o caminho de um arquivo.
import java.nio.file.Path;
// Traz a classe que representa a conexão com o banco.
import java.sql.Connection;
// Traz a classe que abre conexões usando o driver JDBC.
import java.sql.DriverManager;
// Traz a classe que executa SQL com parâmetros (os sinais de ?).
import java.sql.PreparedStatement;
// Traz a classe que guarda as linhas devolvidas por um SELECT.
import java.sql.ResultSet;
// Traz o tipo de erro lançado pelo banco de dados.
import java.sql.SQLException;

/**
 * Classe do pacote util responsavel pela conexao com o banco de dados.
 * Centraliza a abertura e o fechamento da conexao usada pela camada DAO.
 */
// Cria a classe pública ConnectionFactory, que cuida das conexões com o banco.
public class ConnectionFactory {

    // Constante com o nome da classe do driver JDBC do PostgreSQL.
    private static final String DRIVER = "org.postgresql.Driver";
    // Constante com o endereço do banco: tipo, máquina, porta e nome do banco.
    private static final String URL = "jdbc:postgresql://localhost:5433/crud_pizza";
    // Constante com o usuário do banco.
    private static final String USUARIO = "postgres";
    // Constante com a senha, lida da variável DB_PASSWORD ou do .env; sem nenhuma delas, usa postgres.
    private static final String SENHA = buscarConfiguracao("DB_PASSWORD", "postgres");

    /**
     * Abre uma nova conexao com o banco de dados.
     */
    // Método que devolve uma conexão aberta; static permite chamá-lo sem criar objeto.
    public static Connection getConnection() throws Exception {
        // Carrega na memória a classe do driver do PostgreSQL.
        Class.forName(DRIVER);
        // Abre a conexão com endereço, usuário e senha, e a devolve.
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    // Método interno que procura uma configuração; recebe o nome dela e um valor padrão.
    private static String buscarConfiguracao(String chave, String valorPadrao) {
        // Lê a variável de ambiente do sistema com esse nome.
        String valorAmbiente = System.getenv(chave);
        // Testa se a variável existe e não está em branco.
        if (valorAmbiente != null && !valorAmbiente.isBlank()) {
            // Devolve o valor encontrado e encerra o método.
            return valorAmbiente;
        }

        // Monta o caminho do arquivo .env na pasta em que o servidor foi iniciado.
        Path arquivoEnv = Path.of(".env");
        // Testa se o arquivo .env existe.
        if (Files.exists(arquivoEnv)) {
            // Abre o arquivo para leitura; esse try fecha o arquivo sozinho no final.
            try (BufferedReader reader = Files.newBufferedReader(arquivoEnv)) {
                // Declara a variável que guardará cada linha lida.
                String linha;
                // Repete enquanto houver linha para ler; readLine devolve null no fim do arquivo.
                while ((linha = reader.readLine()) != null) {
                    // Remove os espaços do início e do fim da linha.
                    String linhaTratada = linha.trim();
                    // Testa se a linha começa com o nome da configuração seguido de =.
                    if (linhaTratada.startsWith(chave + "=")) {
                        // Devolve só o que vem depois do sinal de =.
                        return linhaTratada.substring(chave.length() + 1).trim();
                    }
                }
            // Se acontecer outro erro no try, a execução continua aqui.
            } catch (Exception ex) {
                // Escreve o problema no console do servidor.
                System.out.println("Nao foi possivel ler o arquivo .env: " + ex.getMessage());
            }
        }

        // Nada foi encontrado: devolve o valor padrão.
        return valorPadrao;
    }

    /**
     * Fecha a conexao.
     */
    // Método que fecha a conexão; void significa que não devolve nada.
    public static void closeConnection(Connection con) throws SQLException {
        // Só fecha se a conexão chegou a ser aberta, evitando erro com null.
        if (con != null) {
            // Fecha a conexão com o banco.
            con.close();
        }
    }

    /**
     * Fecha a conexao e o comando SQL.
     */
    // Versão do método (sobrecarga) que fecha também o comando SQL.
    public static void closeConnection(Connection con, PreparedStatement stmt) throws SQLException {
        // Só fecha se o comando foi criado.
        if (stmt != null) {
            // Fecha o comando SQL.
            stmt.close();
        }
        // Chama a versão anterior para fechar a conexão.
        closeConnection(con);
    }

    /**
     * Fecha a conexao, o comando SQL e o resultado da consulta.
     */
    // Versão do método que fecha também o resultado da consulta.
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs)
            // throws avisa que o método pode lançar erro do banco; a chave abre o corpo do método.
            throws SQLException {
        // Só fecha se existe resultado.
        if (rs != null) {
            // Fecha o resultado da consulta.
            rs.close();
        }
        // Chama a versão anterior para fechar o comando e a conexão.
        closeConnection(con, stmt);
    }
}
