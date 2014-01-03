package com.receiver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Receiver {

private static ServerSocket serverSocket;
private static Socket clientSocket;
private static InputStreamReader inputStreamReader;
private static BufferedReader bufferedReader;
private static String message;

	public static void main(String[] args) {
	
	    try {
	        serverSocket = new ServerSocket(4444);  //Server socket
	
	    } catch (IOException e) {
	        System.out.println("Could not listen on port: 4444");
	    }
	
	    System.out.println("Server started. Listening to the port 4444");
	
	    while (true) {
	        try {
	
	            clientSocket = serverSocket.accept();   //accept the client connection
	            inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
	            bufferedReader = new BufferedReader(inputStreamReader); //get client msg                    
	            message = bufferedReader.readLine();
	
	            System.out.println(message);
	            
	            Scanner keyboard = new Scanner(System.in);
	            String response = keyboard.nextLine();
	            
	            System.out.println("Sending response back to phone: " + response);
	            
	            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream());
	            pw.write(response);
	            pw.flush();
	            pw.close();
	            
	            inputStreamReader.close();
	            clientSocket.close();
	
	        } catch (IOException ex) {
	            System.out.println("Problem in message reading");
	        }
	    }

    }
}
