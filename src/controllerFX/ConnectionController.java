package controllerFX;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import server.Server;

public class ConnectionController implements Initializable{

	@FXML
	private Button connectButton;
	@FXML
	private TextField pseudoField;

	private String pseudo;

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}

	public void connectAction() throws Exception{
		setPseudo(pseudoField.getText());
		Stage secondStage = (Stage) connectButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("../viewFX/Chat.fxml"));
		Scene scene = new Scene(root);
		secondStage.setScene(scene);
		secondStage.show();
		Server server = new Server();
		Server.getMyComponent().connect(pseudo);
	}

}
