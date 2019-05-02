package chat_server_carli_salali;

import java.io.*;
import java.net.*;


public class Client
{

  public static void main(String[] args) throws Exception
  {
    Client client = new Client();
    client.run();
  }

  private void run() throws Exception
  {
    //localhost = 127.0.0.1 means this computer
    Socket socket = new Socket("localhost", 444);
    PrintStream PS = new PrintStream(socket.getOutputStream());
    //Send this to the server
    PS.println("Hello to Server from Client.");
    
    //listen something comming back from the server
    InputStreamReader IR = new InputStreamReader(socket.getInputStream());
    BufferedReader BR = new BufferedReader(IR);
    
    //Prints the message comming from the server to the client
    String message = BR.readLine();
    System.out.println(message);
    
  }
}