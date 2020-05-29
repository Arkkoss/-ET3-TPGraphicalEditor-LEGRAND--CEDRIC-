package sample;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Modele {
	
	
	private boolean valeurSelectMove = true;
	private boolean valeurEllipse = false;
	private boolean valeurRectangle = false;
	private boolean valeurLine = false;
	private Color valeurColor;
	private List<Shape> lstFormes = new ArrayList<Shape>();

	public List<Shape> getLstFormes() {
		return lstFormes;
	}

	private Controller controller;
	
	public Modele(Controller controller) {
		this.controller = controller;
	}

	public boolean getValeurSelectMove() {
		return valeurSelectMove;
	}

	public void setValeurSelectMove(boolean valeurSelectMove) {
		this.valeurSelectMove = valeurSelectMove;
	}

	public boolean getValeurEllipse() {
		return valeurEllipse;
	}

	public void setValeurEllipse(boolean valeurEllipse) {
		this.valeurEllipse = valeurEllipse;
	}

	public boolean getValeurRectangle() {
		return valeurRectangle;
	}

	public void setValeurRectangle(boolean valeurRectangle) {
		this.valeurRectangle = valeurRectangle;
	}

	public boolean getValeurLine() {
		return valeurLine;
	}

	public void setValeurLine(boolean valeurLine) {
		this.valeurLine = valeurLine;
	}

	public Color getValeurColor() {
		return valeurColor;
	}

	public void setValeurColor(Color valeurColor) {
		this.valeurColor = valeurColor;
	}
	

	
	
}
