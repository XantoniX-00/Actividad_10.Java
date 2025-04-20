import java.util.*;

public class Principal {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        List<Thread> hilos = new ArrayList<>();

        System.out.println("Ingrese contraseñas para validar (escriba 'salir' para terminar):");

        while (true) {
            System.out.print("Contraseña: ");
            String contrasena = entrada.nextLine();

            if (contrasena.equalsIgnoreCase("salir")) break;

            Thread hilo = new Thread(new ValidadorContrasena(contrasena));
            hilo.start();
            hilos.add(hilo);
        }

        hilos.forEach(hilo -> {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("Error al esperar hilo: " + e.getMessage());
            }
        });

        System.out.println("Validación finalizada. Revisa el archivo 'registro.txt' para ver el historial.");
        entrada.close();
    }
}
