package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.Receiver;

public interface Connection extends Remote {

	Emitter connect(String nickname, Receiver rcv) throws RemoteException;
	void disconnect(String nickname) throws RemoteException;

}
