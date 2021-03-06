package edu.jdgp;

import java.util.HashMap;
import java.util.Arrays;
import java.util.Random;

public class SumaMatriz {
	int n;
	int m;
	int[][] matriz;
	int[][] sumasParciales;
	public String[][] caminos;

	public void matriz() {
		m = 4; n = 4; 
		matriz = new int[m][n];
		matriz[0][0] = 2; matriz[0][1] = 8; matriz[0][2] = 3; matriz[0][3] = 4;
		matriz[1][0] = 5; matriz[1][1] = 3; matriz[1][2] = 4; matriz[1][3] = 5;
		matriz[2][0] = 1; matriz[2][1] = 2; matriz[2][2] = 2; matriz[2][3] = 1;
		matriz[3][0] = 3; matriz[3][1] = 4; matriz[3][2] = 6; matriz[3][3] = 5;
		sumasParciales = new int[m][n];
		caminos = new String[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				sumasParciales[i][j] = -1;
				caminos[i][j] = "";
			}
		}
	}
/*
	public void matriz() {
		m = 100; n = 100;
		matriz = new int[m][n];
		sumasParciales = new int[m][n];
		caminos = new String[m][n];
		Random rand = new Random();
		for (int i = 0; i <m; i++) {
			for (int j = 0; j < n; j++) {
				matriz[i][j] = rand.nextInt(10);
				sumasParciales[i][j] = -1;
				caminos[i][j] = "";
			}
		}
	}
*/
	public int sumaMatriz(int x, int y) {
		if (x == m || y == n)
			return -1;
		if (sumasParciales[x][y] != -1) 
			return sumasParciales[x][y];
		if (x == m-1 && y == n-1)
			return matriz[m-1][n-1];
					
		int suma = -1;
		int abajo = sumaMatriz(x, y+1);
		int derecha = sumaMatriz(x+1, y);
		if (abajo >= 0 && derecha >= 0) {
			if (abajo < derecha) {
				suma = abajo + matriz[x][y];
				caminos[x][y] = "D" + caminos[x][y+1];
			} else {
				suma = derecha + matriz[x][y];
				caminos[x][y] = "A" + caminos[x+1][y];
			}
		}
		else if (abajo == -1) {
			suma = derecha + matriz[x][y];
			caminos[x][y] = "A" + caminos[x+1][y];
		}
		else if (derecha == -1) {
			suma = abajo + matriz[x][y];
			caminos[x][y] = "A" + caminos[x][y+1];
		}
		sumasParciales[x][y] = suma;
		return suma;
	}

	public static void main(String args[]) {
		SumaMatriz sm = new SumaMatriz();
		sm.matriz();
		long start; int s; long end;
		start = System.nanoTime();
		s = sm.sumaMatriz(0,0);
		end = System.nanoTime();
		System.out.println("Programacion dinamica: " + s + " " + (end - start) + " " + sm.caminos[0][0]);
	}
	
}

/*
public class SumaMatriz {
	int n;
	int m;
	int[][] matriz;
	int[][] sumasParciales;


	public void matriz() {
		m = 15; n = 15;
		matriz = new int[m][n];
		sumasParciales = new int[m][n];
		Random rand = new Random();
		for (int i = 0; i <m; i++) {
			for (int j = 0; j < n; j++) {
				matriz[i][j] = rand.nextInt(10);
				sumasParciales[i][j] = -1;
			}
		}
	}

	public void matriz() {
		m = 4; n = 4; 
		matriz = new int[m][n];
		matriz[0][0] = 2; matriz[0][1] = 8; matriz[0][2] = 3; matriz[0][3] = 4;
		matriz[1][0] = 5; matriz[1][1] = 3; matriz[1][2] = 4; matriz[1][3] = 5;
		matriz[2][0] = 1; matriz[2][1] = 2; matriz[2][2] = 2; matriz[2][3] = 1;
		matriz[3][0] = 3; matriz[3][1] = 4; matriz[3][2] = 6; matriz[3][3] = 5;
	}

	public int sumaMatriz(int x, int y) {
		if (sumasParciales.get(x + "_" + y) != null) {
			return sumasParciales.get(x + "_" + y);
		}
		if (x == m || y == n)
			return -1;
		if (x == m-1 && y == n-1)
			return matriz[m-1][n-1];
					
		int suma = -1;
		int abajo = sumaMatriz(x, y+1);
		int derecha = sumaMatriz(x+1, y);
		if (abajo >= 0 && derecha >= 0)
			suma = Math.min(abajo, derecha) + matriz[x][y];
		else if (abajo == -1)
			suma = derecha + matriz[x][y];
		else if (derecha == -1)
			suma = abajo + matriz[x][y];
		sumasParciales.put(x + "_" + y, new Integer(suma));
		return suma;
	}

	public int sumaMatriz(int x, int y) {
		if (x == m || y == n)
			return -1;
		if (sumasParciales[x][y] != -1) 
			return sumasParciales[x][y];
		if (x == m-1 && y == n-1)
			return matriz[m-1][n-1];
					
		int suma = -1;
		int abajo = sumaMatriz(x, y+1);
		int derecha = sumaMatriz(x+1, y);
		if (abajo >= 0 && derecha >= 0)
			suma = Math.min(abajo, derecha) + matriz[x][y];
		else if (abajo == -1)
			suma = derecha + matriz[x][y];
		else if (derecha == -1)
			suma = abajo + matriz[x][y];
		sumasParciales[x][y] = suma;
		return suma;
	}

	public int sumaMatriz2(int x, int y) {
		if (x == m || y == n)
			return -1;
		if (x == m-1 && y == n-1)
			return matriz[m-1][n-1];
					
		int suma = -1;
		int abajo = sumaMatriz2(x, y+1);
		int derecha = sumaMatriz2(x+1, y);
		if (abajo >= 0 && derecha >= 0)
			suma = Math.min(abajo, derecha) + matriz[x][y];
		else if (abajo == -1)
			suma = derecha + matriz[x][y];
		else if (derecha == -1)
			suma = abajo + matriz[x][y];
		return suma;
	}

	public static void main(String args[]) {
		SumaMatriz sm = new SumaMatriz();
		sm.matriz();
		long start; int s; long end;
		//s = sm.sumaMatriz2(0,0);
		
		start = System.nanoTime();
		s = sm.sumaMatriz(0,0);
		end = System.nanoTime();
		System.out.println("Programacion dinamica: " + s + " " + (end - start));

		start = System.nanoTime();
		s = sm.sumaMatriz2(0,0);
		end = System.nanoTime();
		System.out.println("Recusividad simple: " + s + " " + (end - start));
	}
}
*/
