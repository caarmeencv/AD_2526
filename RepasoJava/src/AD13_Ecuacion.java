//Cálculo de los valores de una ecuación de 2º grado

package ad01_inicio;

import java.util.Scanner;

public class AD13_Ecuacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float a, b, c;
		
		System.out.println("Resolución de ecuaciones");
		System.out.println("========================");
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.print("Introduce coeficiente a: ");
		a = sc.nextFloat();
		System.out.print("Introduce coeficiente b: ");
		b = sc.nextFloat();
		System.out.print("Introduce coeficiente c: ");
		c = sc.nextFloat();
		
		if (a == 0 && b == 0 && c == 0) {
			System.out.println("La ecuación presenta infinitas soluciones");
		} else if (a == 0 && b == 0 && c != 0) {
			System.out.println("La ecuación no tiene solución");
		} else if (a != 0 && b != 0 && c == 0) {
			System.out.println("x1 = 0");
			System.out.println("x2 = " + -b/a);
		} else if (a == 0 && b != 0 && c != 0) {
			System.out.println("x1 = x2 = " + -c/b);
		} else {
			System.out.println("x1 = " + 
		(-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a));
			System.out.println("x2 = " + 
		(-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a));
		}

		sc.close();
	}

}