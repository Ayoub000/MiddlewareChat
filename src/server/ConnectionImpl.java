package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import client.Connection;
import client.Dialogue;

public class ConnectionImpl extends UnicastRemoteObject implements Connection {

	private DialogueImpl dial;
	private static int new_client = 0;

	protected ConnectionImpl() throws RemoteException
	{
		super();
	}

	@Override
	public Dialogue connect(String pseudo)
	{
		dial = null;
		try {
			dial = new DialogueImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		dial.getClients().put(pseudo, new ArrayList<String>());
		new_client += 1;
		dial.setNewClient(new_client);
		return (Dialogue)dial;
	}

	@Override
	public void disconnect(String pseudo) {
		dial.getClients().remove(pseudo);
		new_client -= 1;
		dial.setNewClient(new_client);
	}

}
