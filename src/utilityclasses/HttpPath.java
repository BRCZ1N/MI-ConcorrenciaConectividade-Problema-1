package utilityclasses;

public enum HttpPath {

	PATH_INVOICE("/client/invoice"), PATH_HISTORIC("/client/historic");

	private String path;

	private HttpPath(String path) {

		this.path = path;

	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
