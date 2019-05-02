import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server
{
  public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
  public static ArrayList<String> CurrentUsers = new ArrayList<String>();
  
  public static void main(String[] args) throws IOException
  {
    try
    {
      final int port = 4445;
      ServerSocket server = new ServerSocket(port);
      System.out.println("Waiting for clients...");
      
      while (true)
      {
        //Accept a connection and add to the connection array
        Socket socket = server.accept();
        ConnectionArray.add(socket);
        //Get IP address and the hostname
        System.out.println("Client connected from: " + socket.getLocalAddress().getHostName());
        
        AddUserName(socket);
        
        ServerReturn chat = new ServerReturn(socket);
        //Create a thread
        Thread x = new Thread(chat);
        x.start();
      }
    }
    catch (Exception x)
    {
      System.out.print(x);
    }
  }

  private static void AddUserName(Socket socket) throws IOException
  {
    Scanner input = new Scanner(socket.getInputStream());
    String UserName = input.nextLine();
    CurrentUsers.add(UserName);
    //
    for (int i = 1; i <= Server.ConnectionArray.size(); i++)
    {
      Socket tempSocket = (Socket) Server.ConnectionArray.get(i-1);
      PrintWriter out = new PrintWriter(tempSocket.getOutputStream());
      out.println("#?!" + CurrentUsers);
      out.flush();
    }
    
  }

}
