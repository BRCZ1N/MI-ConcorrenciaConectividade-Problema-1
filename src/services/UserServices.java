package services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import resources.User;
import utilityclasses.StatusConsumeEnum;

/**
 * Esta � a classe ConsumptionServices, que representa os servi�os de usuarios
 * da aplica��o.
 */
public class UserServices {

	private static Map<String, User> mapUsers;
	private static long id = 0;

	/**
	 * Esse � o construtor da classe UserServices, que atribui ao map que dever�
	 * possuir os clientes do sistema uma instancia.
	 */
	public UserServices() {

		mapUsers = new HashMap<String, User>();

	}

	/**
	 * Esse � o m�todo que adiciona usu�rios para efeito de testes.
	 */
	public static void generateUsersTest() {

		UserServices.addUser(new User("Usuario1", "Test1", StatusConsumeEnum.NORMAL));// 0 - ID
		UserServices.addUser(new User("Usuario2", "Test2", StatusConsumeEnum.NORMAL));// 1 - ID
		UserServices.addUser(new User("Usuario3", "Test3", StatusConsumeEnum.NORMAL));// 2 - ID
		UserServices.addUser(new User("Usuario4", "Test4", StatusConsumeEnum.NORMAL));// 3 - ID
		UserServices.addUser(new User("Usuario5", "Test5", StatusConsumeEnum.NORMAL));// 4 - ID
		UserServices.addUser(new User("Usuario6", "Test6", StatusConsumeEnum.NORMAL));// 5 - ID
		UserServices.addUser(new User("Usuario7", "Test7", StatusConsumeEnum.NORMAL));// 6 - ID

	}

	/**
	 * Esse � o m�todo, que adiciona um cliente ao map de clientes e que chama os
	 * metodos para adicionar chaves de clientes no map dos outros servi�os.
	 * 
	 * @param User user - Usuario a ser cadastrado
	 */
	public static void addUser(User user) {

		user.setId(Long.toString(id));
		mapUsers.put(Long.toString(id), user);
		id += 1;
		ConsumptionServices.addSlotClientConsumptions(user.getId());
		InvoiceServices.addSlotClientInvoices(user.getId());

	}

	/**
	 * Esse � o m�todo, que deleta um cliente do map de clientes e que chama os
	 * metodos para deletar dos outros servi�os.
	 * 
	 * @param String idClient - Identificador do cliente
	 */
	public static void deleteUser(String idClient) {

		if (getUser(idClient) != null) {

			mapUsers.remove(idClient);
			ConsumptionServices.deleteUserConsumption(idClient);
			InvoiceServices.deleteUserInvoices(idClient);

		}

	}

	/**
	 * Esse � o m�todo, retorna um usu�rio caso ele exista.
	 * 
	 * @param String idClient - Identificador do cliente
	 * @return Retorna o objeto usu�rio
	 */
	public static User getUser(String idClient) {

		for (Entry<String, User> user : mapUsers.entrySet()) {

			if (user.getKey().equals(id)) {

				return user.getValue();

			}

		}

		return null;

	}

//	private static User getUserPerName(String nameClient) {
//
//		for (Entry<String, User> user : mapUsers.entrySet()) {
//
//			if (user.getValue().getName().equals(nameClient)) {
//
//				return user.getValue();
//
//			}
//
//		}
//
//		return null;
//
//	}

	/**
	 * Esse � o m�todo, que faz a autentica��o de um usuario atrav�s do id e senha.
	 * 
	 * @param String idClient - Identificador do cliente
	 * @param String password - Senha do cliente
	 * @return Retorna um array de bytes contendo a mensagem de autentica��o
	 */
	public static byte[] authenticateClient(String idClient, String password) {

		for (Map.Entry<String, User> user : mapUsers.entrySet()) {

			if (user.getValue().getId().trim().equals(idClient.trim())
					&& user.getValue().getPassword().trim().equals(password.trim())) {

				return "authenticate".getBytes();

			}

		}

		return "denied authenticate".getBytes();

	}

	/**
	 * Esse � o m�todo, que faz a autentica��o de um usuario atrav�s do id e de sua
	 * senha.
	 * 
	 * @param User user - Usuario editado
	 */
	public static void editUser(User user) {

		if (getUser(user.getId()) == null) {

			mapUsers.replace(user.getId(), user);

		}

	}

	/**
	 * Esse � o m�todo, que retorna o status de consumo do cliente
	 * 
	 * @param String idClient - Identificador do cliente
	 * @return Retorna o status de consumo do cliente em formato JSON
	 */
	public static JSONObject getStatusConsumptionJSON(String idClient) {

		JSONObject json = new JSONObject();

		if (containsClient(idClient)) {

			json.put("idClient", idClient);
			json.put("statusConsumption", mapUsers.get(idClient).getStatusConsumption().getTypeConsume());

			return json;

		}

		return null;

	}

	/**
	 * Esse � o m�todo, que atualiza o status de consumo cliente
	 * 
	 * @param String idClient - Identificador do cliente
	 * @param double currentConsumption - Consumo atual do cliente
	 */
	public static void refreshStatusConsumption(String idClient, double currentConsumption) {

		User user = getUser(idClient);

		if (currentConsumption > ConsumptionServices.averageConsumptions(idClient) + 200) {

			user.setStatusConsumption(StatusConsumeEnum.HIGH);
			editUser(user);

		} else {

			user.setStatusConsumption(StatusConsumeEnum.NORMAL);
			editUser(user);

		}

	}

	/**
	 * Esse � o m�todo, que verifica se um cliente existe
	 * 
	 * @param String idClient - Identificador do cliente
	 * @return Retorna verdadeiro se existe e falso se n�o existe
	 */
	public static boolean containsClient(String idClient) {

		for (String id : mapUsers.keySet()) {

			if (id.equals(idClient)) {

				return true;
			}

		}

		return false;

	}

	/**
	 * Esse � o m�todo, que faz a autentica��o de um usuario atrav�s do id e senha.
	 * 
	 * @param String idClient - Identificador do cliente
	 * @param String password - Senha do cliente
	 * @return Retorna verdadeiro se existir o client e falso se n�o
	 */
	public static boolean authClient(String idClient, String passwordClient) {

		for (Entry<String, User> client : mapUsers.entrySet()) {

			if (client.getValue().getId().equals(idClient) && client.getValue().getPassword().equals(passwordClient)) {

				return true;

			}

		}
		return false;
	}

}
