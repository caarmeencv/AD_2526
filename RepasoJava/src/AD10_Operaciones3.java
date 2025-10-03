/*
 * Programa que recorre los números enteros entre ini y fin y muestra los siguientes resultados:
 * 	-Suma de pares
 * 	-Imprime los múltiplos de un valor leído por teclado
 * 	-Imprime la suma de los múltiplos de un valor leído por teclado
 * */

package ad01_inicio;

import java.util.Scanner;

public class AD10_Operaciones3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int ini, fin, acumPar = 0, acumSumar = 0, multSumar, multMostrar;
		
		System.out.println("PROGRAMA DE OPERACIONES_1");
		System.out.println("=========================");
		
		System.out.print("Introduzca Inicio del bucle: ");
		ini = sc.nextInt();
		System.out.print("Introduzca Fin del bucle: ");
		fin = sc.nextInt();
		System.out.print("Introduzca el múltiplo que quieres sumar: ");
		multSumar = sc.nextInt();
		System.out.print("Introduzca el múltiplo que quieres mostrar: ");
		multMostrar = sc.nextInt();		
		
		for (int i = ini; i <= fin; i++) {
			if (i%2 == 0) acumPar += i;  //acumPar = acumPar + i;
			
			if (i%multMostrar == 0) System.out.println(i + " es múltiplo de " + multMostrar);
			
			if (i%multSumar == 0) acumSumar += i; 			
			
		
		}
		
		System.out.println("La suma de los pares es: " + acumPar);
		System.out.println("La suma de los múltiplos de " + multSumar + " es: " + acumSumar);
		
		sc.close();
	}
}