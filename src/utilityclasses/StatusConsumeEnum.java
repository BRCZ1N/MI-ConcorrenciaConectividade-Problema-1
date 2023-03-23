package utilityclasses;

/**
 * Esta � a enumera��o StatusConsumeEnum, que possui os estados possiveis de consumo
 */
public enum StatusConsumeEnum {

	HIGH("HIGH CONSUME"), NORMAL("NORMAL CONSUME");

	private String typeConsume;

	/**
	 * Esse � o construtor da enumera��o StatusConsumeEnum, que constroe o objeto que
	 * representa os possiveis status de consumo
	 * 
	 * @param String typeConsume
	 */
	private StatusConsumeEnum(String typeConsume) {

		this.typeConsume = typeConsume;

	}

	/**
	 * Esse � o m�todo, que um status de consumo
	 * 
	 * @return Status de consumo
	 */
	public String getTypeConsume() {
		return typeConsume;
	}

}
