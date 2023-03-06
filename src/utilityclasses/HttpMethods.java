package utilityclasses;

public enum HttpMethods {

	GET("GET"), PUT("PUT"), POST("POST"), DELETE("DELETE");

	private String method;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	private HttpMethods(String method) {

		this.method = method;

	}

}
