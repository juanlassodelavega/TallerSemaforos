import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class Server {

    private static final int PORT = 704;
    private static final int MIN_DRAW = 1;
    private static final int MAX_DRAW = 6;

    public static void main(String[] args) {
        try (ServerSocket listener = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT);
            while (true) {
                try (Socket socket = listener.accept();
                     BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    String inputString = input.readLine();
                    out.println(resolveMessage(inputString));
                } catch (IOException e) {
                    System.err.println("Error atendiendo al cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("No se pudo iniciar el servidor: " + e.getMessage());
        }
    }

    private static String resolveMessage(String inputString) {
        if (inputString == null) {
            return "Debes enviar un número del 1 al 6.";
        }

        String cleanInput = inputString.trim();
        if (cleanInput.isEmpty()) {
            return "Debes enviar un número del 1 al 6.";
        }

        if (!isNumeric(cleanInput)) {
            return cleanInput + " ni siquiera es un número.";
        }

        int clientNumber = Integer.parseInt(cleanInput);
        if (clientNumber < MIN_DRAW || clientNumber > MAX_DRAW) {
            return "El número no entra en el sorteo.";
        }

        int winnerNumber = ThreadLocalRandom.current().nextInt(MIN_DRAW, MAX_DRAW + 1);
        if (clientNumber == winnerNumber) {
            return "El número " + clientNumber + " ha resultado ganador. Enhorabuena.";
        }

        return "Mala suerte, el número premiado ha sido el " + winnerNumber + ". Inténtalo de nuevo.";
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
