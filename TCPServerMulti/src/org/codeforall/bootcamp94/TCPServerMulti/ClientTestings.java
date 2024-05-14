package org.codeforall.bootcamp94.TCPServerMulti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientTestings {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new MessageSend());
        executorService.execute(new MessageReceive());
    }
}

class MessageSend implements Runnable {

    @Override
    public void run() {
        try{
            Socket clientSocket = new Socket("localhost", 8080);
            PrintWriter outServer = new PrintWriter(clientSocket.getOutputStream(), true);
            String message;
            while (true){
                System.out.println("Enter your message mr.client (type quit to.....quit....duh): ");
                BufferedReader consoleInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                message = consoleInput.readLine();
                if (message.equalsIgnoreCase("quit")){
                    break;
                }
                outServer.println(message);
            }
            clientSocket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class MessageReceive implements Runnable{

    @Override
    public void run() {
        try{
            Socket clientSocket = new Socket("localhost", 8080);
            BufferedReader inServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String response;
            while (true){
                response = inServer.readLine();
                if (response == null){
                    break;
                }
                System.out.println("Server sent you: " + response);
            }
            clientSocket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}