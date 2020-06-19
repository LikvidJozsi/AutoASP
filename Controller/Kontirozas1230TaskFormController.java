package Controller;


import java.net.URL;
import java.util.ResourceBundle;

import Controller.Tasks.Task;
import Controller.Tasks.Kontirozas1230.Kontirozas1230Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class Kontirozas1230TaskFormController extends TaskFormController implements Initializable{
	
	@FXML
	TextField bankPenztarDatum;
	
	@FXML
	TextField partnerNev;

	@Override
	public Task getTask() {
		Kontirozas1230Task task = new Kontirozas1230Task();
		task.setBankPenztarNapFilter(bankPenztarDatum.getText());
		task.setPartnerNevFilter(partnerNev.getText());
		task.setKontirSorok(KontirSorManager.getInstance().getKontirSorok());
		return task;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
