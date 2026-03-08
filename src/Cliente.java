import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    private static final int PORT = 704;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Introduce la dirección IP del servidor:");
            String serverAddress = sc.nextLine().trim();

            if (serverAddress.isEmpty()) {
                System.err.println("Debes introducir una IP o hostname válido.");
                return;
            }

            while (true) {
                System.out.println("Introduce un número del 1 al 6 (o 'salir' para terminar):");
                String number = sc.nextLine();

                if ("salir".equalsIgnoreCase(number.trim())) {
                    System.out.println("Cliente finalizado.");
                    break;
                }

                try (Socket socket = new Socket(serverAddress, PORT);
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    out.println(number);
                    String answer = in.readLine();
                    System.out.println(answer != null ? answer : "Sin respuesta del servidor.");
                } catch (IOException e) {
                    System.err.println("No se pudo conectar con el servidor: " + e.getMessage());
                }
            }
        }
    }
}
