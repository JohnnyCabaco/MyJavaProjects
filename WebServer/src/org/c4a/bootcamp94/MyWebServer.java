package org.c4a.bootcamp94;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyWebServer {
    public static void main(String[] args) throws IOException {

        ServerSocket myServerSocket = new ServerSocket(8080);

        while (true) {
            System.out.println("Awaiting connection...");
            Socket myClientSocket = myServerSocket.accept();
            System.out.println("Connection accepted.");
            requestHandler(myClientSocket);
            System.out.println("Request handled.");
            myClientSocket.close();
            System.out.println("Client socket closed.");
        }
    }

    public static void requestHandler(Socket myClientSocket) {

        File myHtmlFile = new File("WebServer/resources/index.html");

        BufferedReader myBufferedReader = null;

        OutputStream myOutputStream = null;

        try {

            myBufferedReader = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream()));

            myOutputStream = myClientSocket.getOutputStream();

            String input = myBufferedReader.readLine();
            String responseHeader = "";

            if (input.startsWith("GET / HTTP/1.1")) {
                if (!myHtmlFile.exists()) {
                    responseHeader = "HTTP/1.1 404 Not Found\r\n" +
                            "Content-Type: text/html; charset=UTF-8\r\n" +
                            "Content-Length: 0\r\n" +
                            "\r\n";

                    myOutputStream.write(responseHeader.getBytes());
                    myOutputStream.close();
                    return;
                }

                responseHeader = "HTTP/1.0 200 Document Follows\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "Content-Length: " + myHtmlFile.length() + "\r\n" +
                        "\r\n";

                myOutputStream.write(responseHeader.getBytes());

                FileInputStream myFileInputStream = new FileInputStream(myHtmlFile);
                byte[] myBuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = myFileInputStream.read(myBuffer)) != -1) {
                    myOutputStream.write(myBuffer, 0, bytesRead);
                }
                myFileInputStream.close();
            }

        } catch (
                IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            try {
                myBufferedReader.close();
                myOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
