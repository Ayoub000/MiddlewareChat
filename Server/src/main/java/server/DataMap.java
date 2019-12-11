package server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class DataMap {

	private static List<Receiver> receivers = new ArrayList<Receiver>();
	private static List<String> clients = new ArrayList<String>();

	public static List<String> getClients() {
		return clients;
	}
	public static List<Receiver> getReceivers() throws RemoteException {
		return receivers;
	}


}
