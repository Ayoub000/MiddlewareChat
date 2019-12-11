package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

	static private Connection myConnection = null;

	public Client()
	{
		try {
			//example of a RMI URL use to retrieve a remote reference
			myConnection = (Connection) Naming.lookup("rmi://localhost:1099/Connection");

		} catch (MalformedURLException | RemoteException | NotBoundException e ) {
			e.printStackTrace();
		}
	}
	public static Connection getMyConnection() {
		return myConnection;
	}
}
