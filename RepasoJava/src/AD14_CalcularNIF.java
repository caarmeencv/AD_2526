//Programa que calcula el NIF de un DNI leído por teclado

package ad01_inicio;

import java.util.Scanner;

public class AD14_CalcularNIF {

	public static void main(String[] args) {	
		// TODO Auto-generated method stub
		 Scanner sc = new Scanner(System.in);
		 String caracteres="TRWAGMYFPDXBNJZSQVHLCKE";
		 int numDNI;
		 int resto;
       
		 System.out.print("Introduce tu número de DNI: ");
		 
		 numDNI = sc.nextInt();
       
		 resto = numDNI%23;
       
		 System.out.println("El NIF es: " + numDNI + caracteres.charAt(resto));
		 
		 sc.close();
	}

}