package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import client.Dialogue;

public class DialogueImpl extends UnicastRemoteObject implements Dialogue {

	private HashMap<String,List<String>> clientList;
	private int new_message = 0;
	private int new_client = 0;

	public DialogueImpl() throws RemoteException {
		super();
		clientList = new HashMap<String,List<String>>();
	}

	public int getNewMessage()
	{
		return new_message;
	}

	public int getNewClient()
	{
		return new_client;
	}

	@Override
	public void connect(String pseudo) {
		clientList.put(pseudo, new ArrayList<String>());
		new_client += 1;
	}

	@Override
	public void disconnect(String pseudo) {
		clientList.remove(pseudo);
		new_client += 1;
	}

	@Override
	public HashMap<String,List<String>> getClients() {
		return clientList;
	}

	@Override
	public void sendMessage(String from, String to, String message) {
		try
		{
			clientList.get(to).add("Je suis "+from+" et j'envois : "+message);
			clientList.get(from).add("Je suis "+from+" et j'envois : "+message);
			new_message += 1;
		}
		catch(NullPointerException e)
		{
			System.err.println("No Receiver Specified !");
		}
	}

	@Override
	public List<String> getMessages(String pseudo) {
		return clientList.get(pseudo);
	}

}
