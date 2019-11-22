package server;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import client.Dialogue;

public class DialogueImpl extends UnicastRemoteObject implements Dialogue {

	private HashMap<String,List<String>> clientList;

	public DialogueImpl() throws RemoteException {
		super();
		clientList = new HashMap<String,List<String>>();
	}

	@Override
	public void connect(String pseudo) {
		clientList.put(pseudo, new ArrayList<String>());
	}

	@Override
	public void disconnect(String pseudo) {
		clientList.remove(pseudo);
	}

	@Override
	public HashMap<String,List<String>> getClients() {
		return clientList;
	}

	@Override
	public void sendMessage(String from, String to, String message) {
		clientList.get(to).add("Je suis "+from+" et j'envois : "+message);
		clientList.get(from).add("Je suis "+from+" et j'envois : "+message);
		//clientList.put(to, clientList.get(to));
	}

	@Override
	public List<String> getMessages(String pseudo) {
		return clientList.get(pseudo);
	}

}
