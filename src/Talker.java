

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;



public class Talker 
{
    private BufferedReader buffRead;
    private DataOutputStream dos;   
    private String serverName;
    private int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    
    //constucter for client
    Talker (String serverName, int port, String id)
    {
          
        try 
        {	
        	clientSocket = new Socket(serverName, port);
           
            dos = new DataOutputStream (clientSocket.getOutputStream());
            buffRead = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
            
        } catch (IOException ex) 
        {
            System.out.println ("ERROR: Error in server name or port!");
            ex.printStackTrace();
        }
    }
    
    //constructor for server
    Talker (Socket socket, String id)
    {

        try 
        {	
        	serverSocket = new ServerSocket(1201);
        	socket = serverSocket.accept();
            dos = new DataOutputStream (socket.getOutputStream());
            buffRead = new BufferedReader (new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) 
        {
            System.out.println ("ERROR: Error in contructor using socket");
            ex.printStackTrace();
        }
        
    }
    
    void send (String message) throws IOException
    {
        dos.writeBytes(message + "\n");
                
    }
    
    String recieve () throws IOException
    {
        String received;
        received = buffRead.readLine();
        
        return received;
    }
    
}

    
    

