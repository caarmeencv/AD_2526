import java.util.Random;

public class  hilos{

    public static void main(String[] args) {

        Thread tarea1 = new Thread(new MiTarea("Tarea 1"));
        Thread tarea2 = new Thread(new MiTarea("Tarea 2"));
        Thread tarea3 = new Thread(new MiTarea("Tarea 3"));

        tarea1.start();
        tarea2.start();
        tarea3.start();
    }
}

class MiTarea implements Runnable {

    private String tarea;
    private Random random = new Random();

    public MiTarea(String tarea) {
        this.tarea = tarea;
    }

    @Override
    public void run() {
        try {
            System.out.println(tarea + " iniciada en el hilo: " + Thread.currentThread().getName());

            int espera = 1000 + random.nextInt(2000); 
            Thread.sleep(espera);

            System.out.println(tarea + " finalizada en el hilo: " + Thread.currentThread().getName());

        } catch (InterruptedException e) {
            System.out.println(tarea + " fue interrumpida.");
        }
    }
}

