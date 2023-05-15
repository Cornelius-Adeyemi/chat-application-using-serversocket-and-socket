package com.adebisi.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Server {

    private static HashMap<String, ServerThread> threadMap = new HashMap<>();
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket =new ServerSocket(8080);;
        try {


            Socket socket;
            while(true){

                socket = serverSocket.accept();
                String in = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine().toLowerCase();
                ServerThread serverThread = new ServerThread(socket, in,threadMap);
                 threadMap.put(in, serverThread);
                 serverThread.start();


            }

        }catch(Exception e){

        }
        finally {
            serverSocket.close();
        }
    }
}
