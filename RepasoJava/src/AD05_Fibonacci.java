//Cálculo de la serie de Fibonacci hasta un valor dado

package ad01_inicio;

import java.util.Scanner;

public class AD05_Fibonacci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int max;
		int t1, t2, aux;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("SERIE DE FIBONACCI");
		System.out.println("==================");
		
		System.out.print("Introduzca el valor máximo de la serie: ");
		max = sc.nextInt();
		
		t1 = 0;
		t2 = 1;
		System.out.print(t1 + ", " + t2 + ", ");
		aux = t1 + t2;
		
		while (aux < max) {
			System.out.print(aux + ", ");
			t1 = t2;
			t2 = aux;
			aux = t1 + t2;
		}
		
		sc.close();
		
	}

}