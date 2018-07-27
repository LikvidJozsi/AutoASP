package Controller;


import Controller.Tasks.AcceptAndFinalizeTask;
import Controller.Tasks.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AcceptAndFinalizeFormController extends TaskFormController{
	
	@FXML
	TextField bankPenztarDatum;
	
	@FXML
	TextField ev;
	
	@Override
	public Task getTask() {
		AcceptAndFinalizeTask task = new AcceptAndFinalizeTask();
		task.setBankpenztarnapszuro(bankPenztarDatum.getText());
		task.setEvszuro(ev.getText());
		return task;
	}
}
