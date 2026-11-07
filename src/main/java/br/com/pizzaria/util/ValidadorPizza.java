// Informa o pacote (a pasta lógica) desta classe: br.com.pizzaria.util.
package br.com.pizzaria.util;

// Traz a classe Pizza, que representa uma pizza no sistema.
import br.com.pizzaria.model.Pizza;

// Traz o tipo numérico exato, próprio para valores em dinheiro.
import java.math.BigDecimal;
// Traz a interface de lista do Java.
import java.util.List;

/**
 * Valida no servidor os dados de uma pizza antes de gravar no banco.
 * A validacao do formulario pode ser ignorada, por isso e repetida aqui.
 */
// Cria a classe ValidadorPizza, com as regras dos dados de uma pizza.
public class ValidadorPizza {

    // Lista fixa dos tamanhos aceitos.
    private static final List<String> TAMANHOS = List.of("Pequena", "Media", "Grande");
    // Maior preço que cabe na coluna NUMERIC(10, 2) do banco.
    private static final BigDecimal PRECO_MAXIMO = new BigDecimal("99999999.99");

    /**
     * Converte o preco digitado. Retorna null quando o valor nao e um numero.
     */
    // Método que transforma o texto digitado em número.
    public static BigDecimal converterPreco(String preco) {
        // Testa se o preço não foi informado ou é inválido.
        if (preco == null) {
            // Devolve null: nesta validação, significa que não há valor ou erro.
            return null;
        }

        // Início do trecho que pode dar erro; se der, o Java pula para o catch ou o finally.
        try {
            // Tira os espaços, troca vírgula por ponto e converte para número.
            return new BigDecimal(preco.trim().replace(",", "."));
        // Se o texto recebido não for um número, a execução continua aqui.
        } catch (NumberFormatException ex) {
            // Devolve null: nesta validação, significa que não há valor ou erro.
            return null;
        }
    }

    /**
     * Retorna a mensagem do primeiro erro encontrado, ou null se estiver tudo certo.
     */
    // Método que confere a pizza e devolve o texto do erro, ou null se estiver certa.
    public static String validar(Pizza pizza) {
        // Copia o nome para uma variável local.
        String nome = pizza.getNomePizza();
        // Copia a descrição para uma variável local.
        String descricao = pizza.getDescricaoPizza();
        // Copia o preço para uma variável local.
        BigDecimal preco = pizza.getPrecoPizza();

        // Testa se o nome está ausente ou só tem espaços.
        if (nome == null || nome.isBlank()) {
            // Devolve a mensagem de erro.
            return "Informe o nome da pizza.";
        }
        // Testa se o nome passou do limite da coluna VARCHAR(60).
        if (nome.length() > 60) {
            // Devolve a mensagem de erro.
            return "O nome deve ter no máximo 60 caracteres.";
        }
        // Testa se a descrição está ausente ou só tem espaços.
        if (descricao == null || descricao.isBlank()) {
            // Devolve a mensagem de erro.
            return "Informe a descrição da pizza.";
        }
        // Testa se a descrição passou do limite da coluna VARCHAR(200).
        if (descricao.length() > 200) {
            // Devolve a mensagem de erro.
            return "A descrição deve ter no máximo 200 caracteres.";
        }
        // Testa se o tamanho não está na lista permitida.
        if (!TAMANHOS.contains(pizza.getTamanhoPizza())) {
            // Devolve a mensagem de erro.
            return "Escolha um tamanho válido.";
        }
        // Testa se o preço não foi informado ou é inválido.
        if (preco == null) {
            // Devolve a mensagem de erro.
            return "Informe um preço válido.";
        }
        // signum devolve -1, 0 ou 1; testa se o preço é zero ou negativo.
        if (preco.signum() <= 0) {
            // Devolve a mensagem de erro.
            return "O preço deve ser maior que zero.";
        }
        // Compara o preço com o limite; maior que 0 significa que passou.
        if (preco.compareTo(PRECO_MAXIMO) > 0) {
            // Devolve a mensagem de erro.
            return "O preço informado é muito alto.";
        }
        // Ignora zeros finais e testa se há mais de 2 casas decimais.
        if (preco.stripTrailingZeros().scale() > 2) {
            // Devolve a mensagem de erro.
            return "O preço deve ter no máximo 2 casas decimais.";
        }
        // Devolve null: nesta validação, significa que não há valor ou erro.
        return null;
    }
}
