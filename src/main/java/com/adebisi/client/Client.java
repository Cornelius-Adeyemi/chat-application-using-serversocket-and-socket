package com.adebisi.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args){
       try {
           Scanner scanner = new Scanner(System.in);

           Socket socket = new Socket("localhost",8080);
           BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
           PrintStream out = new PrintStream(socket.getOutputStream(),true);
           BufferedReader write = new BufferedReader(new InputStreamReader(System.in));

           System.out.println("Please enter your first name");
           String name = scanner.nextLine();
           out.println(name.toLowerCase());
           System.out.println("your name: "+ name.toLowerCase() );
           System.out.println("***************");
           System.out.println("To write a message to the server, please prefix with either all:, me, or name:(the name of the client you want to message) ");
           System.out.println("Example:");
           System.out.println("all:How are you doing-(this is to send message to all the client)");
           System.out.println("adebisi:how are you doing? -(This is to send a message to adebisi)");
           System.out.println("me:exit- (to exit connection with the server)");

            ServerToClient serverToClient = new ServerToClient(socket, in);
            serverToClient.start();


           while(true){
               String message = write.readLine();
               System.out.println("you sent: "+ message);
               out.println(message);

               if(message.indexOf("me:") == 0){
                   break;
               }

           }
         socket.close();
          out.close();
          write.close();
          in.close();



       }catch (Exception e){}
    }
}
