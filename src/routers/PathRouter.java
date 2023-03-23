package routers;

import java.util.HashMap;
import java.util.Map;

import http.RequestHttp;

/**
 * Esta é a classe PathRouter, que armazena os roteamentos principais do projeto
 * para usuários, faturas e consumos.
 */
public class PathRouter {

	private Map<String, RouterInterface> routes = new HashMap<>();

	/**
	 * Esse é o construtor da classe PathRouter, que armazena no map as instancias
	 * de cada caminho geral possível do servidor, ou seja, as instancias para as
	 * classes dos caminhos de fatura, usuários e consumos.
	 */
	public PathRouter() {

		routes.put("/invoice", new RouterInvoice());
		routes.put("/consumption", new RouterConsumption());
		routes.put("/user", new RouterUser());

	}

	/**
	 * Esse é o método, de roteamento principal da classe geral de roteamento que
	 * recebe o retorno da resposta gerada pelos roteamentos especificos de usuario,
	 * faturas e consumo.
	 * 
	 * @param HttpMethods method O método da requisição http
	 * @return Resposta da requisição http através de uma string
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
	 * Esse é o método, que retorna o endpoint principal do caminho da requisição
	 * 
	 * @param String path Caminho da requisição
	 * @return O caminho geral da requisição
	 */
	public String searchEndpoint(String path) {

		String[] pathArray = path.split("/");

		return "/" + pathArray[1];
	}

}
