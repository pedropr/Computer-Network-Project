import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerReturn implements Runnable
{
  Socket socket;
  private Scanner input;
  private PrintWriter out;
  String message = "";
  
  //Constructor
  public ServerReturn(Socket socket)
  {
    this.socket = socket;
  }
  
  public void CheckConnection() throws IOException
  {
    if (!socket.isConnected())
    {
      for (int i = 1; i <= Server.ConnectionArray.size(); i++)
      {
        if (Server.ConnectionArray.get(i) == socket)
        {
          Server.ConnectionArray.remove(i);
        }
      }
      
      for (int i = 1; i <= Server.ConnectionArray.size(); i++)
      {
        Socket tempSocket = (Socket) Server.ConnectionArray.get(i-1);
        PrintWriter tempOut = new PrintWriter(tempSocket.getOutputStream());
        tempOut.println(tempSocket.getLocalAddress().getHostName() + " disconnected!");
        tempOut.flush();
        //Show disconnection at server
        System.out.println(tempSocket.getLocalAddress().getHostName() + "disconnected!");
      }
    }
  }

  public void run()
  {
    try
    {
      try 
      {
        input = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream());
        
        while(true) 
        {
          CheckConnection();
          
          if(!input.hasNext())
          {
            return;
          }
          
          message = input.nextLine();
          
          System.out.println("Client said: " + message);
          for (int i = 1; i <= Server.ConnectionArray.size(); i++)
          {
            Socket tempSocket = (Socket) Server.ConnectionArray.get(i-1);
            PrintWriter tempOut = new PrintWriter(tempSocket.getOutputStream());
            tempOut.println(message);
            tempOut.flush();
            System.out.println("Sent to: " + tempSocket.getLocalAddress().getHostName());
          }
        }
      }
      finally 
      {
        socket.close();
      }
    }
    catch (Exception e) 
    {
      System.out.print(e);
    }
  }
}
