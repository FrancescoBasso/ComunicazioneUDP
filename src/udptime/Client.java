/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udptime;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franc
 */
public class Client {
    
    int port = 2000;
		//indirizzo del server
		InetAddress serverAddress;
		//socket UDP
		DatagramSocket dSocket;
		//Datagramma UDP con la richiesta da inviare al server
		DatagramPacket outPacket;
		//Datagramma UDP di risposta ricevuto dal server
		DatagramPacket inPacket;
		
		//buffer per i dati da inviare
		byte[] buffer;
		//messaggio di richiesta
		String message="RICHIESTA DATA E ORA";
		//messaggio di risposta
		String response;
                
                public Client(int port){
        try {
            this.port=port;
            try {
                serverAddress = InetAddress.getLocalHost();
            } catch (UnknownHostException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("Indirizzo del server trovato!");
            dSocket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
                    
                }
                
                public void Invio(){
        try {
            outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, port);
            
            //si inviano i dati
            dSocket.send(outPacket);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
                }
                
            public void Ricezione(){
        try {
            buffer = new byte[256];
            //e il datagramma UDP per ricevere i dati del buffer
            inPacket = new DatagramPacket(buffer, buffer.length);
            
            //si accetta il datagramma di risposta
            dSocket.receive(inPacket);
            
            //si estrae il messaggio
            response = new String(inPacket.getData(), 0, inPacket.getLength());
            
            System.out.println("Connessione stabilita!");
            System.out.println("Data e ora del server: " + response);
            System.out.println("Connessione chiusa!");
            
            //si chiude la connessione
            dSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
                
               
	
    
}
