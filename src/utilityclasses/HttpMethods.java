package utilityclasses;

/**
 * Esta é a enumeração HttpCodes, que possui os metodos das requisições http
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @version 1.0
 */
public enum HttpMethods {

	GET("GET"), PUT("PUT"), POST("POST"), DELETE("DELETE");

	private String method;

	/**
	 * Esse é o construtor da enumeração HttpMethods, que constroe o objeto que
	 * representa os possiveis metodos http
	 * 
	 * @param String method
	 */
	private HttpMethods(String method) {

		this.method = method;

	}

	/**
	 * Esse é o método, que retorna código do metodo http
	 * 
	 * @return Metodo http
	 */
	public String getMethod() {
		return method;
	}

}
