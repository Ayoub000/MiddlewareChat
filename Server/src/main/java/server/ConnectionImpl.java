package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import client.Connection;
import client.Emitter;

public class ConnectionImpl extends UnicastRemoteObject implements Connection {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static int new_client = 0;
	public ConnectionImpl() throws RemoteException {
		super();
	}

	@Override
	public Emitter connect(String nickname, Receiver rcv) throws RemoteException {
		for(Receiver r : DataMap.getReceivers()) r.addClient(nickname);
		DataMap.getReceivers().add(rcv);
		DataMap.getClients().add(nickname);
		rcv.initClients(DataMap.getClients());
		new_client += 1;
		return new EmitterImpl(new_client,nickname);
	}

	@Override
	public void disconnect(String nickname) throws RemoteException {
		DataMap.getClients().remove(nickname);
		for(Receiver r : DataMap.getReceivers()) r.removeClient(nickname);
	}
}
