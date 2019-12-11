package server;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class ServerMain {

	public static void main(String[] argv) throws RemoteException, MalformedURLException
	{
		Server server = new Server();
		server.getRef();
	}
}
