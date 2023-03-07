package utilityclasses;

public enum HttpCodes {

	HTTP_200("HTTP/1.1 200 OK\r\n"), 
	HTTP_201("HTTP/1.1 201 Created\r\n"), 
	HTTP_204("HTTP/1.1 204 No Content\r\n"),
	HTTP_400("HTTP/1.1 400 Bad Request\r\n"),
	HTTP_401("HTTP/1.1 401 Unauthorized\r\n"),
	HTTP_403("HTTP/1.1 403 Forbidden\r\n"), 
	HTTP_404("HTTP/1.1 404 Not Found\r\n"),
	HTTP_500("HTTP/1.1 500 Internal Server Error\r\n");

//	200 OK: a solicitação foi bem-sucedida e o servidor retornou a resposta solicitada.
//	201 Created: a solicitação foi bem-sucedida e o servidor criou um novo recurso como resultado.
//	204 No Content: a solicitação foi bem-sucedida, mas não há conteúdo a ser retornado.
//	400 Bad Request: a solicitação não pode ser processada devido a uma sintaxe inválida ou outras informações incorretas na solicitação.
//	401 Unauthorized: a solicitação não foi autorizada devido a credenciais inválidas ou ausentes.
//	403 Forbidden: a solicitação foi entendida, mas o servidor se recusa a fornecer o recurso.
//	404 Not Found: o recurso solicitado não foi encontrado no servidor.
//	500 Internal Server Error: ocorreu um erro interno no servidor que impediu a conclusão da solicitação.

	private String versionHttp;

	private HttpCodes(String versionHttp) {

		this.versionHttp = versionHttp;

	}

	public String getVersionHttp() {
		return versionHttp;
	}

	public void setVersionHttp(String versionHttp) {
		this.versionHttp = versionHttp;
	}

}
