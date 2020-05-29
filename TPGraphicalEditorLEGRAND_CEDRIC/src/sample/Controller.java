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
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Controller {
	
	private Modele modele;
	
	
	private EventHandler radioButtonSMListener;
	private EventHandler radioButtonEListener;
	private EventHandler radioButtonRListener;
	private EventHandler radioButtonLListener;
	private EventHandler paneDListener;
	private EventHandler colorPickerCListener;
	private EventHandler buttonDeleteListener;
	private EventHandler buttonDuplicateListener;
	
	private Line currentLine;
	private Rectangle currentRectangle;
	private Ellipse currentEllipse;
	private Shape selectedShape;
	
	//conteneurs fxml
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
            	btnDelete.setDisable(false);
            	btnDuplicate.setDisable(false);
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
             	btnDelete.setDisable(true);
             	btnDuplicate.setDisable(true);
               	if(selectedShape != null) selectedShape.setStrokeWidth(3);
               	selectedShape = null;
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
              	btnDelete.setDisable(true);
              	btnDuplicate.setDisable(true);
               	if(selectedShape != null) selectedShape.setStrokeWidth(3);
               	selectedShape = null;
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
               	btnDelete.setDisable(true);
               	btnDuplicate.setDisable(true);
               	if(selectedShape != null) selectedShape.setStrokeWidth(3);
               	selectedShape = null;
               	System.out.println("testL");
               	System.out.println(modele.getValeurLine());
               }
            };
            
            radLine.setOnAction(radioButtonLListener);
            
                 
		//On implemente les listener de la zone de dessin, verifiant quel bouton radio est selectionne

				
					pnDessin.setOnMousePressed(e -> {
						if(modele.getValeurLine()) {				//creation de line
							currentLine = new Line(e.getX(), e.getY(), e.getX(), e.getY());
							currentLine.setStroke(modele.getValeurColor());
							currentLine.setStrokeWidth(3);
							currentLine.setOnMouseClicked( el -> {
								selectedShape = (Line) el.getSource();
								for (Shape s :modele.getLstFormes()) {
									s.setStrokeWidth(3);
								}
								selectedShape.setStrokeWidth(6);
								System.out.println(selectedShape.getStroke());
							});
							
							currentLine.setOnMouseDragged(eml -> {
								Line temp = (Line) eml.getSource();
								double startX = eml.getX()-temp.getStartX();//((Line) selectedShape)
								double startY = eml.getY()-temp.getStartY();
								
								temp.setStartX(eml.getX());
								temp.setStartY(eml.getY());
								temp.setEndX(temp.getEndX()+ startX);
								temp.setEndY(temp.getEndY()+ startY);
							});
							
							modele.getLstFormes().add(currentLine);
							pnDessin.getChildren().add(currentLine);
						}
						else if (modele.getValeurRectangle()) { 		//creation de rectangle
							currentRectangle = new Rectangle(e.getX(), e.getY(), 0, 0);
							currentRectangle.setFill(modele.getValeurColor());
							currentRectangle.setStroke(Color.BLACK);
							currentRectangle.setStrokeWidth(3);
							
							currentRectangle.setOnMouseClicked( el -> {
								selectedShape = (Rectangle) el.getSource();
								for (Shape s :modele.getLstFormes()) {
									s.setStrokeWidth(3);
								}
								selectedShape.setStrokeWidth(6);
								System.out.println(selectedShape.getStroke());
							});
							
							currentRectangle.setOnMouseDragged(eml -> {
								Rectangle temp = (Rectangle) eml.getSource();
								double startX = eml.getX()-temp.getX();
								double startY = eml.getY()-temp.getY();
								
								temp.setX(eml.getX());
								temp.setY(eml.getY());

							});
							
							
							modele.getLstFormes().add(currentRectangle);
							pnDessin.getChildren().add(currentRectangle);
						}
						else if (modele.getValeurEllipse() ) {				//creation de ellipse
							currentEllipse = new Ellipse(e.getX(), e.getY(), 0, 0);
							currentEllipse.setFill(modele.getValeurColor());
							currentEllipse.setStroke(Color.BLACK);
							currentEllipse.setStrokeWidth(3);
							
							currentEllipse.setOnMouseClicked( el -> {
								selectedShape = (Ellipse) el.getSource();
								for (Shape s :modele.getLstFormes()) {
									s.setStrokeWidth(3);
								}
								selectedShape.setStrokeWidth(6);
								System.out.println(selectedShape.getStroke());
							});
							
							currentEllipse.setOnMouseDragged(eml -> {
								Ellipse temp = (Ellipse) eml.getSource();
								double startX = eml.getX()-temp.getCenterX();
								double startY = eml.getY()-temp.getCenterY();
								
								temp.setCenterX(eml.getX());
								temp.setCenterY(eml.getY());

							});
							
							modele.getLstFormes().add(currentEllipse);
							pnDessin.getChildren().add(currentEllipse);
						}
						
					});
					
					pnDessin.setOnMouseDragged(e -> {
						if(modele.getValeurLine()) {		//creation de line
							if (currentLine != null) {
								currentLine.setEndX(e.getX());
								currentLine.setEndY(e.getY());
							}
						}
						else if(modele.getValeurRectangle()) {		//creation de rectangle
							if (currentRectangle != null) {
								currentRectangle.setWidth(e.getX()- currentRectangle.getX());
								currentRectangle.setHeight(e.getY() - currentRectangle.getY());
								
							}
						}
						else if(modele.getValeurEllipse()) {		//creation de ellipse
							if (currentEllipse != null) {
								currentEllipse.setRadiusX(Math.abs(e.getX()- currentEllipse.getCenterX()));
								currentEllipse.setRadiusY(Math.abs(e.getY() - currentEllipse.getCenterY()));
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
				if(modele.getValeurSelectMove()) {
					if(selectedShape != null) {
						if(selectedShape.getClass().getSimpleName().equals("Line")) {
							selectedShape.setStroke(modele.getValeurColor());
						}
						else {
							selectedShape.setFill(modele.getValeurColor());
						}
					}
				}
			}
		};
		
		cpColor.setOnAction(colorPickerCListener);
		
		// On ajoute le listener du bouton delete
		
		buttonDeleteListener = new EventHandler() {
			@Override
            public void handle(Event event) {
				pnDessin.getChildren().remove(selectedShape);
				modele.getLstFormes().remove(selectedShape);
				selectedShape = null;
			}
		};
		
		btnDelete.setOnAction(buttonDeleteListener);
		
		// On ajoute le listeenr du bouton duplicate
		
		buttonDuplicateListener = new EventHandler() {
			@Override
            public void handle(Event event) {
				if(selectedShape != null) {
					if(selectedShape instanceof Line ) {			//duplication de line
						Line temp = (Line) selectedShape;
						Line dupli = new Line(temp.getStartX()+10,temp.getStartY()+10,temp.getEndX()+10,temp.getEndY()+10);
						dupli.setStroke(temp.getStroke());
						dupli.setStrokeWidth(3);
						
						dupli.setOnMouseClicked( el -> {
							selectedShape = (Line) el.getSource();
							for (Shape s :modele.getLstFormes()) {
								s.setStrokeWidth(3);
							}
							selectedShape.setStrokeWidth(6);
							System.out.println(selectedShape.getStroke());
						});
						
						dupli.setOnMouseDragged(eml -> {
							Line tempdupli = (Line) eml.getSource();
							double startX = eml.getX()-tempdupli.getStartX();
							double startY = eml.getY()-tempdupli.getStartY();
							
							tempdupli.setStartX(eml.getX());
							tempdupli.setStartY(eml.getY());
							tempdupli.setEndX(tempdupli.getEndX()+ startX);
							tempdupli.setEndY(tempdupli.getEndY()+ startY);
						});
						
						pnDessin.getChildren().add(dupli);
						modele.getLstFormes().add(dupli);
						if(selectedShape != null) selectedShape.setStrokeWidth(3);
						selectedShape = null;
					}
					else if(selectedShape instanceof Rectangle) {			//duplication de rectangle
						Rectangle temp = (Rectangle) selectedShape;
						Rectangle dupli = new Rectangle(temp.getX()+10,temp.getY()+10,temp.getWidth(),temp.getHeight());
						dupli.setFill(temp.getFill());
						dupli.setStroke(temp.getStroke());
						dupli.setStrokeWidth(3);
						
						dupli.setOnMouseClicked( el -> {
							selectedShape = (Rectangle) el.getSource();
							for (Shape s :modele.getLstFormes()) {
								s.setStrokeWidth(3);
							}
							selectedShape.setStrokeWidth(6);
							System.out.println(selectedShape.getStroke());
						});
						
						dupli.setOnMouseDragged(eml -> {
							Rectangle tempdupli = (Rectangle) eml.getSource();
							double startX = eml.getX()-tempdupli.getX();
							double startY = eml.getY()-tempdupli.getY();
							
							tempdupli.setX(eml.getX());
							tempdupli.setY(eml.getY());

						});
						
						pnDessin.getChildren().add(dupli);
						modele.getLstFormes().add(dupli);
						if(selectedShape != null) selectedShape.setStrokeWidth(3);
						selectedShape = null;
					}
					
					else if(selectedShape instanceof Ellipse) {  //duplication de ellipse
						Ellipse temp = (Ellipse) selectedShape;
						Ellipse dupli = new Ellipse(temp.getCenterX()+10,temp.getCenterY()+10,temp.getRadiusX(),temp.getRadiusY());
						dupli.setFill(temp.getFill());
						dupli.setStroke(temp.getStroke());
						dupli.setStrokeWidth(3);
						
						dupli.setOnMouseClicked( el -> {
							selectedShape = (Ellipse) el.getSource();
							for (Shape s :modele.getLstFormes()) {
								s.setStrokeWidth(3);
							}
							selectedShape.setStrokeWidth(6);
							System.out.println(selectedShape.getStroke());
						});
						
						dupli.setOnMouseDragged(eml -> {
							Ellipse tempdupli = (Ellipse) eml.getSource();
							double startX = eml.getX()-tempdupli.getCenterX();//((Line) selectedShape)
							double startY = eml.getY()-tempdupli.getCenterY();
							
							tempdupli.setCenterX(eml.getX());
							tempdupli.setCenterY(eml.getY());

						});
						
						pnDessin.getChildren().add(dupli);
						modele.getLstFormes().add(dupli);
						if(selectedShape != null) selectedShape.setStrokeWidth(3);
						selectedShape = null;
					}
				}
			}
		};
		
		btnDuplicate.setOnAction(buttonDuplicateListener);
		
		
	}
	
}
