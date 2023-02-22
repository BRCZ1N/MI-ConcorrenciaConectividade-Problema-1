package allclasses;

public enum StatusLoginEnum {

	OFF("DISCONNECTED"), ON("CONNECTED");

	private String status;

	private StatusLoginEnum(String status) {

		this.status = status;

	}

	public String getStatus() {

		return this.status;

	}

	@Override
	public String toString() {

		return this.status;
	}

}
