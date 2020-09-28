package tp1_ex2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	static final int port = 1600;

	   public static void main(String[] args) throws Exception {
		   
		    Socket socket = new Socket("127.0.0.1", port);
	        System.out.println("SOCKET = " + socket);
		
	        BufferedReader plec = new BufferedReader(
	                               new InputStreamReader(socket.getInputStream())
	                               );

	        PrintWriter pred = new PrintWriter(
	                             new BufferedWriter(
	                                new OutputStreamWriter(socket.getOutputStream())),
	                             true);
			
	        Scanner saisieUtilisateur = new Scanner(System.in);
	        
	        int min = 0;
	        int max = 100;
	        
	        System.out.println("Veuillez saisir un nombre entre "+min+" et "+max+" :");
	        int nb = saisieUtilisateur.nextInt();
	        
	        while(nb > max || nb < min) {
	        	System.out.println("Veuillez saisir un nombre entre "+min+" et "+max+" :");
	 	        nb = saisieUtilisateur.nextInt();
	        }
	        
	        pred.println(nb);          				// envoi d'un message
	        String response = plec.readLine();      // lecture de l'écho
	        
	        while(!response.equals("equal")) {
	        	if(response.equals("sup")) {
	        		//superieur
	        		min = nb;
	        	}
	        	if(response.equals("inf")) {
	        		//inferieur
	        		max = nb;
	        	}
	        	System.out.println("Veuillez saisir un nombre entre "+min+" et "+max+" :");
	 	        nb = saisieUtilisateur.nextInt();
	 	        pred.println(nb);          		 // envoi d'un message
		        response = plec.readLine();      // lecture de l'écho
	        }
	        
	        System.out.println("Bien joué !");     // message de terminaison
	        pred.println(200);				//code de fin du jeus
	        plec.close();
	        pred.close();
	        socket.close();
	        
	   }
}
