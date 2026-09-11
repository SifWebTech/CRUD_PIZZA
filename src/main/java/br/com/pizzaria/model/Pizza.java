package br.com.pizzaria.model;

import java.math.BigDecimal;

/**
 * Classe do pacote model que representa a entidade principal do projeto.
 * Cada objeto Pizza corresponde a um registro da tabela "pizza".
 */
public class Pizza {

    private int idPizza;
    private String nomePizza;
    private String descricaoPizza;
    private String tamanhoPizza;
    private BigDecimal precoPizza;

    public Pizza() {
    }

    public Pizza(String nomePizza, String descricaoPizza, String tamanhoPizza, BigDecimal precoPizza) {
        this.nomePizza = nomePizza;
        this.descricaoPizza = descricaoPizza;
        this.tamanhoPizza = tamanhoPizza;
        this.precoPizza = precoPizza;
    }

    public Pizza(int idPizza, String nomePizza, String descricaoPizza, String tamanhoPizza,
                 BigDecimal precoPizza) {
        this.idPizza = idPizza;
        this.nomePizza = nomePizza;
        this.descricaoPizza = descricaoPizza;
        this.tamanhoPizza = tamanhoPizza;
        this.precoPizza = precoPizza;
    }

    public int getIdPizza() {
        return idPizza;
    }

    public void setIdPizza(int idPizza) {
        this.idPizza = idPizza;
    }

    public String getNomePizza() {
        return nomePizza;
    }

    public void setNomePizza(String nomePizza) {
        this.nomePizza = nomePizza;
    }

    public String getDescricaoPizza() {
        return descricaoPizza;
    }

    public void setDescricaoPizza(String descricaoPizza) {
        this.descricaoPizza = descricaoPizza;
    }

    public String getTamanhoPizza() {
        return tamanhoPizza;
    }

    public void setTamanhoPizza(String tamanhoPizza) {
        this.tamanhoPizza = tamanhoPizza;
    }

    public BigDecimal getPrecoPizza() {
        return precoPizza;
    }

    public void setPrecoPizza(BigDecimal precoPizza) {
        this.precoPizza = precoPizza;
    }

    @Override
    public String toString() {
        return "Pizza{" + "idPizza=" + idPizza + ", nomePizza=" + nomePizza
                + ", tamanhoPizza=" + tamanhoPizza + ", precoPizza=" + precoPizza + '}';
    }
}
