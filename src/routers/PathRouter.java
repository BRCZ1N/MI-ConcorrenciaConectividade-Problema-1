package routers;

import java.util.HashMap;
import java.util.Map;

import http.RequestHttp;
import http.ResponseHttp;
import utilityclasses.HttpCodes;

/**
 * Esta é a classe PathRouter, que armazena os roteamentos principais do projeto
 * para usuários, faturas e consumos.
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @version 1.0
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
	 * @param http - A requisição http do cliente
	 * @return Resposta da requisição http através de uma string
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
	 * Esse é o método, que retorna o endpoint principal do caminho da requisição
	 * 
	 * @param path - Caminho da requisição
	 * @return O caminho geral da requisição
	 */
	public String searchEndpoint(String path) {

		String[] pathArray = path.split("/");

		return "/" + pathArray[1];
	}

}
