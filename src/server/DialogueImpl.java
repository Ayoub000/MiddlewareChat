package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

import client.Dialogue;

public class DialogueImpl extends UnicastRemoteObject implements Dialogue {

	private static HashMap<String,List<String>> clientList = new HashMap<String,List<String>>();
	private static int new_message = 0;
	private static int new_client = 0;

	public DialogueImpl() throws RemoteException {
		super();
	}

	public int getNewMessage()
	{
		return new_message;
	}

	public int getNewClient()
	{
		return new_client;
	}

	public void setNewMessage(int value)
	{
		DialogueImpl.new_message = value;
	}
	public void setNewClient(int value)
	{
		DialogueImpl.new_client = value;
	}

	@Override
	public HashMap<String,List<String>> getClients() {
		return clientList;
	}

	@Override
	public void sendMessage(String from, String to, String message) {
		try
		{
			clientList.get(to).add("J'envois : "+message+" à "+to);
			clientList.get(from).add("J'envois : "+message+" à "+to);
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
