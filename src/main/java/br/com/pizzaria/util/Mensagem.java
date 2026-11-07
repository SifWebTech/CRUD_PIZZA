package br.com.pizzaria.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Guarda uma mensagem na sessao para ela sobreviver a um redirecionamento.
 * A mensagem e exibida uma unica vez, na proxima pagina carregada.
 */
public class Mensagem {

    public static final String SUCESSO = "sucesso";
    public static final String ERRO = "erro";

    public static void guardar(HttpServletRequest request, String tipo, String texto) {
        request.getSession().setAttribute(tipo, texto);
    }

    /**
     * Move as mensagens da sessao para a requisicao atual.
     */
    public static void carregar(HttpServletRequest request) {
        HttpSession sessao = request.getSession(false);
        if (sessao == null) {
            return;
        }

        for (String tipo : new String[]{SUCESSO, ERRO}) {
            Object texto = sessao.getAttribute(tipo);
            if (texto != null) {
                request.setAttribute(tipo, texto);
                sessao.removeAttribute(tipo);
            }
        }
    }
}
