
package com.mycompany.mensajeriacliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTCPClient {

    private String serverIP;
    private int serverPort;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    
    public SocketTCPClient(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public void start() throws UnknownHostException, IOException {
        System.out.println("(Cliente) Estableciendo conexión...");
        socket = new Socket(serverIP, serverPort);
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
        System.out.println("(Cliente) Conexión establecida.");
    }
    
    public void stop() throws IOException {
        System.out.println("(Cliente) Cerrando conexiones...");
        dis.close();
        dos.close();
        socket.close();  
        System.out.println("(Cliente) Conexiones cerradas.");
    }
    
    
    public String leerMensajeTexto() throws IOException {
        System.out.println("(Cliente) Leyendo mensaje...");
        String mensaje = dis.readUTF();
        System.out.println("(Cliente) Mensaje leido.");
        return mensaje;
    }
    
    public void enviarMensajeTexto(String mensaje) throws IOException {
        System.out.println("(Cliente) Enviado mensaje...");
        dos.writeUTF(mensaje);
        System.out.println("(Cliente) Mensaje enviado.");
    }
    
    
}
