package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Emitter extends Remote {

	void sendMessage(String to, String message) throws RemoteException;
	int getNew_message() throws RemoteException;
	void setNew_message(int new_message) throws RemoteException;
	int getNew_client() throws RemoteException;
	void setNew_client(int new_client) throws RemoteException;
	String getPseudo() throws RemoteException;
	void setPseudo(String pseudo) throws RemoteException;

}
