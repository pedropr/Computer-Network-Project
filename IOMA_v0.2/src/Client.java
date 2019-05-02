import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.JOptionPane;
import java.util.Scanner;


public class Client implements Runnable
{
  Socket socket;
  Scanner input;
  Scanner send = new Scanner(System.in);
  PrintWriter out;
  
  public Client(Socket socket)
  {
    this.socket = socket;
  }
  
  public void run()
  {
    try {
      try {
        input = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream());
        out.flush();
        checkStream();   
      }
      finally {
        socket.close();
      }
    }
    catch (Exception e) {
      System.out.print(e);
    }
  }
  
  public void disconnect() throws IOException
  {
    out.println(ClientGUI.UserName + " has disconnected.");
    out.flush();
    socket.close();
    JOptionPane.showMessageDialog(null, "You disconnected!");
    System.exit(0);
  }
  
  public void checkStream()
  {
    while(true) 
    {
      receive();
    }
  }
  
  public void receive()
  {
    if (input.hasNext())
    {
      String message = input.nextLine();
      
      if (message.contains("#?!"))
      {
        String temp1 = message.substring(3);
        temp1 = temp1.replace("[", "");
        temp1 = temp1.replace("]", "");
        
        String[] currentUsers = temp1.split(", ");
        
        ClientGUI.jL_Online.setListData(currentUsers);
      }
      else
      {
        ClientGUI.tA_Conversation.append(message + "\n");
      }
    }
  }
  
  
  public void send(String str)
  {
    out.println(ClientGUI.UserName + ": " + str);
    out.flush();
    ClientGUI.tF_Message.setText("");
  }
}
