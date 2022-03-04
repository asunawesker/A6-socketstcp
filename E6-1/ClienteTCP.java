import java.io.*;
import java.net.*;

class ClienteTCP{
    private static final int _PUERTO = 1234;
    public static void main(String args[]){

        InetAddress ipServidor = null;
        try {
            ipServidor = InetAddress.getByName(args[0]);
        } catch (UnknownHostException uhe){
            System.err.println("Host no encontrado : " + uhe);
            System.exit(-1);
        }

        for(int n = 1; n < args.length; n++){
            Socket socketCliente = null;
            DataInputStream datosRecepcion = null;
            DataOutputStream datosEnvio = null;

            try {
                int numero = Integer.parseInt(args[n]);

                socketCliente = new Socket(ipServidor, _PUERTO);

                datosRecepcion = new DataInputStream(socketCliente.getInputStream());
                datosEnvio = new DataOutputStream(socketCliente.getOutputStream());

                datosEnvio.writeInt(numero);

                long resultado = datosRecepcion.readLong();

                System.out.println("Solicitud = " + numero + "\tResultado = " + resultado);

                datosRecepcion.close();
                datosEnvio.close();

            } catch (Exception e){
                System.err.println("Se ha producido la excepcion : " + e);
            }
            try {
                if (socketCliente != null)
                socketCliente.close();
            }catch (IOException ioe){
                System.err.println("Error al cerrar el socket : " + ioe);
            }
        }
    }
}
