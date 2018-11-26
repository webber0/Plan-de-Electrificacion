package pack;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;


public class Prim {
	protected int[] centrales;
	private int costos = 0;
	private ArrayList<Nodo> lista_nodos = new ArrayList<Nodo>();

	public MatrizSimetrica leer_archivo(String path) throws IOException{
		File archivo = new File(path);
		Scanner sc = new Scanner(archivo);
		int cantidad_nodos = sc.nextInt();
		MatrizSimetrica matriz = new MatrizSimetrica(cantidad_nodos);
		int cantidad_centrales = sc.nextInt();
		this.centrales = new int[cantidad_centrales];
		for (int z = 0; z < cantidad_centrales; z++) {
			int dato=sc.nextInt();
			if(this.centrales[z]!=dato)this.centrales[z] = dato;
			else;
			}
		for (int i = 0; i < cantidad_nodos; i++) {
			for (int j = 0; j < cantidad_nodos; j++) {
				matriz.setInfo(i, j, sc.nextInt());

			}
		}
		sc.close();
		return matriz;
	}

	public void escribirArchivo() throws IOException {
		FileWriter fl = new FileWriter("electricidad.out");
		PrintWriter pw = new PrintWriter(fl);
		pw.println(this.costos);
		for (int i = 0; i < this.lista_nodos.size(); i++) {
			if (this.lista_nodos.get(i).getNodo1() < this.lista_nodos.get(i).getNodo2()) {
				pw.println(this.lista_nodos.get(i).getNodo1() + " " + this.lista_nodos.get(i).getNodo2());
			} else {
				pw.println(this.lista_nodos.get(i).getNodo2() + " " + this.lista_nodos.get(i).getNodo1());
			}
		}
		pw.close();

	}

	private void inicializar_vector(boolean[] visitado, int[] centrales) {
		for (int i = 0; i < visitado.length; i++) {
			visitado[i] = false;
		}

		for (int j = 0; j < centrales.length; j++) {
			visitado[centrales[j] - 1] = true;
		}
	}

	private boolean todosVisitados(boolean[] incluido) {
		for (boolean r : incluido) {
			if (!r)
				return r;
		}
		return true;
	}
	

	public void algoritmo(MatrizSimetrica grafo, int[] centrales) throws IOException{
		int grado = grafo.grado;
		boolean[] visitado = new boolean[grado];
		int fila = 0, columna = 0, fila_minima = 0, columna_minima = 0, minimo_actual = -1, minimo_anterior ;
		inicializar_vector(visitado, centrales);
		if (!todosVisitados(visitado)) {
			for (fila = 0; fila < grado ; fila++) {
				if (!visitado[fila]) {
					minimo_actual = -1;
					 minimo_anterior = -1;
					for (columna = 0; columna < grado; columna++) {
						if (fila != columna && grafo.getInfo(fila, columna) != 0 ) {
							if (minimo_actual == -1)
								minimo_actual = grafo.getInfo(fila, columna);
							else
								minimo_actual = Math.min(minimo_actual, grafo.getInfo(fila, columna));

							if (minimo_actual != minimo_anterior) {
								minimo_anterior = minimo_actual;
								fila_minima = fila+1;
								columna_minima = columna+1;
						
						
							}
						} else;
						
					}
					if (minimo_actual != -1) {
						lista_nodos.add(new Nodo((fila_minima), (columna_minima)));
						this.costos += minimo_actual;
				
					}
					visitado[fila] = true;
				}
			}
		}
		this.escribirArchivo();
	}

	

}
