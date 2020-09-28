package tp1_ex2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Serveur {

	static final int port = 1600;

	   public static void main(String[] args) throws Exception {
	        ServerSocket s = new ServerSocket(port);
	        Socket soc = s.accept();
	        
	        // Un BufferedReader permet de lire par ligne.
	        BufferedReader plec = new BufferedReader(
	                               new InputStreamReader(soc.getInputStream())
	                              );

	        // Un PrintWriter possède toutes les opérations print classiques.
	        // En mode auto-flush, le tampon est vidé (flush) à l'appel de println.
	        PrintWriter pred = new PrintWriter(
	                             new BufferedWriter(
	                                new OutputStreamWriter(soc.getOutputStream())), 
	                             true);
	        Random r = new Random();
	        int min = 0;
	        int max = 100;
	        
	        int nombreAtrouver = r.nextInt(max-min) + min;
	        System.out.println("le nombre a trouver est " + nombreAtrouver);
	        while (true) {
	           int nb = Integer.parseInt(plec.readLine());  // lecture du message
	           System.out.println(nb);
	           
	           if (nombreAtrouver > nb) {
	        	   //superieur
	        	   String rep = "sup";
	        	   System.out.println(rep);   // trace locale
		           pred.println(rep); 		  // renvoi d'un écho
	           }
	           if (nombreAtrouver < nb) {
	        	   //inferieur
	        	   String rep = "inf";
	        	   System.out.println(rep);   // trace locale
		           pred.println(rep); 		  // renvoi d'un écho
	           }
	           if (nombreAtrouver == nb) {
	        	   //equal
	        	   String rep = "equal";
	        	   System.out.println(rep);   // trace locale
		           pred.println(rep); 		  // renvoi d'un écho
	           }
	           if (nb == 200) break;
	                                
	        }
	        
	        plec.close();
	        pred.close();
	        soc.close();
	   }
}
