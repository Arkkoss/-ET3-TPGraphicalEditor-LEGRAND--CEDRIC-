package sample;

public class Modele {
	
	
	private boolean valeurSelectMove = true;
	private boolean valeurEllipse = false;
	private boolean valeurRectangle = false;
	private boolean valeurLine = false;

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
	
	
}