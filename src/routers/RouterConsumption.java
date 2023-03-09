package routers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import utilityclasses.HttpMethods;
import utilityclasses.RequestHttp;

public class RouterConsumption implements RouterInterface {

	private Map<Pattern, Handler> routers = new HashMap<>();
//	private String datePattern = "(\\d{2}-\\d{2}-\\d{4})";
	private Map<HttpMethods, ArrayList<Pattern>> methodPattern = new HashMap<>();
	private String idPattern = "(\\d+)";

	public RouterConsumption() {

		routers.put(Pattern.compile("/consumption/status/" + idPattern), this::getCurrentStateConsumption);
		routers.put(Pattern.compile("/consumption/historic/" + idPattern), this::getHistoricConsumption);

		ArrayList<Pattern> getPatterns = new ArrayList<>();

		getPatterns.add(Pattern.compile("/consumption/status/" + idPattern));
		getPatterns.add(Pattern.compile("/consumption/historic/" + idPattern));

		methodPattern.put(HttpMethods.GET, getPatterns);

	}

	public boolean matchPattern(String path) {

		for (Entry<Pattern, Handler> pattern : routers.entrySet()) {

			if (pattern.getKey().matcher(path).matches()) {

				return true;

			}

		}
		return false;

	}

	public boolean verifyMethodInPath(HttpMethods httpMethod, String path) {

		for (Pattern pattern : methodPattern.get(httpMethod)) {

			if (pattern.matcher(path).matches()) {

				return true;

			}

		}
		
		return false;

	}

	@Override
	public void router(RequestHttp http) {

		if (http.getMethod() == HttpMethods.GET) {

			if (matchPattern(http.getPath())) {
				
				if(verifyMethodInPath(HttpMethods.GET)) {
					
					
					
				}

			} else {

				// Caminho invalido

			}

		} else if (http.getMethod() == HttpMethods.POST) {
			
			//Não implementado

		} else if (http.getMethod() == HttpMethods.PUT) {
			
			//Não implementado

		} else if (http.getMethod() == HttpMethods.DELETE) {

			// Não implementado

		} else {

			// Não implementado

		}

	}

	public void getCurrentStateConsumption(RequestHttp http) {

	}

	public void getHistoricConsumption(RequestHttp http) {

	}

	public interface Handler {

		public void handler(RequestHttp http);

	}

}
