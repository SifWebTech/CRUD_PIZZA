// Informa o pacote (a pasta lógica) desta classe: br.com.pizzaria.util.
package br.com.pizzaria.util;

/**
 * Prepara textos para serem exibidos com seguranca nas paginas JSP.
 * Impede que dados do banco sejam interpretados como HTML ou JavaScript.
 */
// Cria a classe Html, que trata textos antes de exibi-los na página.
public class Html {

    // Recebe um valor e devolve o texto seguro para colocar no HTML.
    public static String escapar(Object valor) {
        // Testa se não veio valor nenhum.
        if (valor == null) {
            // Devolve texto vazio, para a página não mostrar a palavra null.
            return "";
        }

        // Converte o valor para texto e começa as trocas abaixo.
        return valor.toString()
                // Troca o & primeiro, para não estragar as trocas seguintes.
                .replace("&", "&amp;")
                // Troca o sinal de menor, impedindo a abertura de tags.
                .replace("<", "&lt;")
                // Troca o sinal de maior.
                .replace(">", "&gt;")
                // Troca as aspas duplas, que quebrariam atributos como value.
                .replace("\"", "&quot;")
                // Troca as aspas simples e termina o comando.
                .replace("'", "&#39;");
    }
}
