package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyEvent;

public class Controller {
	
	private Modele modele;
	
	private EventHandler radioButtonSMListener;
	
	
	@FXML
	private RadioButton radSelectMove;
	
	@FXML
	private RadioButton radEllipse;
	
	@FXML
	private RadioButton radRectangle;
	
	@FXML
	private RadioButton radLine;
	
	@FXML
	private ColorPicker cpColor;
	
	@FXML
	private Button btnDelete;
	
	@FXML
	private Button btnDuplicate;
	
	@FXML
	private Canvas cvsDessin;
	
	@FXML
	public void initialize() {
		
		this.modele = new Modele(this);
		
		//On initialise le bouton selectionne par defaut
		
        radioButtonSMListener = new EventHandler(){
            @Override
            public void handle(Event event) {
            	System.out.println("test");
            }
         };
         
         radSelectMove.setOnAction(radioButtonSMListener);

		
		
		
		
	}
	
}
