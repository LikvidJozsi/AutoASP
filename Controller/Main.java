package Controller;



import DAL.InstitutionChooserMenu;
import DAL.KaszperPage;
import DAL.Menu1372;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application implements EventHandler<WindowEvent>{
	
	FrameController controller;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			String institutionname = "TISZANÁNAI ÖNKORMÁNYZATI KONYHA";
			
			
			//AcceptAndFinalizeTask task = new AcceptAndFinalizeTask(currentpage);
			//task.execute();
			// Create a loader object and load View and Controller
			//final FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("View.fxml"));
			final FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Frame.fxml"));
			final VBox viewRoot = (VBox) loader.load();
			// Get controller object
			controller = loader.getController();
			// Set scene (and the title of the window) and display it
			Scene scene = new Scene(viewRoot);
			primaryStage.setScene(scene);
			primaryStage.setOnCloseRequest(this);
			primaryStage.setTitle("AutoAsp");
			primaryStage.show();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	/**
	 * It is called before application is stopped
	 */
	@Override
	public void stop() {
		controller.disconnect();
	}

	/**
	 * Handling the closing of the main window.
	 */
	@Override
	public void handle(WindowEvent event) {
		// TODO Task 4.2
	}
	
	

	

}
