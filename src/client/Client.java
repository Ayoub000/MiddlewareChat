package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

	public static void main(String[] args) {
		Dialogue myComponent;
		try {
			//example of a RMI URL use to retrieve a remote reference 
			myComponent = (Dialogue) Naming.lookup("rmi://localhost:1099/Dialogue");
			myComponent.connect("Ayoub");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			
			e.printStackTrace();
		}

	}

}
