// Informa o pacote (a pasta lógica) desta classe: br.com.pizzaria.model.
package br.com.pizzaria.model;

// Traz o tipo numérico exato, próprio para valores em dinheiro.
import java.math.BigDecimal;

/**
 * Classe do pacote model que representa a entidade principal do projeto.
 * Cada objeto Pizza corresponde a um registro da tabela "pizza".
 */
// Cria a classe Pizza; cada objeto dela corresponde a uma linha da tabela pizza.
public class Pizza {

    // Atributo com o código da pizza; private só permite acesso por dentro da classe.
    private int idPizza;
    // Atributo com o nome da pizza.
    private String nomePizza;
    // Atributo com a descrição da pizza.
    private String descricaoPizza;
    // Atributo com o tamanho da pizza.
    private String tamanhoPizza;
    // Atributo com o preço; BigDecimal evita erros de arredondamento.
    private BigDecimal precoPizza;

    // Construtor vazio: cria a pizza sem dados, para preencher depois com os setters.
    public Pizza() {
    }

    // Construtor sem código, pois quem gera o código é o banco.
    public Pizza(String nomePizza, String descricaoPizza, String tamanhoPizza, BigDecimal precoPizza) {
        // this.nomePizza é o atributo do objeto; recebe o valor do parâmetro.
        this.nomePizza = nomePizza;
        // this.descricaoPizza é o atributo do objeto; recebe o valor do parâmetro.
        this.descricaoPizza = descricaoPizza;
        // this.tamanhoPizza é o atributo do objeto; recebe o valor do parâmetro.
        this.tamanhoPizza = tamanhoPizza;
        // this.precoPizza é o atributo do objeto; recebe o valor do parâmetro.
        this.precoPizza = precoPizza;
    }

    // Construtor completo, com todos os atributos.
    public Pizza(int idPizza, String nomePizza, String descricaoPizza, String tamanhoPizza,
                 // Continuação dos parâmetros do construtor.
                 BigDecimal precoPizza) {
        // this.idPizza é o atributo do objeto; recebe o valor do parâmetro.
        this.idPizza = idPizza;
        // this.nomePizza é o atributo do objeto; recebe o valor do parâmetro.
        this.nomePizza = nomePizza;
        // this.descricaoPizza é o atributo do objeto; recebe o valor do parâmetro.
        this.descricaoPizza = descricaoPizza;
        // this.tamanhoPizza é o atributo do objeto; recebe o valor do parâmetro.
        this.tamanhoPizza = tamanhoPizza;
        // this.precoPizza é o atributo do objeto; recebe o valor do parâmetro.
        this.precoPizza = precoPizza;
    }

    // Getter: devolve o código da pizza.
    public int getIdPizza() {
        // Devolve o valor guardado no atributo.
        return idPizza;
    }

    // Setter: recebe e guarda o código da pizza.
    public void setIdPizza(int idPizza) {
        // this.idPizza é o atributo do objeto; recebe o valor do parâmetro.
        this.idPizza = idPizza;
    }

    // Getter: devolve o nome da pizza.
    public String getNomePizza() {
        // Devolve o valor guardado no atributo.
        return nomePizza;
    }

    // Setter: recebe e guarda o nome da pizza.
    public void setNomePizza(String nomePizza) {
        // this.nomePizza é o atributo do objeto; recebe o valor do parâmetro.
        this.nomePizza = nomePizza;
    }

    // Getter: devolve a descrição da pizza.
    public String getDescricaoPizza() {
        // Devolve o valor guardado no atributo.
        return descricaoPizza;
    }

    // Setter: recebe e guarda a descrição da pizza.
    public void setDescricaoPizza(String descricaoPizza) {
        // this.descricaoPizza é o atributo do objeto; recebe o valor do parâmetro.
        this.descricaoPizza = descricaoPizza;
    }

    // Getter: devolve o tamanho da pizza.
    public String getTamanhoPizza() {
        // Devolve o valor guardado no atributo.
        return tamanhoPizza;
    }

    // Setter: recebe e guarda o tamanho da pizza.
    public void setTamanhoPizza(String tamanhoPizza) {
        // this.tamanhoPizza é o atributo do objeto; recebe o valor do parâmetro.
        this.tamanhoPizza = tamanhoPizza;
    }

    // Getter: devolve o preço da pizza.
    public BigDecimal getPrecoPizza() {
        // Devolve o valor guardado no atributo.
        return precoPizza;
    }

    // Setter: recebe e guarda o preço da pizza.
    public void setPrecoPizza(BigDecimal precoPizza) {
        // this.precoPizza é o atributo do objeto; recebe o valor do parâmetro.
        this.precoPizza = precoPizza;
    }

    // Indica que o método abaixo reimplementa um método herdado ou definido na interface.
    @Override
    // Define como a pizza vira texto, útil para testes e para o console.
    public String toString() {
        // Monta o texto juntando os valores dos atributos.
        return "Pizza{" + "idPizza=" + idPizza + ", nomePizza=" + nomePizza
                // Continuação do texto montado.
                + ", tamanhoPizza=" + tamanhoPizza + ", precoPizza=" + precoPizza + '}';
    }
}
