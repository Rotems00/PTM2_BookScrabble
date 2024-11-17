package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer {
    private final int port;
    private final ClientHandler ch;
    private volatile boolean stop = false;
   

    public MyServer(int p, ClientHandler c) {
        this.port = p;
        this.ch = c;
    }

    public void start() {
        new Thread(()->runServer()).start();
    }

    public void runServer() {
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            serverSocket.setSoTimeout(1000);
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                   
                  
                        this.ch.handleClient(clientSocket.getInputStream(), clientSocket.getOutputStream());
                       
                        
                     
                  
                       
                    }
                 catch (SocketTimeoutException e) {
                 
                } catch (IOException e) {
             
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
          
            e.printStackTrace();
        }
    }

    public void close() {
        this.stop = true;
    }
}
