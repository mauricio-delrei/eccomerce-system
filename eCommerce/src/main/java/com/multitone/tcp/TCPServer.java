package com.multitone.tcp;

//import com.multitone.service.MessageService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private static final int PORT = 8080;

    //private MessageService messageService;

  /*  public TCPServer(MessageService messageService) {
        this.messageService = messageService;
    }*/

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New incoming connection: " + clientSocket.getInetAddress().getHostAddress());


                Thread clientThread = new Thread(() -> handleClient(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            short messageType = inputStream.readShort();

            int payloadLength = inputStream.readInt();

            byte[] payload = new byte[payloadLength];
            inputStream.readFully(payload);


            String response = "Response from server";
            outputStream.writeUTF(response);

            inputStream.close();
            outputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
