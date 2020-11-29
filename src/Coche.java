
public class Coche {
	
	private String matricula;
	private String color;
	private int numPuertas;

	public Coche() {

	}

	public Coche(String matricula, String color,int numPuertas) {

		this.matricula = matricula;
		this.color = color;
		this.numPuertas=numPuertas;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	public int getNumPuertas() {
		return numPuertas;
	}

	public void setNumPuertas(int numPuertas) {
		this.numPuertas = numPuertas;
	}

	public String toString() {

		return matricula + " " + color + " "+numPuertas+" ";
	}
}
