import java.net.*;
import java.io.*;

public class Server
{
    public static void main (String[] args) throws IOException
    {
          //Connection between server and client is established, when the server runs it
          //listen to the port 40000 for the connection of a user

        DatagramSocket socket = new DatagramSocket (40000);
       
        byte[] buf = new byte[256];
        while (true) {
          DatagramPacket packet = new DatagramPacket (buf, buf.length);
          socket.receive (packet);
          String received = new String (packet.getData());
          System.out.println ("IP address: " + packet.getAddress());
          System.out.println ("Received packet: " + received);
        }   
    }
    
}