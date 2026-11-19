import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {
        System.out.println("Esperando conexiones...");
        try(ServerSocket servidor = new ServerSocket(6666);
            Socket cliente = servidor.accept();
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)
        ) {
            System.out.println("Conectado desde " + cliente.getInetAddress());

            String mensaje = entrada.readLine();
            while (!mensaje.equalsIgnoreCase("adios")) {
                salida.println("ECO: " + mensaje);
                mensaje = entrada.readLine();
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("El cliente se ha desconectado: " + e.getMessage() );
        }
    }
}
