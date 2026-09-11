package br.com.pizzaria.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe do pacote util responsavel pela conexao com o banco de dados.
 * Centraliza a abertura e o fechamento da conexao usada pela camada DAO.
 */
public class ConnectionFactory {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5433/crud_pizza";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    /**
     * Abre uma nova conexao com o banco de dados.
     */
    public static Connection getConnection() throws Exception {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    /**
     * Fecha a conexao.
     */
    public static void closeConnection(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    /**
     * Fecha a conexao e o comando SQL.
     */
    public static void closeConnection(Connection con, PreparedStatement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
        closeConnection(con);
    }

    /**
     * Fecha a conexao, o comando SQL e o resultado da consulta.
     */
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs)
            throws SQLException {
        if (rs != null) {
            rs.close();
        }
        closeConnection(con, stmt);
    }
}
