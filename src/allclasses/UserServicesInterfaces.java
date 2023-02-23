package allclasses;

public interface UserServicesInterfaces<T> {

	public boolean addClient(T user);

	public boolean deleteClient(String id);

	public T getUser(String id);

}
