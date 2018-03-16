import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;


public class ServerSide extends JFrame implements ActionListener{
	
	Container cp;
	JButton sendButton;
	JTextArea textArea;
	JTextField inputField;
	JPanel panel;
	private ServerSocket ss;
	private Socket s;
	private DataOutputStream dos;
	private String message = "";
	private Talker talk;
	private Socket serverSocket;
	private String id = "Server Side: ";
	
	
	public ServerSide(){
		
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
		cp.setSize(500, 500);
		
	
		cp.add(scrollPane, BorderLayout.CENTER);
		cp.add(panel, BorderLayout.SOUTH);
		setUp();
		
		try {
		/*	ss = new ServerSocket(1201);
			s = ss.accept();
			
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());*/
			
			talk = new Talker(serverSocket, id);
			
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
	     setTitle ("Server Side");
	     setVisible (true);
	 	}
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String messageOut = "";
		messageOut = inputField.getText().trim();
		try {
			talk.send(id + messageOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		inputField.setText(" ");

	}
	
public static void main(String arge[]){
		
		new ServerSide();
	}
}
