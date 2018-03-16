import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;


public class ClientSide extends JFrame implements ActionListener{
	
	Container cp;
	JButton sendButton;
	JTextArea textArea;
	JTextField inputField;
	JPanel panel;
	private Socket s;
	private DataOutputStream dout;
	private DataInputStream din;
	private String message = "";
	private String id = "Client: ";
	private int port = 1201;
	private String serverName = "127.0.0.1";
	Talker talk;
	
	
	
	public ClientSide(){
		
		sendButton = new JButton("Send");
		sendButton.addActionListener(this);
		sendButton.setActionCommand("Send");
		
		inputField = new JTextField(20);
		inputField.requestFocus();

		textArea = new JTextArea(50,50);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);  
	
		panel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); 

		panel.add(inputField);
		panel.add(sendButton);
		cp = getContentPane();
			
	
		cp.add(scrollPane, BorderLayout.CENTER);
		cp.add(panel, BorderLayout.SOUTH);
		setUp();
		
		try {
		/*	s = new Socket("127.0.0.1" , 1201);
			
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());*/
			
			talk = new Talker(serverName, port, id);
			while(!message.equals("Exit")){
				
				message = talk.recieve();
				textArea.setText(textArea.getText().trim()+"\n" + message); //display message from client
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 void setUp ()
	 {
	     Toolkit tk;
	     Dimension d;
	     
	     setDefaultCloseOperation (EXIT_ON_CLOSE);
	     
	     tk = Toolkit.getDefaultToolkit ();
	     d = tk.getScreenSize ();
	     
	     setSize (d.width/3, d.height/3);
	     setLocation (d.width/4, d.height/4);
	     setTitle ("Client Side");
	     setVisible (true);
	 	}
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String messageOut = "";
		messageOut = inputField.getText().trim();
		try {
			talk.send( id + messageOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		inputField.setText(" ");
	}
	
public static void main(String arge[]){
		
		new ClientSide();
	}
}
