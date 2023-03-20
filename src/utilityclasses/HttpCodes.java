package utilityclasses;

public enum HttpCodes {

	HTTP_200("HTTP/1.1 200 OK"), 
	HTTP_201("HTTP/1.1 201 Created"), 
	HTTP_204("HTTP/1.1 204 No Content"),
	HTTP_400("HTTP/1.1 400 Bad Request"),
	HTTP_401("HTTP/1.1 401 Unauthorized"),
	HTTP_403("HTTP/1.1 403 Forbidden"), 
	HTTP_404("HTTP/1.1 404 Not Found"),
	HTTP_405("HTTP/1.1 405 Method Not Allowed"),
	HTTP_500("HTTP/1.1 500 Internal Server Error"),
	HTTP_501("HTTP/1.1 501 Not Implemented");

//	200 OK: a solicita��o foi bem-sucedida e o servidor retornou a resposta solicitada.
//	201 Created: a solicita��o foi bem-sucedida e o servidor criou um novo recurso como resultado.
//	204 No Content: a solicita��o foi bem-sucedida, mas n�o h� conte�do a ser retornado.
//	400 Bad Request: a solicita��o n�o pode ser processada devido a uma sintaxe inv�lida ou outras informa��es incorretas na solicita��o.
//	401 Unauthorized: a solicita��o n�o foi autorizada devido a credenciais inv�lidas ou ausentes.
//	403 Forbidden: a solicita��o foi entendida, mas o servidor se recusa a fornecer o recurso.
//	404 Not Found: o recurso solicitado n�o foi encontrado no servidor.
//	500 Internal Server Error: ocorreu um erro interno no servidor que impediu a conclus�o da solicita��o.

	private String codeHttp;

	private HttpCodes(String codeHttp) {

		this.codeHttp = codeHttp;

	}

	public String getCodeHttp() {
		return codeHttp;
	}

	public void setCodeHttp(String codeHttp) {
		this.codeHttp = codeHttp;
	}

}
