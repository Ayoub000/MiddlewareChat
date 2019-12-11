package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

	private ConnectionImpl myConnection;

	public Server() throws RemoteException, MalformedURLException
	{
		// registry creation
		LocateRegistry.createRegistry(1099);
		// component instantiation implicit activation
		myConnection = new ConnectionImpl();
		// publication of component reference in the registry
		Naming.rebind("Connection", myConnection);
	}

	public ConnectionImpl getMyConnection() {
		return myConnection;
	}

	public void getRef()
	{
		System.out.println(myConnection.getRef().remoteToString());
		System.out.println("Serveur actif");
	}

}
