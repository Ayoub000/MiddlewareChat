package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import modelFX.DataMonitor;
import server.Receiver;

public class ReceiverImpl extends UnicastRemoteObject implements Receiver {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ReceiverImpl() throws RemoteException {
		super();
	}


	@Override
	public void receive(String from, String message) throws RemoteException {

		DataMonitor.getMessages().add("Je suis "+from+" et j'envois : "+message);
		int temp = DataMonitor.getEmitter().getNew_message() + 1;
		DataMonitor.getEmitter().setNew_message(temp);
	}

	@Override
	public void initClients(List<String> clients) throws RemoteException {
		DataMonitor.setClientList(clients);
	}

	@Override
	public void addClient(String client) throws RemoteException {
		DataMonitor.getClientList().add(client);
	}

	@Override
	public void removeClient(String client) throws RemoteException {
		int temp = DataMonitor.getEmitter().getNew_client() + 1;
		DataMonitor.getEmitter().setNew_client(temp);
		DataMonitor.getClientList().remove(client);

	}

	public String getPseudo() throws RemoteException
	{
		return DataMonitor.getPseudo();
	}



}
