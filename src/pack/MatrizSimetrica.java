package pack;

public class MatrizSimetrica{
	int grado;
	int[][] matriz;
	public MatrizSimetrica(int grado) {
		super();
		this.grado = grado;
		this.matriz = new int[grado][grado];
	}
	
	public int getGrado() {
		return this.grado;
	}
	
	public int getInfo(int fila,int columna) {
	
		return this.matriz[fila][columna];
	}
	
	public void setInfo(int fila,int columna,int costo) {
		this.matriz[fila][columna]=costo;
	}
}
