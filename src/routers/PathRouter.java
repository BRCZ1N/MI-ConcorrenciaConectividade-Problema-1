package routers;

import java.util.HashMap;
import java.util.Map;

import http.RequestHttp;

/**
 * Esta � a classe PathRouter, que armazena os roteamentos principais do projeto
 * para usu�rios, faturas e consumos.
 */
public class PathRouter {

	private Map<String, RouterInterface> routes = new HashMap<>();

	/**
	 * Esse � o construtor da classe PathRouter, que armazena no map as instancias
	 * de cada caminho geral poss�vel do servidor, ou seja, as instancias para as
	 * classes dos caminhos de fatura, usu�rios e consumos.
	 */
	public PathRouter() {

		routes.put("/invoice", new RouterInvoice());
		routes.put("/consumption", new RouterConsumption());
		routes.put("/user", new RouterUser());

	}

	/**
	 * Esse � o m�todo, de roteamento principal da classe geral de roteamento que
	 * recebe o retorno da resposta gerada pelos roteamentos especificos de usuario,
	 * faturas e consumo.
	 * 
	 * @param HttpMethods method O m�todo da requisi��o http
	 * @return Resposta da requisi��o http atrav�s de uma string
	 */
	public String execRoute(RequestHttp http) {

		String endpointService = searchEndpoint(http.getPath());
		String responseHttp = null;

		if (routes.containsKey(endpointService)) {

			responseHttp = routes.get(endpointService).router(http);

		} else {

			// Falta implementar o erro

		}

		return responseHttp;

	}

	/**
	 * Esse � o m�todo, que retorna o endpoint principal do caminho da requisi��o
	 * 
	 * @param String path Caminho da requisi��o
	 * @return O caminho geral da requisi��o
	 */
	public String searchEndpoint(String path) {

		String[] pathArray = path.split("/");

		return "/" + pathArray[1];
	}

}
