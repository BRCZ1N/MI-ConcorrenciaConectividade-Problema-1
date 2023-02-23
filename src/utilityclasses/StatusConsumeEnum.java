package utilityclasses;

public enum StatusConsumeEnum {

	HIGH("HIGH CONSUME"), NORMAL("NORMAL CONSUME");

	private String typeConsume;

	private StatusConsumeEnum(String typeConsume) {

		this.typeConsume = typeConsume;

	}

	public String getTypeConsume() {
		return typeConsume;
	}

	@Override
	public String toString() {

		return this.typeConsume;

	}

}
