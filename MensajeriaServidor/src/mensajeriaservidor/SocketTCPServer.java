
package mensajeriaservidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketTCPServer {

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    
    
    public SocketTCPServer(int puerto) throws IOException {
        serverSocket = new ServerSocket(puerto);
    }
    
    public void start() throws IOException {
        System.out.println("(Servidor) Esperando conexiones...");
        socket = serverSocket.accept();
        dis = new DataInputStream(socket.getInputStream()) ;
        dos = new DataOutputStream(socket.getOutputStream());
        System.out.println("(Servidor) Conexión establecida.");
    }
    
    public void stop() throws IOException {
        System.out.println("(Servidor) Cerrando conexiones...");
        dis.close();
        dos.close();
        socket.close();
        serverSocket.close();
        System.out.println("(Servidor) Conexiones cerradas.");
    }
   
    
    public String leerMensajeTexto() throws IOException {
        System.out.println("(Servidor) Leyendo mensaje...");
        String mensaje = dis.readUTF();
        System.out.println("(Servidor) Mensaje leido.");
        return mensaje;
    }
    
    public void enviarMensajeTexto(String mensaje) throws IOException {
        System.out.println("(Servidor) Enviado mensaje...");
        dos.writeUTF(mensaje);
        System.out.println("(Servidor) Mensaje enviado.");
    }
     
}
