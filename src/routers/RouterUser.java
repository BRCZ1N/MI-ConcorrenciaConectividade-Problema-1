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
 * Esta é a classe RouterUsers, que serve para a organização e processamento do
 * roteamento das requisições relacionadas aos serviços de usuario do cliente no
 * servidor
 */
public class RouterUser implements RouterInterface {

	private Map<Pattern, MethodRouter> routers = new HashMap<>();
	private Map<HttpMethods, ArrayList<Pattern>> httpPatterns = new HashMap<>();
	private String idPattern = "(\\w+)";
	private String passwordPattern = "(\\w+)";

	/**
	 * Esse é o construtor da classe RouterUser que adiciona os padrões de rotas
	 * para serviços de usuario no servidor unido com os metodos necessários para
	 * cada requisição
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
	 * Esse é o método, que verifica se o caminho existe a partir dos padrões
	 * armazenados no sistema
	 * 
	 * @param String path Caminho que foi mandado na requisição
	 * @return Retorna verdadeiro se achar o caminho e falso se não achar
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
	 * Esse é o método, que verifica se o caminho existe a partir dos padrões
	 * armazenados no sistema para certo método http e retorna o padrão se existir
	 * 
	 * @param HttpMethods httpMethod Metodo que foi mandado pela requisição
	 * @param String      path Caminho que foi mandado na requisição
	 * @return Padrão da requisição caso exista
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
	 * Esse é o método, que executa o roteamento completo dos serviços de usuário e
	 * que retorna uma resposta http em formato de string para o cliente
	 * 
	 * @param ResquestHttp http Objeto que representa a requisição http do cliente
	 * @return Resposta da requisição http através de uma string
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
	 * Esse é o método, que busca e executa o método que deve ser executado pela
	 * requisição http, retornando por fim a resposta da requisição em formato
	 * string para ser enviada
	 * 
	 * @param ResquestHttp http Objeto que representa a requisição http do cliente
	 * @param Pattern      patternCurrent Padrão do caminho da requisição
	 * @return Resposta da requisição http através de uma string
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
	 * Esse é o método que executa ações utilizando dos serviços de usuario do
	 * servidor para pegar o estado de consumo atual de um cliente utilizando do
	 * conteudo da requisição http
	 * 
	 * @param ResquestHttp http Objeto que representa a requisição http do cliente
	 * @return Resposta da requisição http através de uma string
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
	 * Esse é o método que executa ações utilizando dos serviços de usuario do
	 * servidor para a autenticação de um cliente utilizando do conteudo da
	 * requisição http
	 * 
	 * @param ResquestHttp http Objeto que representa a requisição http do cliente
	 * @return Resposta da requisição http através de uma string
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
	 * Esta é a interface, para os metodos do que processam as requisições http da
	 * classe e retornam a resposta
	 */
	public interface MethodRouter {

		public String method(RequestHttp http);

	}

}
