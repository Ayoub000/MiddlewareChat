package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMain extends Application {


	public static void main(String[] args) {

		Client client = new Client();
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
