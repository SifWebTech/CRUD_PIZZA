package br.com.pizzaria.util;

import br.com.pizzaria.model.Pizza;

import java.math.BigDecimal;
import java.util.List;

/**
 * Valida no servidor os dados de uma pizza antes de gravar no banco.
 * A validacao do formulario pode ser ignorada, por isso e repetida aqui.
 */
public class ValidadorPizza {

    private static final List<String> TAMANHOS = List.of("Pequena", "Media", "Grande");
    private static final BigDecimal PRECO_MAXIMO = new BigDecimal("99999999.99");

    /**
     * Converte o preco digitado. Retorna null quando o valor nao e um numero.
     */
    public static BigDecimal converterPreco(String preco) {
        if (preco == null) {
            return null;
        }

        try {
            return new BigDecimal(preco.trim().replace(",", "."));
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    /**
     * Retorna a mensagem do primeiro erro encontrado, ou null se estiver tudo certo.
     */
    public static String validar(Pizza pizza) {
        String nome = pizza.getNomePizza();
        String descricao = pizza.getDescricaoPizza();
        BigDecimal preco = pizza.getPrecoPizza();

        if (nome == null || nome.isBlank()) {
            return "Informe o nome da pizza.";
        }
        if (nome.length() > 60) {
            return "O nome deve ter no máximo 60 caracteres.";
        }
        if (descricao == null || descricao.isBlank()) {
            return "Informe a descrição da pizza.";
        }
        if (descricao.length() > 200) {
            return "A descrição deve ter no máximo 200 caracteres.";
        }
        if (!TAMANHOS.contains(pizza.getTamanhoPizza())) {
            return "Escolha um tamanho válido.";
        }
        if (preco == null) {
            return "Informe um preço válido.";
        }
        if (preco.signum() <= 0) {
            return "O preço deve ser maior que zero.";
        }
        if (preco.compareTo(PRECO_MAXIMO) > 0) {
            return "O preço informado é muito alto.";
        }
        if (preco.stripTrailingZeros().scale() > 2) {
            return "O preço deve ter no máximo 2 casas decimais.";
        }
        return null;
    }
}
