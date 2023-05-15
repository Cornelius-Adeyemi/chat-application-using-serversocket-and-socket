package com.adebisi.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerThread extends Thread{
    private Socket socket;

    public String name;
    private BufferedReader in;
    public PrintStream out;

    HashMap<String, ServerThread> threadMap;
    public ServerThread(Socket socket, String name,HashMap<String, ServerThread> threadMap ){
        try {
            this.socket = socket;
             this.name = name;
             this.threadMap = threadMap;
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintStream(this.socket.getOutputStream(),true);
        }catch(Exception e){};
    }

    @Override
    public void run() {
      try{

          while(true){
            String message = in.readLine();
            String[] messageArray  = message.split(":");
            String messenger = messageArray[0].trim().toLowerCase();
            String  realMessage = messageArray[messageArray.length-1].trim();
            if(messenger.equals("me")){
             break;
            }else if(messenger.equals("all")){
              sendAll(realMessage);
            }else{
              sendTo(messenger,realMessage);
            }

          }
         this.socket.close();
          this.out.close();
          this.in.close();
      }catch(Exception e){

      }finally {
          try {
              this.socket.close();
              this.out.close();
              this.in.close();
          }catch (Exception e){}
      }


    }

    private void sendTo(String name, String message){
        ServerThread recipient = threadMap.get(name);
        if(recipient != null){
            recipient.out.println(this.name+ ": "+ message);
        }
    }
    private void sendAll(String message){
        for(String name: threadMap.keySet()){
            if(!name.equals(this.name)) {
                threadMap.get(name).out.println(this.name+ ": "+ message);
            }
        }
    }

}
