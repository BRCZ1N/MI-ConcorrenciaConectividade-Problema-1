package routers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import utilityclasses.HttpMethods;
import utilityclasses.RequestHttp;

public class RouterConsumption implements RouterInterface {

	private Map<Pattern, MethodRouter> routers = new HashMap<>();
	private Map<HttpMethods, ArrayList<Pattern>> httpPatterns = new HashMap<>();
	private String idPattern = "(\\d+)";

	public RouterConsumption() {

		routers.put(Pattern.compile("/consumption/status/" + idPattern), this::getCurrentStateConsumption);
		routers.put(Pattern.compile("/consumption/historic/" + idPattern), this::getHistoricConsumption);

		ArrayList<Pattern> patterns = new ArrayList<>();

		patterns.add(Pattern.compile("/consumption/status/" + idPattern));
		patterns.add(Pattern.compile("/consumption/historic/" + idPattern));
		httpPatterns.put(HttpMethods.GET, patterns);

	}

	public boolean verifyPath(String path) {

		for (Entry<Pattern, MethodRouter> pattern : routers.entrySet()) {

			if (pattern.getKey().matcher(path).matches()) {

				return true;

			}

		}

		return false;

	}

	public Pattern verifyPathInHttpMethod(HttpMethods httpMethod, String path) {

		for (Pattern pattern : httpPatterns.get(httpMethod)) {

			if (pattern.matcher(path).matches()) {

				return pattern;

			}

		}

		return null;

	}

	public void execMethodRouter(RequestHttp http, Pattern pattern) {

		MethodRouter method = routers.get(pattern);
		method.method(http);

	}

	@Override
	public void router(RequestHttp http) {

		Pattern pattern;

		if (http.getMethod() == HttpMethods.GET) {

			if (verifyPath(http.getPath())) {

				if ((pattern = verifyPathInHttpMethod(HttpMethods.GET, http.getPath())) != null) {

					execMethodRouter(http, pattern);

				} else {

					// Erro

				}

			} else {

				// Erro

			}

		} else if (http.getMethod() == HttpMethods.POST) {

			if (verifyPath(http.getPath())) {

				if ((pattern = verifyPathInHttpMethod(HttpMethods.POST, http.getPath())) != null) {

					execMethodRouter(http, pattern);

				} else {

					// Erro

				}

			} else {

				// Erro

			}

		} else if (http.getMethod() == HttpMethods.PUT) {

			if (verifyPath(http.getPath())) {

				if ((pattern = verifyPathInHttpMethod(HttpMethods.PUT, http.getPath())) != null) {

					execMethodRouter(http, pattern);

				} else {

					// Erro

				}

			} else {

				// Erro

			}

		} else if (http.getMethod() == HttpMethods.DELETE) {

			if (verifyPath(http.getPath())) {

				if ((pattern = verifyPathInHttpMethod(HttpMethods.DELETE, http.getPath())) != null) {

					execMethodRouter(http, pattern);

				} else {

					// Erro

				}

			} else {

				// Erro

			}
			
		} else {

			// Não implementado

		}

	}

	public void getCurrentStateConsumption(RequestHttp http) {

	}

	public void getHistoricConsumption(RequestHttp http) {

	}

	public interface MethodRouter {

		public void method(RequestHttp http);

	}

}
