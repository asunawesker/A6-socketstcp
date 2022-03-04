import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServidorTCP {
    private static final String _IP = "192.168.100.9";
    private static final int _PUERTO = 1234;
    private static final int _BACKLOG = 50;
    
    public static void main(String args[]) throws UnknownHostException{

        InetAddress ip = InetAddress.getByName(_IP);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        System.out.println("\nEscuchando en: ");
        System.out.println("IP Host = "+ ip.getHostAddress());
        System.out.println("Puerto = " + _PUERTO);
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(_PUERTO,_BACKLOG,ip);
        }catch (IOException ioe){
            System.err.println("Error al abrir el socket de servidor : " + ioe);
            System.exit(-1);
        }
        int entrada;
        int salida;

        while(true){
            try{
                Socket socketPeticion = serverSocket.accept();

                DataInputStream datosEntrada = new DataInputStream(socketPeticion.getInputStream());
                DataOutputStream datosSalida = new DataOutputStream(socketPeticion.getOutputStream());

                int puertoRemitente = socketPeticion.getPort();
                
                InetAddress ipRemitente = socketPeticion.getInetAddress();

                entrada = datosEntrada.readInt();

                salida = (int) ((long) entrada * (long) entrada);

                datosSalida.writeLong(salida);

                datosEntrada.close();
                datosSalida.close();
                socketPeticion.close();

                System.out.println(formatter.format(new Date()) + "Cliente = " + ipRemitente +
                                   ":" + puertoRemitente + "\tEntrada = " + entrada + "\tSalida = " + salida);
            } catch (Exception e){
                System.err.println("Se ha producido la excepcion : " + e);
            }
        }
    }
}
