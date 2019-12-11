package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Receiver extends Remote {

	void receive(String from,String message) throws RemoteException;
	void initClients(List<String> clients) throws RemoteException;
	void addClient(String client) throws RemoteException;
	void removeClient(String client) throws RemoteException;
	String getPseudo() throws RemoteException;

}
