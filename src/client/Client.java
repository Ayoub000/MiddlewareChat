package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {

	static private Dialogue myComponent;
	public static Dialogue getMyComponent() {
		return myComponent;
	}

	public static void main(String[] args) {

		try {
			//example of a RMI URL use to retrieve a remote reference
			myComponent = (Dialogue) Naming.lookup("rmi://localhost:1099/Dialogue");

		} catch (MalformedURLException | RemoteException | NotBoundException e ) {
			e.printStackTrace();
		}
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../viewFX/Connection.fxml"));
			Scene scene = new Scene(root,700,600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Chat");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
