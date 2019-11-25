package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

public interface Dialogue extends Remote {

	HashMap<String,List<String>> getClients() throws RemoteException;
	void sendMessage(String from ,String to, String message) throws RemoteException;
	List<String> getMessages(String pseudo) throws RemoteException;
	int getNewMessage() throws RemoteException;
	int getNewClient() throws RemoteException;
}
