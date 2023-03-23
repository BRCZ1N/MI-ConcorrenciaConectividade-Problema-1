package services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import resources.User;
import utilityclasses.StatusConsumeEnum;

/**
 * Esta é a classe ConsumptionServices, que representa os serviços de usuarios
 * da aplicação.
 */
public class UserServices {

	private static Map<String, User> mapUsers;
	private static long id = 0;

	/**
	 * Esse é o construtor da classe UserServices, que atribui ao map que deverá
	 * possuir os clientes do sistema uma instancia.
	 */
	public UserServices() {

		mapUsers = new HashMap<String, User>();

	}

	/**
	 * Esse é o método que adiciona usuários para efeito de testes.
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
	 * Esse é o método, que adiciona um cliente ao map de clientes e que chama os
	 * metodos para adicionar chaves de clientes no map dos outros serviços.
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
	 * Esse é o método, que deleta um cliente do map de clientes e que chama os
	 * metodos para deletar dos outros serviços.
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
	 * Esse é o método, retorna um usuário caso ele exista.
	 * 
	 * @param String idClient - Identificador do cliente
	 * @return Retorna o objeto usuário
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
	 * Esse é o método, que faz a autenticação de um usuario através do id e senha.
	 * 
	 * @param String idClient - Identificador do cliente
	 * @param String password - Senha do cliente
	 * @return Retorna um array de bytes contendo a mensagem de autenticação
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
	 * Esse é o método, que faz a autenticação de um usuario através do id e de sua
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
	 * Esse é o método, que retorna o status de consumo do cliente
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
	 * Esse é o método, que atualiza o status de consumo cliente
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
	 * Esse é o método, que verifica se um cliente existe
	 * 
	 * @param String idClient - Identificador do cliente
	 * @return Retorna verdadeiro se existe e falso se não existe
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
	 * Esse é o método, que faz a autenticação de um usuario através do id e senha.
	 * 
	 * @param String idClient - Identificador do cliente
	 * @param String password - Senha do cliente
	 * @return Retorna verdadeiro se existir o client e falso se não
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
