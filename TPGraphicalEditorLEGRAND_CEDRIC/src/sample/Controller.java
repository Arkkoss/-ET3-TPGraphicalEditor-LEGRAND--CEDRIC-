package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Controller {
	
	private Modele modele;
	
	private EventHandler radioButtonSMListener;
	private EventHandler radioButtonEListener;
	private EventHandler radioButtonRListener;
	private EventHandler radioButtonLListener;
	private EventHandler paneDListener;
	private EventHandler colorPickerCListener;
	
	private Line currentLine;
	
	
	@FXML
	private HBox HBDessin;
	
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
	private Pane pnDessin;
	
	@FXML
	public void initialize() {
		
		this.modele = new Modele(this);
		
		//On initialise le bouton selectionne par defaut
		radSelectMove.setSelected(true);
		
		//On place des listeners sur les composants
		
		//SelectMove
		
        radioButtonSMListener = new EventHandler(){
            @Override
            public void handle(Event event) {
            	modele.setValeurSelectMove(true);
            	modele.setValeurEllipse(false);
            	modele.setValeurRectangle(false);
            	modele.setValeurLine(false);
            	System.out.println("testSM");
            	System.out.println(modele.getValeurLine());
            }
         };
         
         radSelectMove.setOnAction(radioButtonSMListener);
         
         //Ellipse
         radioButtonEListener = new EventHandler(){
             @Override
             public void handle(Event event) {
             	modele.setValeurSelectMove(false);
             	modele.setValeurEllipse(true);
             	modele.setValeurRectangle(false);
             	modele.setValeurLine(false);
             	System.out.println("testE");
             	System.out.println(modele.getValeurLine());
             }
          };
          
          radEllipse.setOnAction(radioButtonEListener);
          
          //Rectangle
          radioButtonRListener = new EventHandler(){
              @Override
              public void handle(Event event) {
              	modele.setValeurSelectMove(false);
              	modele.setValeurEllipse(false);
              	modele.setValeurRectangle(true);
              	modele.setValeurLine(false);
              	System.out.println("testR");
              	System.out.println(modele.getValeurLine());
              }
           };
           
           radRectangle.setOnAction(radioButtonRListener);
           
           //Line
           radioButtonLListener = new EventHandler(){
               @Override
               public void handle(Event event) {
               	modele.setValeurSelectMove(false);
               	modele.setValeurEllipse(false);
               	modele.setValeurRectangle(false);
               	modele.setValeurLine(true);
               	System.out.println("testL");
               	System.out.println(modele.getValeurLine());
               }
            };
            
            radLine.setOnAction(radioButtonLListener);
            
                 
		//On implemente les listener de la zone de dessin, verifiant quel forme est selectionnee

					
					pnDessin.setOnMousePressed(e -> {
						if(modele.getValeurLine()) {
							currentLine = new Line(e.getX(), e.getY(), e.getX(), e.getY());
							currentLine.setStroke(modele.getValeurColor());
							pnDessin.getChildren().add(currentLine);
						}
						
					});
					
					pnDessin.setOnMouseDragged(e -> {
						if(modele.getValeurLine()) {
							if (currentLine != null) {
								currentLine.setEndX(e.getX());
								currentLine.setEndY(e.getY());
							}
						}
					});
										

		
		//On initialise la couleur de base dans le modele
		modele.setValeurColor(cpColor.getValue());
		
		//On ajoute le listener du colorpicker pour mettre la valeur dans modele a jour
		
		colorPickerCListener = new EventHandler() {
			@Override
			public void handle(Event event) {
				modele.setValeurColor(cpColor.getValue());
				System.out.println(cpColor.getValue());
			}
		};
		
		cpColor.setOnAction(colorPickerCListener);
		
	}
	
}
