package utilityclasses;

public enum HttpCodes {

	HTTP_200("OK"), // Requisi��o processada com sucesso
	HTTP_201("Created"), // Uma nova instancia foi criada - POST OU PUT
	HTTP_202("Accepted"), // O recurso ser� atualizado/criado de forma assincrona PUT
	HTTP_204("No content"),// A requisi��o foi processada com sucesso e n�o h� body na reposta
	HTTP_400("Bad Request"), // Servidor n�o consegue entender a requisi��o, pois existe algum par�metro inv�lido ou falta dele
	HTTP_401("Unauthorized"), // Credenciais inv�lidas
	HTTP_403("Accepted"), // Credenciais v�lidas mas sem acesso ao recurso
	HTTP_404("No content");// O servidor n�o encontrou o recurso solicitado pelo cliente atrav�s da URL

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private HttpCodes(String code) {

		this.code = code;

	}

}
