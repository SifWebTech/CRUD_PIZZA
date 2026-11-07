package br.com.pizzaria.util;

/**
 * Prepara textos para serem exibidos com seguranca nas paginas JSP.
 * Impede que dados do banco sejam interpretados como HTML ou JavaScript.
 */
public class Html {

    public static String escapar(Object valor) {
        if (valor == null) {
            return "";
        }

        return valor.toString()
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
