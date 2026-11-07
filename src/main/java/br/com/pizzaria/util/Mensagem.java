// Informa o pacote (a pasta lógica) desta classe: br.com.pizzaria.util.
package br.com.pizzaria.util;

// Traz a classe que representa o pedido enviado pelo navegador.
import jakarta.servlet.http.HttpServletRequest;
// Traz a classe da sessão, a memória do servidor para cada visitante.
import jakarta.servlet.http.HttpSession;

/**
 * Guarda uma mensagem na sessao para ela sobreviver a um redirecionamento.
 * A mensagem e exibida uma unica vez, na proxima pagina carregada.
 */
// Cria a classe Mensagem, que controla os avisos mostrados ao usuário.
public class Mensagem {

    // Constante com o nome usado para mensagens de sucesso.
    public static final String SUCESSO = "sucesso";
    // Constante com o nome usado para mensagens de erro.
    public static final String ERRO = "erro";

    // Método que guarda uma mensagem para a próxima página.
    public static void guardar(HttpServletRequest request, String tipo, String texto) {
        // Pega a sessão do visitante e grava a mensagem nela.
        request.getSession().setAttribute(tipo, texto);
    }

    /**
     * Move as mensagens da sessao para a requisicao atual.
     */
    // Método que entrega à página atual as mensagens guardadas.
    public static void carregar(HttpServletRequest request) {
        // Pega a sessão existente; false impede criar uma nova.
        HttpSession sessao = request.getSession(false);
        // Testa se o visitante ainda não tem sessão.
        if (sessao == null) {
            // Encerra o método aqui.
            return;
        }

        // Repete o trecho abaixo para os dois tipos de mensagem.
        for (String tipo : new String[]{SUCESSO, ERRO}) {
            // Lê a mensagem guardada na sessão.
            Object texto = sessao.getAttribute(tipo);
            // Testa se existe mensagem desse tipo.
            if (texto != null) {
                // Passa a mensagem para a requisição atual, de onde a JSP lê.
                request.setAttribute(tipo, texto);
                // Apaga da sessão, para a mensagem aparecer uma única vez.
                sessao.removeAttribute(tipo);
            }
        }
    }
}
