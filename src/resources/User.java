package resources;

import utilityclasses.StatusConsumeEnum;

/**
 * Esta � a classe User, que � a representa��o dos dados mais gerais de cliente
 * no servidor
 */
public class User {

	private String id;
	private String name;
	private String password;
	private StatusConsumeEnum statusConsumption;

	/**
	 * Esse � o construtor da classe User, que constroe o objeto que representa
	 * dados b�sicos do cliente
	 * 
	 * @param double name - Nome do cliente
	 * @param String password - Senha do cliente
	 * @param StatusConsumeEnum statusConsumption - Status de consumo do cliente
	 */
	public User(String name, String password, StatusConsumeEnum statusConsumption) {

		this.name = name;
		this.password = password;
		this.statusConsumption = statusConsumption;
	}

	/**
	 * Esse � o m�todo, que retorna o m�todo da requisi��o http
	 * 
	 * @return Id do cliente
	 */
	public String getId() {
		return id;
	}

	/**
	 * Esse � o m�todo, que seta o id do cliente
	 * 
	 * @param String id - Identificador do cliente
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Esse � o m�todo, que retorna o nome do cliente
	 * 
	 * @return Nome do cliente
	 */
	public String getName() {
		return name;
	}

	/**
	 * Esse � o m�todo, que seta o nome do cliente
	 * 
	 * @param String name - Nome do cliente
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Esse � o m�todo, que retorna a senha do cliente
	 * 
	 * @return Senha do cliente
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Esse � o m�todo, que seta a senha do cliente
	 * 
	 * @param String password - Senha do cliente
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Esse � o m�todo, que retorna o status de consumo do cliente
	 * 
	 * @return Status de consumo do cliente
	 */
	public StatusConsumeEnum getStatusConsumption() {
		return statusConsumption;
	}

	/**
	 * Esse � o m�todo, que seta o status de consumo do cliente
	 * 
	 * @param StatusConsumeEnum statusConsumption - Status de consumo do cliente
	 */
	public void setStatusConsumption(StatusConsumeEnum statusConsumption) {
		this.statusConsumption = statusConsumption;
	}

}
