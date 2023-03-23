package routers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import http.RequestHttp;
import http.ResponseHttp;
import services.UserServices;
import utilityclasses.HttpCodes;
import utilityclasses.HttpMethods;

/**
 * Esta � a classe RouterUsers, que serve para a organiza��o e processamento do
 * roteamento das requisi��es relacionadas aos servi�os de usuario do cliente no
 * servidor
 */
public class RouterUser implements RouterInterface {

	private Map<Pattern, MethodRouter> routers = new HashMap<>();
	private Map<HttpMethods, ArrayList<Pattern>> httpPatterns = new HashMap<>();
	private String idPattern = "(\\w+)";
	private String passwordPattern = "(\\w+)";

	/**
	 * Esse � o construtor da classe RouterUser que adiciona os padr�es de rotas
	 * para servi�os de usuario no servidor unido com os metodos necess�rios para
	 * cada requisi��o
	 */
	public RouterUser() {

		routers.put(Pattern.compile("/user/statusConsumption/" + idPattern), this::getCurrentStateConsumption);
		routers.put(Pattern.compile("/user/auth/id:" + idPattern + "&password:" + passwordPattern), this::authClient);

		ArrayList<Pattern> patterns = new ArrayList<>();

		patterns.add(Pattern.compile("/user/statusConsumption/" + idPattern));
		patterns.add(Pattern.compile("/user/auth/id:" + idPattern + "&password:" + passwordPattern));
		httpPatterns.put(HttpMethods.GET, patterns);

	}

	/**
	 * Esse � o m�todo, que verifica se o caminho existe a partir dos padr�es
	 * armazenados no sistema
	 * 
	 * @param String path Caminho que foi mandado na requisi��o
	 * @return Retorna verdadeiro se achar o caminho e falso se n�o achar
	 */
	public boolean verifyPath(String path) {

		for (Entry<Pattern, MethodRouter> pattern : routers.entrySet()) {

			if (pattern.getKey().matcher(path).matches()) {

				return true;

			}

		}

		return false;

	}

	/**
	 * Esse � o m�todo, que verifica se o caminho existe a partir dos padr�es
	 * armazenados no sistema para certo m�todo http e retorna o padr�o se existir
	 * 
	 * @param HttpMethods httpMethod Metodo que foi mandado pela requisi��o
	 * @param String      path Caminho que foi mandado na requisi��o
	 * @return Padr�o da requisi��o caso exista
	 */
	public Pattern verifyPathInHttpMethod(HttpMethods httpMethod, String path) {

		for (Pattern pattern : httpPatterns.get(httpMethod)) {

			if (pattern.matcher(path).matches()) {

				return pattern;

			}

		}

		return null;

	}

	/**
	 * Esse � o m�todo, que executa o roteamento completo dos servi�os de usu�rio e
	 * que retorna uma resposta http em formato de string para o cliente
	 * 
	 * @param ResquestHttp http Objeto que representa a requisi��o http do cliente
	 * @return Resposta da requisi��o http atrav�s de uma string
	 */
	@Override
	public String router(RequestHttp http) {

		Pattern pattern;
		String responseHttp = null;

		if (http.getMethod() == HttpMethods.GET) {

			if (verifyPath(http.getPath())) {

				if ((pattern = verifyPathInHttpMethod(HttpMethods.GET, http.getPath())) != null) {

					responseHttp = execMethodRouter(http, pattern);

				} else {

					Map<String, String> mapHeaders = new HashMap<>();
					mapHeaders.put("Content-Length", "0");
					responseHttp = new ResponseHttp(HttpCodes.HTTP_405.getCodeHttp(), mapHeaders).toString();

				}

			} else {

				Map<String, String> mapHeaders = new HashMap<>();
				mapHeaders.put("Content-Length", "0");
				responseHttp = new ResponseHttp(HttpCodes.HTTP_400.getCodeHttp(), mapHeaders).toString();

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

	/**
	 * Esse � o m�todo, que busca e executa o m�todo que deve ser executado pela
	 * requisi��o http, retornando por fim a resposta da requisi��o em formato
	 * string para ser enviada
	 * 
	 * @param ResquestHttp http Objeto que representa a requisi��o http do cliente
	 * @param Pattern      patternCurrent Padr�o do caminho da requisi��o
	 * @return Resposta da requisi��o http atrav�s de uma string
	 */
	public String execMethodRouter(RequestHttp http, Pattern patternCurrent) {

		MethodRouter methodReq = null;

		for (Entry<Pattern, MethodRouter> methodRouter : routers.entrySet()) {

			if (patternCurrent.pattern().equals(methodRouter.getKey().pattern())) {

				methodReq = methodRouter.getValue();

			}

		}

		return methodReq.method(http);

	}

	/**
	 * Esse � o m�todo que executa a��es utilizando dos servi�os de usuario do
	 * servidor para pegar o estado de consumo atual de um cliente utilizando do
	 * conteudo da requisi��o http
	 * 
	 * @param ResquestHttp http Objeto que representa a requisi��o http do cliente
	 * @return Resposta da requisi��o http atrav�s de uma string
	 */
	public String getCurrentStateConsumption(RequestHttp http) {

		Pattern pattern = Pattern.compile("/user/statusConsumption/" + idPattern);
		Matcher matcher = pattern.matcher(http.getPath());
		matcher.matches();
		ResponseHttp response = null;
		String jsonRespString = UserServices.getStatusConsumptionJSON(matcher.group(1)).toString();
		Map<String, String> mapHeaders = new HashMap<>();

		if (jsonRespString == null) {

			mapHeaders.put("Content-Length", "0");
			response = new ResponseHttp(HttpCodes.HTTP_404.getCodeHttp(), mapHeaders);

		} else {

			mapHeaders.put("Content-Type", "application/json");
			mapHeaders.put("Content-Length", Integer.toString(jsonRespString.getBytes().length));
			response = new ResponseHttp(HttpCodes.HTTP_200.getCodeHttp(), mapHeaders, jsonRespString);

		}

		return response.toString();

	}

	/**
	 * Esse � o m�todo que executa a��es utilizando dos servi�os de usuario do
	 * servidor para a autentica��o de um cliente utilizando do conteudo da
	 * requisi��o http
	 * 
	 * @param ResquestHttp http Objeto que representa a requisi��o http do cliente
	 * @return Resposta da requisi��o http atrav�s de uma string
	 */
	public String authClient(RequestHttp http) {

		Pattern pattern = Pattern.compile("/user/auth/id:" + idPattern + "&password:" + passwordPattern);
		Matcher matcher = pattern.matcher(http.getPath());
		matcher.matches();
		ResponseHttp response = null;
		boolean authResp = UserServices.authClient(matcher.group(1), matcher.group(2));
		Map<String, String> mapHeaders = new HashMap<>();
		mapHeaders.put("Content-Length", "0");

		if (authResp) {

			response = new ResponseHttp(HttpCodes.HTTP_200.getCodeHttp(), mapHeaders);

		} else {

			response = new ResponseHttp(HttpCodes.HTTP_404.getCodeHttp(), mapHeaders);

		}

		return response.toString();

	}

	/**
	 * Esta � a interface, para os metodos do que processam as requisi��es http da
	 * classe e retornam a resposta
	 */
	public interface MethodRouter {

		public String method(RequestHttp http);

	}

}
