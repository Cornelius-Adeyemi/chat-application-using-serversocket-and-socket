package com.adebisi.client;

import java.io.BufferedReader;
import java.net.Socket;

public class ServerToClient extends Thread{
    private Socket socket;
    private BufferedReader in;
    public ServerToClient(Socket socket, BufferedReader in){
        this.socket = socket;
        this.in = in;
    }

    public void run(){
        try{
            while(true){
                String message = in.readLine();
                System.out.println(message);
            }

        }catch(Exception e){

        }
        finally {
            try{

            }catch (Exception e){}
        }

    }
}
