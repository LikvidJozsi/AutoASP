package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.KontirSor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class KontirSorokTabController implements Initializable{
	
	// References to table and it's columns containing the existing kontirTetels
	@FXML
	TableView<KontirSor> kontirTetelekTable;
	@FXML
	TableColumn<KontirSor, String> nevColumn;
	@FXML
	TableColumn<KontirSor, String> megjegyzesColumn;
	@FXML
	TableColumn<KontirSor, String> kontirAzonositoColumn;

	// References to controls for adding a new kontirTetel
	
	@FXML
	TextField nevTextField;
	@FXML
	TextField megjegyzesTextField;
	@FXML
	TextField kontirAzonositoTextField;
	
	
	//Collection containing current kontirtetels
	ObservableList<KontirSor> kontirSorok;
	
	
	public KontirSorokTabController() {
		kontirSorok = FXCollections.observableArrayList(new ArrayList<KontirSor>());
	}

	
	public void addEventHandler() {
		KontirSor newsor = new KontirSor();
		newsor.setNev(nevTextField.getText());
		newsor.setMegjegyzes(megjegyzesTextField.getText());
		newsor.setKontirAzonosito(kontirAzonositoTextField.getText());
		kontirSorok.add(newsor);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setTablePropertyMapping();
		
		KontirSor pelda = new KontirSor();
		pelda.setNev("peldaNev");
		pelda.setKontirAzonosito("peldaAzonosito");
		pelda.setMegjegyzes("peldaMegjegyzes");
		kontirSorok.add(pelda);
		
		
		kontirTetelekTable.setItems(kontirSorok);
		
	}
	
	private void setTablePropertyMapping() {
		nevColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));
		megjegyzesColumn.setCellValueFactory(new PropertyValueFactory<>("megjegyzes"));
		kontirAzonositoColumn.setCellValueFactory(new PropertyValueFactory<>("kontirAzonosito"));
	}

}
