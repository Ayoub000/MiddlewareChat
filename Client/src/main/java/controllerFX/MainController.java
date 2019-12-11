package controllerFX;

import java.net.URL;
import java.util.ResourceBundle;

import client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelFX.DataMonitor;


public class MainController implements Initializable{

	@FXML
	private Button connectButton;
	@FXML
	private TextField pseudoField;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}

	public void connectAction() throws Exception{
		DataMonitor.setPseudo(pseudoField.getText());
		DataMonitor.setEmitter(Client.getMyConnection().connect(DataMonitor.getPseudo(), DataMonitor.getReceiver()));
		Stage secondStage = (Stage) connectButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("../viewFX/Chat.fxml"));
		Scene scene = new Scene(root);
		secondStage.setScene(scene);

	}

}