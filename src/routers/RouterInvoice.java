package routers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import utilityclasses.HttpMethods;
import utilityclasses.RequestHttp;

public class RouterInvoice implements RouterInterface {

	private Map<Pattern, MethodRouter> routers = new HashMap<>();
	private Map<HttpMethods, ArrayList<Pattern>> httpPatterns = new HashMap<>();
	private String datePattern = "(\\d{2}-\\d{2}-\\d{4})";
	private String idPattern = "(\\d+)";

	public RouterInvoice() {

		routers.put(Pattern.compile("/invoice/" + idPattern), this::getInvoice);
		routers.put(Pattern.compile("/invoice/all"), this::getAllInvoices);
		routers.put(Pattern.compile("/invoice/newInvoice/" + idPattern + "/data?inicio=" + datePattern + "&fim=" + datePattern),
				this::createInvoice);

		ArrayList<Pattern> patterns = new ArrayList<>();

		patterns.add(Pattern.compile("/invoice/" + idPattern));
		patterns.add(Pattern.compile("/invoice/all"));
		patterns.add(Pattern.compile("/invoice/newInvoice/" + idPattern + "/data?inicio=" + datePattern + "&fim=" + datePattern));
		httpPatterns.put(HttpMethods.GET, patterns);
		patterns.clear();

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

	public void getInvoice(RequestHttp http) {

	}

	public void getAllInvoices(RequestHttp http) {

	}

	public void createInvoice(RequestHttp http) {

	}

	public interface MethodRouter {

		public void method(RequestHttp http);

	}

}
