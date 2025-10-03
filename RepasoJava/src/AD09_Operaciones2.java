//Programa que evalúa los valores entre dos variables leídas por teclado y calcula:
//* Los múltiplos de 3
//* Imprime los múltiplos de 7
//* La suma de los impares
//* El total de números procesados

package ad01_inicio;

import java.util.Scanner;

public class AD09_Operaciones2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int ini, fin, multiplo3=0, sumaImpares=0, cont=0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("OPERACIONES");
		System.out.println("===========");
		
		//Leemos valores inicial y final
		System.out.print("Introduzca valor inicial: ");
		ini = sc.nextInt();
		System.out.print("Introduzca valor final: ");
		fin = sc.nextInt();
		
		for (int i = ini; i <= fin; i++) {
			if (i % 3 == 0) multiplo3++;
			if (i % 2 != 0) sumaImpares += i;
			if (i % 7 == 0) System.out.println("El número " + i + " es múltiplo de siete");
			cont++;
		}
		
		System.out.println("Múltiplos de tres: " + multiplo3);
		System.out.println("La suma de los impares es: " + sumaImpares);
		System.out.println("Total de números procesados: " + cont);

		sc.close();
	}
}