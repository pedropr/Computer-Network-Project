package chat_server_carli_salali;

import java.io.*;
import java.net.*;


public class Server
{

  public static void main(String[] args) throws Exception
  {
    Server server = new Server();
    server.run();
  }

  private void run() throws Exception
  {
    ServerSocket serverSocket = new ServerSocket(444);
    Socket socket = serverSocket.accept();
    InputStreamReader IR = new InputStreamReader(socket.getInputStream());
    BufferedReader BR = new BufferedReader(IR);
    
    //Prints the message on the server window
    String message = BR.readLine();
    System.out.println(message);
    
    if (message != null)
    {
      //Client see the message: "Message received!"
      PrintStream PS = new PrintStream(socket.getOutputStream());
      PS.println("Message received!");
    }
  }
}
