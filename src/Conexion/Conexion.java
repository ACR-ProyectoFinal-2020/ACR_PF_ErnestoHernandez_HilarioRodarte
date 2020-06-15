package Conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author mijum
 */
public class Conexion {
    	private String ip = "localhost";
	private int port = 22222;
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
        private Boolean accepted;
        private Boolean esRojo;
        private Boolean isClient;
	private ServerSocket serverSocket;
        public Conexion(String ip, int p){
            this.ip = ip;
            this.port = p;
            this.accepted = false;
            this.esRojo = false;
        }
        public Conexion(){this.accepted = false; this.esRojo=false;}
        public boolean connect() {
		try {
                        isClient = true;
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
                        accepted = true;
		} catch (IOException e) {
			System.out.println("No fue posible conectarse a: " + ip + ":" + port + " | Iniciando servidor...");
			return false;
		}
		System.out.println("Servidor conectado...");
		return true;
	}

	public void initializeServer() {
		try {
			serverSocket = new ServerSocket(port);
                        esRojo = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
        
        public void listenForServerRequest() {
		Socket socket = null;
		try {
			socket = serverSocket.accept();
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
			System.out.println("Un cliente ha sido encontrado, y se conectó");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public Boolean getAccepted() {
        return accepted;
    }

    public Boolean getEsRojo() {
        return esRojo;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public Boolean getIsClient() {
        return isClient;
    }

    public void setIsClient(Boolean isClient) {
        this.isClient = isClient;
    }
    
    
    
}
