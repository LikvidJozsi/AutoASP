package Controller;


import java.net.URL;
import java.util.ResourceBundle;

import Controller.Tasks.Kontirozas1230Task;
import Controller.Tasks.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class Kontirozas1230TaskFormController extends TaskFormController implements Initializable{
	
	@FXML
	TextField bankPenztarDatum;
	
	@FXML
	TextField afa27;
	
	@FXML
	TextField afa18;
	
	@FXML
	TextField afa5;
	
	@FXML
	TextField ahk;
	
	@FXML
	TextField afaMentes;
	
	@FXML
	TextField megjegyzes;
	
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
		afa27.setText("S0824");
		afa5.setText("");
		afa18.setText("");
		ahk.setText("S0342");
		afaMentes.setText("S0829");
		
		
		megjegyzes.setText("közvilágítás");
	}

}
