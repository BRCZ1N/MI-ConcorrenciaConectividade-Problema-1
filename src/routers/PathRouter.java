package routers;

import java.util.HashMap;
import java.util.Map;
import utilityclasses.RequestHttp;

public class PathRouter {

	private Map<String, RouterInterface> routes = new HashMap<>();

	public PathRouter() {

		routes.put("/invoice", new RouterInvoice());
		routes.put("/consumption", new RouterConsumption());
		routes.put("/user", new RouterConsumption());

	}

	public void execRoute(RequestHttp http) {

		String endpointService = searchEndpoint(http.getPath());

		if (routes.containsKey(endpointService)) {

			routes.get(endpointService).router(http);

		} else {

			// Falta implementar o erro

		}

	}

	public String searchEndpoint(String path) {

		String[] pathArray = path.split("\"");

		return pathArray[0];
	}

}
