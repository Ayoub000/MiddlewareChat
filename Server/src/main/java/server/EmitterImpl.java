package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.Emitter;

public class EmitterImpl extends UnicastRemoteObject implements Emitter {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String pseudo;
	private static int new_message = 0;
	private static int new_client = 0;


	protected EmitterImpl() throws RemoteException {}

	protected EmitterImpl(int new_client,String pseudo) throws RemoteException {
		super();
		EmitterImpl.new_client = new_client;
		this.pseudo = pseudo;
	}

	public int getNew_message() {
		return new_message;
	}
	public void setNew_message(int new_message) {
		EmitterImpl.new_message = new_message;
	}
	public int getNew_client() {
		return new_client;
	}
	public void setNew_client(int new_client) {
		EmitterImpl.new_client = new_client;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	@Override
	public void sendMessage(String to, String message) throws RemoteException {
		for(Receiver r : DataMap.getReceivers())
		{
			if(r.getPseudo().equals(to)) r.receive(getPseudo(), message);
		}
	}


}
