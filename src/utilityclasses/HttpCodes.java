package utilityclasses;

public enum HttpCodes {

	HTTP_200("OK"), // Requisição processada com sucesso
	HTTP_201("Created"), // Uma nova instancia foi criada - POST OU PUT
	HTTP_202("Accepted"), // O recurso será atualizado/criado de forma assincrona PUT
	HTTP_204("No content"),// A requisição foi processada com sucesso e não há body na reposta
	HTTP_400("Bad Request"), // Servidor não consegue entender a requisição, pois existe algum parâmetro inválido ou falta dele
	HTTP_401("Unauthorized"), // Credenciais inválidas
	HTTP_403("Accepted"), // Credenciais válidas mas sem acesso ao recurso
	HTTP_404("No content");// O servidor não encontrou o recurso solicitado pelo cliente através da URL

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
