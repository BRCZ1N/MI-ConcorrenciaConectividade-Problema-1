package routers;

import java.util.HashMap;
import java.util.Map;

import http.RequestHttp;
import http.ResponseHttp;
import utilityclasses.HttpCodes;

/**
 * Esta � a classe PathRouter, que armazena os roteamentos principais do projeto
 * para usu�rios, faturas e consumos.
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @version 1.0
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
	 * @param http - A requisi��o http do cliente
	 * @return Resposta da requisi��o http atrav�s de uma string
	 */
	public String execRoute(RequestHttp http) {

		String endpointService = searchEndpoint(http.getPath());
		String responseHttp = null;

		if (routes.containsKey(endpointService)) {

			responseHttp = routes.get(endpointService).router(http);

		} else {

			Map<String, String> mapHeaders = new HashMap<>();
			mapHeaders.put("Content-Length", "0");
			responseHttp = new ResponseHttp(HttpCodes.HTTP_404.getCodeHttp(), mapHeaders).toString();

		}

		return responseHttp;

	}

	/**
	 * Esse � o m�todo, que retorna o endpoint principal do caminho da requisi��o
	 * 
	 * @param path - Caminho da requisi��o
	 * @return O caminho geral da requisi��o
	 */
	public String searchEndpoint(String path) {

		String[] pathArray = path.split("/");

		return "/" + pathArray[1];
	}

}
