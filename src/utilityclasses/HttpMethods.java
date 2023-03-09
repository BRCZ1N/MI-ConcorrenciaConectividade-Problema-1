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

	public HttpMethods stringToHttpMethod (String method) {

		if (HttpMethods.GET.getMethod().equals(method)) {

			return HttpMethods.GET;

		} else if (HttpMethods.POST.getMethod().equals(method)) {

			return HttpMethods.POST;

		} else if (HttpMethods.PUT.getMethod().equals(method)) {

			return HttpMethods.PUT;

		} else {

			return HttpMethods.DELETE;

		}

	}

}
