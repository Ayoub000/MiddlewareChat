package client;

import java.rmi.Remote;
import java.util.HashMap;
import java.util.List;

public interface Dialogue extends Remote {

	void connect(String pseudo);
	void disconnect(String pseudo);
	HashMap getClients();
	void sendMessage(String from, String to, String message);
	List<String> getMessages(String pseudo);
}
