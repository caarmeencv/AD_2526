
public class App {

    public static void main(String[] args) throws Exception {

        //Ejercicio 1
        System.out.println("EJERCICIO 1");

        String A = "patata";
        String B = "patata";

        if (A.equals(B)) {
            System.out.println("A y B son equals");
        } else {
            System.out.println("A y B no son equals");
        }

        if (A == B) {
            System.out.println("A y B son iguales con ==");
        } else {
            System.out.println("A y B no son iguales con ==");
        }

        String C = new String("patata");

        if (A.equals(C)) {
            System.out.println("A y C son equals");
        } else {
            System.out.println("A y C no son equals");
        }

        if (A == C) {
            System.out.println("A y C son iguales con ==");
        } else {
            System.out.println("A y C no son iguales con ==");
        }

        System.out.println("-------------------------------");

        //Ejercicio 2
        System.out.println("EJERCICIO 2");

        Integer D = 0;
        Integer E = 0;

        while (D == E) {

            D++;
            E++;

        }

        if (D != E) {
            System.out.println("D(" + D + ") y E(" + E + ") no son iguales");
        }

        D = 0;
        E = 0;

        while (D == E) {

            D--;
            E--;

        }

        if (D != E) {
            System.out.println("D(" + D + ") y E(" + E + ") no son iguales");
        }

        System.out.println("-------------------------------");

        //Ejercicio 3
        System.out.println("EJERCICIO 3");

        float F = 0;

        while (F < 1) {
            F = F + 0.1F;
            System.out.println("F es " + F);
        }

        System.out.println("-------------------------------");

        //Ejercicio 4
        System.out.println("EJERCICIO 4");

        float G = 0.1F;
        float H = 0.2F;
        float I = G + H;

        System.out.println("Con Float:");
        System.out.println(G + " + " + H + " = " + I);

        System.out.println("Con double:");
        System.out.println(G + " + " + H + " = " + (double) I);

        System.out.println("-------------------------------");

    }
}
