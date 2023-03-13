package routers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import services.ConsumptionServices;
import utilityclasses.HttpCodes;
import utilityclasses.HttpMethods;
import utilityclasses.RequestHttp;
import utilityclasses.ResponseHttp;

public class RouterConsumption implements RouterInterface {

	private Map<Pattern, MethodRouter> routers = new HashMap<>();
	private Map<HttpMethods, ArrayList<Pattern>> httpPatterns = new HashMap<>();
	private String idPattern = "(\\d+)";

	public RouterConsumption() {

//		routers.put(Pattern.compile("/consumption/status/" + idPattern), this::getCurrentStateConsumption);
		routers.put(Pattern.compile("/consumption/historic/" + idPattern), this::getHistoricConsumption);

		ArrayList<Pattern> patterns = new ArrayList<>();

//		patterns.add(Pattern.compile("/consumption/status/" + idPattern));
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

	public String execMethodRouter(RequestHttp http, Pattern patternCurrent) {

		MethodRouter methodReq = null;

		for (Entry<Pattern, MethodRouter> methodRouter : routers.entrySet()) {

			if (patternCurrent.pattern().equals(methodRouter.getKey().pattern())) {

				methodReq = methodRouter.getValue();

			}

		}

		return methodReq.method(http);

	}

	@Override
	public String router(RequestHttp http) {

		Pattern pattern;
		String responseHttp = null;

		if (http.getMethod() == HttpMethods.GET) {

			if (verifyPath(http.getPath())) {

				if ((pattern = verifyPathInHttpMethod(HttpMethods.GET, http.getPath())) != null) {

					responseHttp = execMethodRouter(http, pattern);

				}
//				} else {
//
//					// Erro
//
//				}

			} else {

				Map<String, String> mapHeaders = new HashMap<>();
				mapHeaders.put("Content-Length", "0");
				responseHttp = new ResponseHttp(HttpCodes.HTTP_404.getCodeHttp(), mapHeaders).toString();

			}

//		} else if (http.getMethod() == HttpMethods.POST) {
//
//			if (verifyPath(http.getPath())) {
//
//				if ((pattern = verifyPathInHttpMethod(HttpMethods.POST, http.getPath())) != null) {
//
//					responseHttp = execMethodRouter(http, pattern);
//
//				} else {
//
//					// Erro
//
//				}
//
//			} else {
//
//				// Erro
//
//			}
//
//		} else if (http.getMethod() == HttpMethods.PUT) {
//
//			if (verifyPath(http.getPath())) {
//
//				if ((pattern = verifyPathInHttpMethod(HttpMethods.PUT, http.getPath())) != null) {
//
//					responseHttp = execMethodRouter(http, pattern);
//
//				} else {
//
//					// Erro
//
//				}
//
//			} else {
//
//				// Erro
//
//			}
//
//		} else if (http.getMethod() == HttpMethods.DELETE) {
//
//			if (verifyPath(http.getPath())) {
//
//				if ((pattern = verifyPathInHttpMethod(HttpMethods.DELETE, http.getPath())) != null) {
//
//					responseHttp = execMethodRouter(http, pattern);
//
//				} else {
//
//					// Erro
//
//				}
//
//			} else {
//
//				// Erro
//
//			}

		} else {

			Map<String, String> mapHeaders = new HashMap<>();
			mapHeaders.put("Content-Length", "0");
			responseHttp = new ResponseHttp(HttpCodes.HTTP_501.getCodeHttp(), mapHeaders).toString();

		}

		return responseHttp;

	}

//	public String getCurrentStateConsumption(RequestHttp http) {
//
//		ResponseHttp response = null;
//		String[] idClient = http.getPath().split("/");
//		String jsonRespString = ConsumptionServices.getConsumptionsJSON(idClient[2]).toString();
//		Map<String, String> mapHeaders = new HashMap<>();
//
//		if (jsonRespString == null) {
//
//			mapHeaders.put("Content-Length", "0");
//			response = new ResponseHttp(HttpCodes.HTTP_404.toString(), mapHeaders);
//
//		} else {
//
//			mapHeaders.put("Content-Type", "application/json");
//			mapHeaders.put("Content-Length", Integer.toString(jsonRespString.getBytes().length));
//			response = new ResponseHttp(HttpCodes.HTTP_200.toString(), mapHeaders, jsonRespString);
//
//		}
//
//		return response.toString();
//
//	}

	public String getHistoricConsumption(RequestHttp http) {

		Pattern pattern = Pattern.compile("/consumption/historic/" + idPattern);
		Matcher matcher = pattern.matcher(http.getPath());
		matcher.matches();
		ResponseHttp response;
		JSONObject jsonRespString = ConsumptionServices.getConsumptionsJSON(matcher.group(1));
		Map<String, String> mapHeaders = new HashMap<>();

		if (jsonRespString == null) {

			mapHeaders.put("Content-Length", "0");
			response = new ResponseHttp(HttpCodes.HTTP_404.getCodeHttp(), mapHeaders);

		} else {

			mapHeaders.put("Content-Type", "application/json");
			mapHeaders.put("Content-Length", Integer.toString(jsonRespString.toString().getBytes().length));
			response = new ResponseHttp(HttpCodes.HTTP_200.getCodeHttp(), mapHeaders, jsonRespString.toString());

		}

		return response.toString();

	}

	public interface MethodRouter {

		public String method(RequestHttp http);

	}

}
