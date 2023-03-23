package utilityclasses;

/**
 * Esta é a enumeração StatusConsumeEnum, que possui os estados possiveis de consumo
 */
public enum StatusConsumeEnum {

	HIGH("HIGH CONSUME"), NORMAL("NORMAL CONSUME");

	private String typeConsume;

	/**
	 * Esse é o construtor da enumeração StatusConsumeEnum, que constroe o objeto que
	 * representa os possiveis status de consumo
	 * 
	 * @param String typeConsume
	 */
	private StatusConsumeEnum(String typeConsume) {

		this.typeConsume = typeConsume;

	}

	/**
	 * Esse é o método, que um status de consumo
	 * 
	 * @return Status de consumo
	 */
	public String getTypeConsume() {
		return typeConsume;
	}

}
